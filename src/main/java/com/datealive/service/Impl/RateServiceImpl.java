package com.datealive.service.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.datealive.common.PageResult;
import com.datealive.common.ResultCode;
import com.datealive.common.StaticFinalCode;
import com.datealive.entity.dto.RateSearchListDto;
import com.datealive.entity.dto.RateTopList;
import com.datealive.entity.pojo.Rate;
import com.datealive.entity.vo.Rate.RateList;
import com.datealive.entity.vo.Rate.RateType;
import com.datealive.mapper.RateMapper;
import com.datealive.mapper.RateTypeMapper;
import com.datealive.service.RateService;
import com.datealive.service.RedisService;
import com.datealive.utils.RedisUtil;
import com.github.pagehelper.PageHelper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Service
@Data
public class RateServiceImpl implements RateService {
    @Autowired
    private RateMapper rateMapper;
    @Autowired
    private RateTypeMapper rateTypeMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private RedisService redisService;

    @Override
    public boolean createRate(Rate rate) {
        return rateMapper.createRate(rate);
    }

    @Override
    public PageResult<RateList> getRateList(Integer pageNum) {
        Long rateCnt = rateMapper.getRateCnt();
        int totalPage=(int)Math.ceil(1.0*rateCnt/ StaticFinalCode.pageSize_5);
        PageHelper.startPage(pageNum,StaticFinalCode.pageSize_5);
        List<RateList> rateList = rateMapper.getRateList();

        if(rateList.isEmpty()){
            return new PageResult<RateList>(ResultCode.Not_Found,totalPage,pageNum,null);
        }else {
            return new PageResult<RateList>(ResultCode.Success,totalPage,pageNum,rateList);
        }
    }

    @Override
    public List<RateTopList> getTop10List() {
        //在redis中获取前top10的数据id  热门比赛top 缓存 在redis中1个小时
        // 1个小时失效后查询获取
        List<RateTopList> lists = new ArrayList<>();
        if (redisUtil.hasKey(StaticFinalCode.Rate_TOP_List_Key)) {

            Object topRateList = redisService.getTopRateList();
            String toplistStr = JSONObject.toJSONString(topRateList);
            lists = JSON.parseArray(toplistStr, RateTopList.class);
            return lists;
        }else {

            Set<Object> objectSet = redisService.zgetTopRateViewList(0, 9);
//            Iterator<Object> iterator = objectSet.iterator();
//            while (iterator.hasNext()) {
//                Object next = iterator.next();
//            }
            for (Object id : objectSet) {
                    RateTopList rateTop = rateMapper.getTopRateByRateID(Long.valueOf(String.valueOf(id)));
                    lists.add(rateTop);
            }
            redisService.setTopRateList(lists, StaticFinalCode.Rate_Top_TIMEOUT);
            return lists;
        }
    }

    @Override
    public Rate getRateDetailByID(Long ID) {
        //这里 查询比赛详情信息 先从redis中拿 使用hashmap结构 比赛列表分页不做，只做比赛详情缓存
        //redis中拿不到，就在mysql中拿 拿了之后再缓存到redis中
        //比赛热点信息使用zset结构 无论从mysql中拿还是redis中拿到比赛详情信息 这个对应字段都要自增

        //如果缓存中有这个比赛信息 则从缓存中拿
        if (redisUtil.hHasKey(StaticFinalCode.Rate_DEATIL_BYID_Key, String.valueOf(ID))) {
            Rate rate = redisService.hgetRateDetailByIdKey(String.valueOf(ID));
            if (rate!=null) {
                redisService.zincrRateViewByOne(StaticFinalCode.RATE_TOP_10_INCY_key, String.valueOf(ID));
            }
            return rate;
        }else {
            //如果redis中拿不到
            //下面这条代码 需要再mysql和redis中能找到比赛的条件下才能添加
            Rate rate = rateMapper.getRateDetailByID(ID);
            if (rate!=null) {
                redisService.zincrRateViewByOne(StaticFinalCode.RATE_TOP_10_INCY_key, String.valueOf(ID));
                redisService.hsetRateDetailByIdKey(String.valueOf(rate.getId()),rate);
            }
            return rate;
        }

    }

    @Override
    public List<RateSearchListDto> getRateListByMultiCondition(Long typeID, Integer isTeam, String rateGrade) {
        List<RateSearchListDto> rateListByMultiCondition = rateMapper.getRateListByMultiCondition(rateGrade, typeID, isTeam);
        return rateListByMultiCondition;
    }

    @Override
    public boolean deleteRateByID(Long ID) {
        boolean deleteRateByID = rateMapper.deleteRateByID(ID);
        if (deleteRateByID) {
            //如果删除成功 就需要删除redis缓存的比赛详情信息的map 和 比赛top 的zset
            redisService.hdelKeyAndItem(StaticFinalCode.Rate_DEATIL_BYID_Key,String.valueOf(ID));
            redisService.zremoveRateView(StaticFinalCode.RATE_TOP_10_INCY_key, String.valueOf(ID));
        }
        return deleteRateByID;
    }

    @Override
    public boolean updateRateByID(Rate rate) {
        if (rateMapper.updateRateByID(rate)) {
            redisService.hdelKeyAndItem(StaticFinalCode.Rate_DEATIL_BYID_Key,String.valueOf(rate.getId()));
            return true;
        }
        return false;
    }

    @Override
    public boolean createRateType(RateType rateType) {
        return rateTypeMapper.createRateType(rateType);
    }

    @Override
    public boolean deleteRateType(Long ID) {
        return rateTypeMapper.deleteRateType(ID);
    }

    @Override
    public boolean updateRateType(RateType rateType) {
        return rateTypeMapper.updateRateType(rateType);
    }

    @Override
    public List<RateType> getTypeList() {
        return rateTypeMapper.getTypeList();
    }

    @Override
    public RateType getTypeByRateTypeID(Long ID) {
        return rateTypeMapper.getTypeByRateTypeID(ID);
    }


    @Override
    public List<RateSearchListDto> getRateListByTitle(String title) {
        return rateMapper.getRateListByTitle(title);
    }

}
