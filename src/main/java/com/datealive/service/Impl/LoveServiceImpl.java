package com.datealive.service.Impl;

import com.datealive.common.PageResult;
import com.datealive.common.Result;
import com.datealive.common.ResultCode;
import com.datealive.common.StaticFinalCode;
import com.datealive.entity.dto.LoveAllListDto;
import com.datealive.entity.dto.LoveRate;
import com.datealive.entity.pojo.Love;
import com.datealive.entity.pojo.Rate;
import com.datealive.mapper.LoveMapper;
import com.datealive.mapper.RateMapper;
import com.datealive.mapper.TeamMapper;
import com.datealive.service.LoveService;
import com.github.pagehelper.PageHelper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Data
@Service
public class LoveServiceImpl implements LoveService {
    @Autowired
    private LoveMapper loveMapper;

    @Autowired
    private RateMapper rateMapper;

    @Autowired
    private TeamMapper teamMapper;

    @Override
    public PageResult<Love> getLoveListByUserID(Long userID, Integer pageNum) {
        Long allLoveListByUserIDCnt=loveMapper.getLoveByUserIDByCnt(userID);
        int totalPage=(int)Math.ceil(allLoveListByUserIDCnt*1.0/ StaticFinalCode.PageSize_10);
        PageHelper.startPage(pageNum,StaticFinalCode.PageSize_10);
        List<Love> loveListByUserID = loveMapper.getLoveListByUserID(userID);
        if(loveListByUserID.isEmpty()){
            return new PageResult<Love>(ResultCode.Not_Found,totalPage,pageNum,null);
        }else {
            return new PageResult<Love>(ResultCode.Success,totalPage,pageNum,loveListByUserID);
        }
    }



    @Override
    public PageResult<Love> getLoveListByRateID(Long rateID,Integer pageNum) {
        Long allLoveListByRateIDCnt=loveMapper.getLoveByRateIDByCnt(rateID);
        int totalPage=(int)Math.ceil(allLoveListByRateIDCnt*1.0/StaticFinalCode.PageSize_10);
        PageHelper.startPage(pageNum,StaticFinalCode.PageSize_10);
        List<Love> loveListByRateID = loveMapper.getLoveListByRateID(rateID);
        if(loveListByRateID.isEmpty()){
            return new PageResult<Love>(ResultCode.Not_Found,totalPage,pageNum,null);
        }else{
            return new PageResult<Love>(ResultCode.Success,totalPage,pageNum,loveListByRateID);
        }
    }

    @Override
    public Result getLoveListByUserIDWithouPageNum(Long userID) {
        List<Love> loveListByUserID = loveMapper.getLoveListByUserID(userID);
        List<LoveAllListDto> ret=new ArrayList<>();
        for (Love love : loveListByUserID) {
            Long rateID = love.getRateID();
            Rate rateDetailByID = rateMapper.getRateDetailByID(rateID);

            LoveAllListDto loveAllListDto=new LoveAllListDto();
            loveAllListDto.setLoveID(love.getLoveID());
            loveAllListDto.setRateBannerUrl(rateDetailByID.getBannerUrl());
            loveAllListDto.setRateHost(rateDetailByID.getHost());

            Long rateCntByRateID = teamMapper.getRateCntByRateID(rateID);
            loveAllListDto.setRateCnt(rateCntByRateID);
            loveAllListDto.setRateTitle(rateDetailByID.getTitle());
            loveAllListDto.setRateID(rateDetailByID.getId());
            loveAllListDto.setRateIntroduction(rateDetailByID.getIntroduction());
            ret.add(loveAllListDto);

        }
        if(loveListByUserID.isEmpty()){
            return Result.error("用户无收藏比赛");
        }else {
            return Result.success("用户收藏比赛搜索成功",ret);
        }
    }

    @Override
    public Love getLoveByLoveID(Long loveID) {
        return loveMapper.getLoveByLoveID(loveID);
    }

    @Override
    public boolean createLove(Love love) {
        return loveMapper.createLove(love);
    }

    @Override
    public boolean deleteLove(Long loveID) {
        return loveMapper.deleteLove(loveID);
    }
}
