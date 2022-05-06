package com.datealive.mapper;

import com.datealive.entity.pojo.Rate;
import com.datealive.entity.pojo.RateAdRelation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Mapper
public interface RateADRelationMapper {
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
    List<RateAdRelation> getRelationByRateID(@Param("rateID")Long rateID);

    /**
     * 根据广告的ID获得与该广告绑定的比赛的关系
     * @param adID
     * @return
     */
    List<RateAdRelation> getRelationByADID(@Param("ADID")Long adID);

    /**
     * 根据比赛的ID号查询该比赛下有多少个广告
     * @param rateID
     * @return
     */
    Long getRelationByRateIDCnt(@Param("rateID")Long rateID);

    /**
     * 根据广告号看该广告捆绑了多少个比赛
     * @param ADID
     * @return
     */
    Long getRelationByADIDCnt(@Param("ADID")Long ADID);


}
