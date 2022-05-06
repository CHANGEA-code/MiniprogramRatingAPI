package com.datealive.service;

import com.datealive.entity.dto.BigUserDetail;
import com.datealive.entity.dto.RateTopList;
import com.datealive.entity.pojo.Rate;

import java.util.List;
import java.util.Set;

/**
 * @ClassName: RedisService
 * @Description: TODO
 * @author: datealive
 * @date: 2021/5/16  12:56
 */
public interface RedisService {

    public boolean hsetUidAndOpenidKey(String userId,String openId);

    public boolean hsetUserAndResumeKey(String userId,BigUserDetail bigUserDetail);

    public void hdelKeyAndItem(String key,String item);

    public boolean setTopDataKey(String key,Object value,long time);

    public boolean hsetJoinTeamRequestKey(String key,String item,Object value,long time);

    public boolean hsetInviteMessage(String key,String item,Object value,long time);

    public boolean hsetApplyMessage(String key,String item,Object value,long time);

    public void zincrRateViewByOne(String key,String value);

    public void zremoveRateView(String key,String value);

    public Set<Object> zgetTopRateViewList(long start, long end);

    public boolean hsetRateDetailByIdKey(String rateId, Rate rate);

    public Rate hgetRateDetailByIdKey(String rateId);

    public boolean setTopRateList(Object value,long time);

    public Object getTopRateList();
}
