package com.datealive.service;

import com.datealive.common.PageResult;
import com.datealive.entity.pojo.RateAdRelation;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface RateADRelationService {
    /**
     * 添加一个关系
     * @param rateAdRelation
     * @return
     */
    boolean createRelation(@RequestBody RateAdRelation rateAdRelation);

    /**
     * 根据关系ID删除该广告与比赛之间的关系
     * @param relationID
     * @return
     */
    boolean deleteRelationByRelationID(@Param("ID")Long relationID);

    /**
     * 根据广告与比赛之间的关系获得该关系
     * @param relationID
     * @return
     */
    RateAdRelation getRateAdRelationByRelationID(@Param("relationID")Long relationID);

    /**
     * 根据比赛ID获得与该比赛绑定的广告的关系
     * @param rateID
     * @return
     */
    PageResult<RateAdRelation> getRelationByRateID(@Param("rateID")Long rateID, Integer pageNum);

    /**
     * 根据广告的ID获得与该广告绑定的比赛的关系
     * @param adID
     * @return
     */
    PageResult<RateAdRelation> getRelationByADID(@Param("ADID")Long adID,Integer pageNum);

}
