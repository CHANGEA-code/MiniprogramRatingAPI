package com.datealive.wechat.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.datealive.utils.StringUtils;
import com.datealive.wechat.service.WxMiniApi;
import com.datealive.wechat.utils.WeChatUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @ClassName: WxMiniApiImpl
 * @Description: TODO
 * @author: datealive
 * @date: 2021/5/6  11:03
 */
@Slf4j
@Service
public class WxMiniApiImpl implements WxMiniApi {


    @Override
    public JSONObject authCode2Session(String appId, String secret, String jscode) {
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + appId + "&secret=" + secret + "&js_code=" + jscode + "&grant_type=authorization_code";
        String str = WeChatUtil.httpRequest(url, "GET", null);
        log.info("api/wx-mini/getSessionKey:" + str);
        if (StringUtils.isEmpty(str)) {
            return null;
        } else {
            return JSONObject.parseObject(str);
        }

    }
}
