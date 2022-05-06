package com.datealive.wechat.service.impl;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.datealive.common.Result;
import com.datealive.common.StaticFinalCode;
import com.datealive.utils.RedisUtil;
import com.datealive.utils.StringUtils;
import com.datealive.wechat.entity.TemplateMessage;
import com.datealive.wechat.entity.TemplateParam;
import com.datealive.wechat.service.WxSubscribeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName: WxSubscribeMessageImpl
 * @Description: TODO
 * @author: datealive
 * @date: 2021/5/7  23:49
 */
@Service
@Slf4j
public class WxSubscribeMessageImpl implements WxSubscribeMessage {


    @Autowired
    private RedisUtil redisUtil;

    public volatile String AccessToken="";


    @Override
    public Result sendMessage(TemplateMessage templateMessage) {
        System.out.println(templateMessage);
        JSONObject body=new JSONObject();
        body.set("touser",templateMessage.getTouser());
        body.set("template_id",templateMessage.getTemplate_id());
        JSONObject json=new JSONObject();

        List<TemplateParam> data = templateMessage.getData();
        for (int i = 0; i < data.size(); i++) {
            TemplateParam param = data.get(i);
            json.set(param.getKey(), new JSONObject().set("value",param.getValue()));
        }
        body.set("data",json);

        //发送
        String accessToken= getAccessToken();
        String post =  HttpUtil.post("https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token=" + accessToken, body.toString());
        System.out.println(post);
        JSONObject jsonObject = JSONUtil.parseObj(post);
        String errcode = jsonObject.getStr("errcode");
        String errmsg = jsonObject.getStr("errmsg");
        if(!"0".equals(errcode)){
            return Result.error(errmsg);
        }
        //发送消息到前端，这里返回信息到结果里 前端调用进入message组件内
        //消息有 1.创建队伍后的提醒 2.申请进入队伍 队长的提醒 3.通过审核的提醒 4.没有通过审核的提醒
        //这里直接返回json就行  字段前端识别下
        return Result.success(errmsg,json);


    }

    public String getAccessToken() {
        //如果在redis缓存中有这个key 则去缓存拿 这里需要考虑下
        //如果恰好这个key刚刚过期 然后一堆请求过来 导致了缓存击穿 但缓存击穿也没啥好办法
        //一个是 设置key永不过期 这显然不行 token是有时效性的
        //另外一个是 使用分布式锁 这里使用分布式锁 访问先调方法getAccessToken 如果获取不到返回为空 则调用freshAccessToken方法刷新token
        //这里需要注意的是 如果 你获取到了临界过期的有效token 那么 如果 刚好在你访问的时候过期了，此时模板信息接口会失效
        //这里可以采取两个办法来解决 一个是 判断状态码重新freshAccessToken 一个是把redis中key的过期时间设置短一点

        checkAndResetToken();
        return AccessToken;

    }
    private void checkAndResetToken(){
        String access_token = (String) redisUtil.get(StaticFinalCode.ACCESS_TOKEN);
        //如果为空 则加分布式锁
        if (StringUtils.isEmpty(access_token)) {
            long currentTimeMillis = System.currentTimeMillis();
            String redisLockValue = String.valueOf(currentTimeMillis + StaticFinalCode.LOCK_TIMEOUT);
            final boolean lock = redisUtil.lock(StaticFinalCode.RedisLockKey, redisLockValue);
            if(lock){
                try {
                    AccessToken=getAccessTokenApiGetRequest();
                }catch (Exception e){

                }finally {
                    log.info("释放了锁");
                    redisUtil.unlock(StaticFinalCode.RedisLockKey,redisLockValue);
                }
            }else{
                log.info("没获取到锁的");
                //没有拿到锁的线程
//                while (true){
//                     access_token = (String) redisUtil.get(StaticFinalCode.ACCESS_TOKEN);
//                     if(!StringUtils.isEmpty(access_token)){
//                         break;
//                     }
//                }
                //每个没拿到锁的进程 最多尝试5次获取token 如果没有获取到 就返回null
                int failCount=1;
                while(failCount<=5){
                    try {
                        //每个线程sleep 300毫秒
                        Thread.sleep(300);
                        //尝试获取token
                        access_token = (String) redisUtil.get(StaticFinalCode.ACCESS_TOKEN);
                        if(!StringUtils.isEmpty(access_token)){
                             AccessToken=access_token;
                             break;
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally {
                        failCount++;
                    }
                }

            }
        }else{
            AccessToken=access_token;
        }
    }


    private String getAccessTokenApiGetRequest(){

        String result = HttpUtil.get("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + WxUserLoginServiceImpl.appid + "&secret=" + WxUserLoginServiceImpl.secret);
        JSONObject jsonObject = JSONUtil.parseObj(result);

        String AccessToken = jsonObject.getStr(StaticFinalCode.ACCESS_TOKEN);

        if (!StringUtils.isEmpty(AccessToken)) {
            //这里缓存token的话 如果是缓存两个小时，可能会出问题
            //比如在缓存过期的最后一秒 你获取到了token 但是你发送请求的时候 token恰好过期
            //这就会导致请求失败 所以这里的解决方法是 将key的过期时间稍微减短
            //token获取次数每天 2000
            //一天24个小时 /2 ==12
            //每天仅需获取12次token
            //为了保证有效token 缓存过期时间可以设置成1个小时 相当于 合理情况下 一天获取24个token
            redisUtil.set(StaticFinalCode.ACCESS_TOKEN,AccessToken,StaticFinalCode.ACCESS_TOKEN_EXPIRE-3600);
            log.info("getAccessTokenApiGetRequest ----=====缓存accessstoken");
            return AccessToken;
        }
        return null;
    }



}
