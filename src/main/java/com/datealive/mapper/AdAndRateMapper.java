package com.datealive.mapper;

import com.datealive.entity.pojo.AdAndRate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName: AdAndRateMapper
 * @Description: TODO
 * @author: datealive
 * @date: 2021/5/4  20:47
 */
@Mapper
public interface AdAndRateMapper {

    /**
     * 根据广告ID获取 广告和比赛的关系信息
     * @param gzhu_ad_id
     * @return
     */
    AdAndRate getAdAndRateByAdId(@Param("gzhu_ad_id")Long gzhu_ad_id);

    /**
     * 根据比赛ID获取 广告和比赛的关系信息
     * @param gzhu_rate_id
     * @return
     */
    AdAndRate getAdAndRateByRateId(@Param("gzhu_rate_id")Long gzhu_rate_id);

    List<AdAndRate> getAdAndRateListByRateId(@Param("gzhu_rate_id")Long gzhu_rate_id);

    /**
     * 插入 广告和比赛的关系信息
     * @param adAndRate
     * @return
     */
    int insertAdAndRate(AdAndRate adAndRate);

    /**
     * 根据广告ID 删除 广告和比赛的关系
     * @param gzhu_ad_id
     * @return
     */
    int deleteAdAndRateByAdId(@Param("gzhu_ad_id")Long gzhu_ad_id);

    /**
     * 根据比赛ID 删除 广告和比赛的关系
     * @param gzhu_rate_id
     * @return
     */
    int deleteAdAndRateByRateId(@Param("gzhu_rate_id")Long gzhu_rate_id);
}
