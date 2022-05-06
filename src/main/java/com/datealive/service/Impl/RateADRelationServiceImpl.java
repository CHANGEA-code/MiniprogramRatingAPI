package com.datealive.service.Impl;

import com.datealive.common.PageResult;
import com.datealive.common.ResultCode;
import com.datealive.common.StaticFinalCode;
import com.datealive.entity.pojo.RateAdRelation;
import com.datealive.mapper.RateADRelationMapper;
import com.datealive.service.RateADRelationService;
import com.github.pagehelper.PageHelper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class RateADRelationServiceImpl implements RateADRelationService {
    @Autowired
    private RateADRelationMapper rateADRelationMapper;

    @Override
    public boolean createRelation(RateAdRelation rateAdRelation) {
        return rateADRelationMapper.createRelation(rateAdRelation);
    }

    @Override
    public boolean deleteRelationByRelationID(Long relationID) {
        return rateADRelationMapper.deleteRelationByRelationID(relationID);
    }

    @Override
    public RateAdRelation getRateAdRelationByRelationID(Long relationID) {
        return rateADRelationMapper.getRateAdRelationByRelationID(relationID);
    }

    @Override
    public PageResult<RateAdRelation> getRelationByRateID(Long rateID,Integer pageNum) {
        Long totalCnt=rateADRelationMapper.getRelationByRateIDCnt(rateID);
        int totalPage=(int)Math.ceil(totalCnt*1.0/ StaticFinalCode.PageSize_10);
        PageHelper.startPage(pageNum, StaticFinalCode.PageSize_10);
        List<RateAdRelation> relationByRateID = rateADRelationMapper.getRelationByRateID(rateID);
        if(relationByRateID.isEmpty()){
            return new PageResult<RateAdRelation>(ResultCode.Not_Found,totalPage,pageNum,null);
        }else {
            return new PageResult<RateAdRelation>(ResultCode.Success,totalPage,pageNum,relationByRateID);
        }

    }

    @Override
    public PageResult<RateAdRelation> getRelationByADID(Long adID, Integer pageNum) {
        Long totalCnt=rateADRelationMapper.getRelationByADIDCnt(adID);
        int totalPage=(int)Math.ceil(totalCnt*1.0/ StaticFinalCode.PageSize_10);
        PageHelper.startPage(pageNum,StaticFinalCode.PageSize_10);
        List<RateAdRelation> relationByADID = rateADRelationMapper.getRelationByADID(adID);
        if(relationByADID.isEmpty()){
            return new PageResult<RateAdRelation>(ResultCode.Not_Found,totalPage,pageNum,null);
        }else {
            return new PageResult<RateAdRelation>(ResultCode.Success,totalPage,pageNum,relationByADID);
        }
    }
}
