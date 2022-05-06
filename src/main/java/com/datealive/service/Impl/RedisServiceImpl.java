package com.datealive.service.Impl;

import com.datealive.common.StaticFinalCode;
import com.datealive.entity.dto.BigUserDetail;
import com.datealive.entity.dto.RateTopList;
import com.datealive.entity.pojo.Rate;
import com.datealive.service.RedisService;
import com.datealive.utils.JacksonUtils;
import com.datealive.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @ClassName: RedisServiceImpl
 * @Description: TODO
 * @author: datealive
 * @date: 2021/5/16  12:56
 */
@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private RedisUtil redisUtil;



    private boolean hsetKey(String key,String item,Object value){
        if (!redisUtil.hHasKey(key, item)) {
            boolean hset = redisUtil.hset(key,item, value);
            return hset;
        }
        return true;
    }

    private boolean hsetKeyWithTime(String key,String item,Object value,long time){
        if (!redisUtil.hHasKey(key, item)) {
            boolean hset = redisUtil.hset(key,item, value,time);
            return hset;
        }
        return true;
    }


    @Override
    public boolean hsetUidAndOpenidKey(String userId, String openId) {
        return this.hsetKey(StaticFinalCode.UidAndOpenidKey,userId,openId);
    }

    @Override
    public boolean hsetUserAndResumeKey(String userId,BigUserDetail bigUserDetail) {
        return this.hsetKey(StaticFinalCode.UserAndResumeKey,userId,bigUserDetail);
    }

    @Override
    public void hdelKeyAndItem(String key, String item) {
        redisUtil.hdel(key,item);
    }

    @Override
    public boolean setTopDataKey(String key, Object value, long time) {
        return redisUtil.set(key,value,time);
    }

    @Override
    public boolean hsetJoinTeamRequestKey(String key, String item, Object value, long time) {
        return this.hsetKeyWithTime(key, item, value, time);
    }

    @Override
    public boolean hsetInviteMessage(String key, String item, Object value, long time) {
        return this.hsetKeyWithTime(key, item, value, time);
    }

    @Override
    public boolean hsetApplyMessage(String key, String item, Object value, long time) {
        return this.hsetKeyWithTime(key, item, value, time);
    }

    @Override
    public void zincrRateViewByOne(String key, String value) {
        redisUtil.zIncrementScore(key,value,1);
    }

    @Override
    public void zremoveRateView(String key, String value) {
        redisUtil.zremove(key,value);
    }

    @Override
    public Set<Object> zgetTopRateViewList(long start, long end) {
         return redisUtil.zReverseRange(StaticFinalCode.RATE_TOP_10_INCY_key,start,end);
    }

    @Override
    public boolean hsetRateDetailByIdKey(String rateId, Rate rate) {
        return this.hsetKey(StaticFinalCode.Rate_DEATIL_BYID_Key,rateId,rate);
    }

    @Override
    public Rate hgetRateDetailByIdKey(String rateId) {
        Object hget = redisUtil.hget(StaticFinalCode.Rate_DEATIL_BYID_Key, rateId);
        if (hget!=null) {
            Rate rate = JacksonUtils.convertValue(hget, Rate.class);
            return rate;
        }
        return null;
    }

    @Override
    public boolean setTopRateList(Object value,long time) {
        return redisUtil.set(StaticFinalCode.Rate_TOP_List_Key,value,time);
    }

    @Override
    public Object getTopRateList() {
        return redisUtil.get(StaticFinalCode.Rate_TOP_List_Key);
    }


}
