package com.datealive.mapper;

import com.datealive.entity.pojo.Ad;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import java.util.List;

/**
 * @ClassName: AdMapper
 * @Description: TODO
 * @author: datealive
 * @date: 2021/5/4  17:39
 */
@Mapper
public interface AdMapper {

    /**
     * 根据分页获取广告信息
     * @return
     */
     List<Ad> getAllAdMapperByPage();

    /**
     * 获取所有广告的数量
     * @return
     */
     Long getAllAdCnt();

    /**
     * 根据广告ID获取广告内容
     * @param ad_id
     * @return
     */
     Ad getAdByAdId(@Param("ad_id")Long ad_id);

    /**
     * 插入广告
     * @param ad
     * @return
     */
     int insertAd(Ad ad);

    /**
     * 根据广告ID删除广告
     * @param ad_id
     * @return
     */
     int deleteAd(@Param("ad_id")Long ad_id);

    /**
     * 根据广告ID更新广告信息
     * @param ad
     * @return
     */
     int updateAd(Ad ad);



}
