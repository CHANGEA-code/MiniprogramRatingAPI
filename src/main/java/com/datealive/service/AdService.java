package com.datealive.service;

import com.datealive.common.PageResult;
import com.datealive.entity.dto.AdRateDto;
import com.datealive.entity.pojo.Ad;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName: AdService
 * @Description: TODO
 * @author: datealive
 * @date: 2021/5/4  21:36
 */
public interface AdService {

    /**
     * 广告 这里的广告和比赛关系表 可以不用写service层 直接在广告业务层中配合事务即可（到时候比赛的service层 可能也需要修改下）
     *
     * 给比赛插入广告：
     * 1.在创建比赛的时候插入广告
     * 插入 广告 需要获得 插入的广告信息 以及当前的比赛ID 和 创建完后的广告ID 进行 关系表的维护
     * 2.在创建比赛完成后插入广告
     * 插入 广告 需要获得 插入的广告信息 以及当前的比赛ID 和创建完后的广告ID 进行 关系表的维护
     * 与第一条不同的是  这里的 比赛是已经持久化在数据库中有一段时间了 第一条中 创建比赛的时候 需要返回 比赛ID
     *
     *
     * 删除广告
     * 1.可以根据广告ID 删除
     * 2.也可以根据比赛ID 删除
     * 这里需要注意的是  如果 一个 比赛 删除了 需要维护下 这个广告表和 广告比赛关系表
     *
     * 修改广告
     * 1.根据广告ID修改即可
     *
     * 查询广告
     * 根据比赛ID查询广告即可
     */


    public PageResult<Ad> getAllAdMapperByPage(Integer pageNum);

    /**
     * 获取所有广告的数量
     * @return
     */
    public Long getAllAdCnt();

    /**
     * 根据广告ID获取广告内容
     * @param ad_id
     * @return
     */
    public Ad getAdByAdId(Long ad_id);

    /**
     * 根据比赛ID获取广告内容
     * @param rate_id
     * @return
     */
    public List<Ad> getAdByRateId(Long rate_id);

    /**
     * 插入广告
     * @param adRateDto
     * @return
     */
    public boolean insertAd(AdRateDto adRateDto);

    /**
     * 根据广告ID删除广告
     * @param ad_id
     * @return
     */
    public boolean deleteAdByAdId(Long ad_id);

    /**
     * 根据比赛ID删除广告
     * @param rate_id
     * @return
     */
    public boolean deleteAdByRateId(Long rate_id);

    /**
     * 根据广告ID更新广告信息
     * @param ad
     * @return
     */
    public boolean updateAdByAdId(Ad ad);






}
