package com.datealive.service.Impl;

import com.datealive.common.PageResult;
import com.datealive.common.ResultCode;
import com.datealive.common.StaticFinalCode;
import com.datealive.entity.dto.AdRateDto;
import com.datealive.entity.pojo.Ad;
import com.datealive.entity.pojo.AdAndRate;
import com.datealive.mapper.AdAndRateMapper;
import com.datealive.mapper.AdMapper;
import com.datealive.service.AdService;
import com.github.pagehelper.PageHelper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: AdServiceImpl
 * @Description: TODO
 * @author: datealive
 * @date: 2021/5/4  22:30
 */
@Service
public class AdServiceImpl implements AdService {

    @Autowired
    private AdMapper adMapper;

    @Autowired
    private AdAndRateMapper adAndRateMapper;


    @Override
    public PageResult<Ad> getAllAdMapperByPage(Integer pageNum) {
        Long allAdCnt = adMapper.getAllAdCnt();
        int totalPage = (int) Math.ceil(allAdCnt* 1.0 / StaticFinalCode.PageSize_10);
        PageHelper.startPage(pageNum,StaticFinalCode.PageSize_10);
        List<Ad> adList = adMapper.getAllAdMapperByPage();
        if(adList.isEmpty()){
            return new PageResult<Ad>(ResultCode.Not_Found,totalPage,pageNum,null);
        }else{
            return new PageResult<Ad>(ResultCode.Success,totalPage,pageNum,adList);
        }

    }

    @Override
    public Long getAllAdCnt() {
        return adMapper.getAllAdCnt();
    }

    /**
     *
     * @param ad_id
     * @return
     */
    @Override
    public Ad getAdByAdId(Long ad_id) {

        return adMapper.getAdByAdId(ad_id);
    }

    //这里先设置 一个比赛只有一个广告
    @Override
    public List<Ad> getAdByRateId(Long rate_id) {

        List<Ad> adList=new ArrayList<>();
        //先在关系表中查询 是否存在
        List<AdAndRate> adAndRateByRateId = adAndRateMapper.getAdAndRateListByRateId(rate_id);
        //如果存在 则继续
        if(!adAndRateByRateId.isEmpty()){
            //根据找到的关系实体查询比赛
            for (AdAndRate adAndRate : adAndRateByRateId) {
                Ad adByAdId = adMapper.getAdByAdId(adAndRate.getGzhuAdId());
                adList.add(adByAdId);
            }

            return adList;
        }
        //如果不存在 返回null
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean insertAd(AdRateDto adRateDto) {
        Ad ad = new Ad(null,adRateDto.getAdName(),
                                  adRateDto.getAdUrl(),adRateDto.getAdBanner());
        int insertAd = adMapper.insertAd(ad);
        //如果广告插入成功
        if(insertAd>0){
            //则在关系表中 插入广告和比赛的关系  这里有个问题 创建比赛的时候 比赛ID从哪里来？
            //采取一个折中的办法  创建完比赛后 才能给比赛添加广告  分为两步完整创建比赛
            AdAndRate adAndRate = new AdAndRate(null, ad.getAdId(), adRateDto.getGzhuRateId());

            int insertAdAndRate = adAndRateMapper.insertAdAndRate(adAndRate);
            if(insertAdAndRate>0){
                return true;
            }
            return false;
        }
        return false;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteAdByAdId(Long ad_id) {
        //删除一个比赛下的广告
        //需要开启事务
        //由于关系表是外键 所以删除要先删 关系表表 然后删除 广告表
        //先删除 关系表
        int deleteAdAndRateByAdId = adAndRateMapper.deleteAdAndRateByAdId(ad_id);
        //关系表删除成功
        if(deleteAdAndRateByAdId>0){
            //接着删除广告表
            int deleteAd = adMapper.deleteAd(ad_id);
            if(deleteAd>0){
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteAdByRateId(Long rate_id) {
        //开启事务  先删除关系表表 然后删除 广告表
        //根据比赛ID来删除广告
        //先在关系表中根据比赛ID查对应关系获得广告ID
        AdAndRate adAndRateByRateId = adAndRateMapper.getAdAndRateByRateId(rate_id);
        Long gzhuAdId = adAndRateByRateId.getGzhuAdId();
        //接着删除关系表
        int deleteAdAndRateByRateId = adAndRateMapper.deleteAdAndRateByRateId(rate_id);
        //如果删除关系表成功
        if(deleteAdAndRateByRateId>0){
            //删除广告表
            int deleteAd = adMapper.deleteAd(gzhuAdId);
            if(deleteAd>0){
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public boolean updateAdByAdId(Ad ad) {
        Long adId = ad.getAdId();
        if(adId!=null){
            int updateAd = adMapper.updateAd(ad);
            if(updateAd>0){
                return true;
            }
            return false;
        }
        return false;
    }
}
