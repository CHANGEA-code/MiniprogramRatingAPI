package com.datealive.service.Impl;

import com.alibaba.fastjson.JSON;
import com.datealive.common.PageResult;
import com.datealive.common.StaticFinalCode;
import com.datealive.entity.pojo.Goods;
import com.datealive.mapper.GoodsMapper;
import com.datealive.service.GoodsService;
import com.datealive.utils.JacksonUtils;
import com.datealive.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @ClassName: GoodsServiceImpl
 * @Description: TODO
 * @author: datealive
 * @date: 2021/5/3  22:27
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private RedisUtil redisUtil;


    @Override
    public PageResult<Goods> getAllGoods(Integer pageNum) {
        int goodsSize = (int) redisUtil.lGetListSize("AllGoods");
        if(redisUtil.hasKey("AllGoods")){
            if(pageNum<1){
                pageNum=1;
            }
            if(pageNum>goodsSize){
                pageNum= goodsSize;
            }
            //向上取整
            int pageCount = (int) Math.ceil(goodsSize*1.0 / StaticFinalCode.pageSize_5);
            int fromIndex = (pageNum - 1) *  StaticFinalCode.pageSize_5;

            int toIndex = fromIndex +  StaticFinalCode.pageSize_5-1;
            if (toIndex >= goodsSize) {
                toIndex = goodsSize;
            }
            if (pageNum > pageCount + 1) {
                fromIndex = 0;
                toIndex = 0;
            }
            //取出全部的list
            List<Goods> list1 = new ArrayList<>();
            List<Object> list = redisUtil.lGet("AllGoods", fromIndex, toIndex);
            for (int i = 0; i < list.size(); i++) {

                Object o = list.get(i);
                list1.add((Goods) o);
            }
            return new PageResult<Goods>(200,pageCount,pageNum,list1);

        }else{
            List<Goods> allGoods = goodsMapper.getAllGoods();
            for (Goods good : allGoods) {
                redisUtil.lSet("AllGoods",good);

            }
            return new PageResult<>(200,allGoods.size(),pageNum,allGoods);
        }

    }



    @Override
    public Long getAllGoodsCnt() {
        return goodsMapper.getAllGoodsCnt();
    }


    @Override
    public Goods getGoodsByGoodsId(Long goods_id) {
        return goodsMapper.getGoodsByGoodsId(goods_id);
    }



    @Override
    public boolean insertGoods(Goods goods) {
        return goodsMapper.insertGoods(goods) > 0 ? true : false;
    }

    @Override
    public boolean deleteGoodsByGoodsId(Long goods_id) {
        return goodsMapper.deleteGoodsByGoodsId(goods_id) > 0 ? true : false;
    }

    @Override
    public boolean updateGoodsByGoodsIdAndGoodsPrice(Long goods_id, Long goods_price) {
        return goodsMapper.updateGoodsByGoodsIdAndGoodsPrice(goods_id,goods_price) > 0 ? true: false;
    }

    @Override
    public boolean updateGoodsByGoodsIdAndGoodsCnt(Long goods_id, Long goods_cnt) {
        return goodsMapper.updateGoodsByGoodsIdAndGoodsCnt(goods_id,goods_cnt) > 0 ? true:false;
    }

    @Override
    public boolean descGoodsCnt(Long goodsID) {
        Goods goodsByGoodsId = goodsMapper.getGoodsByGoodsId(goodsID);
        Long goodsCnt = goodsByGoodsId.getGoodsCnt();
        if(goodsCnt>0){
            goodsMapper.updateGoodsByGoodsIdAndGoodsCnt(goodsID,goodsCnt-1);
            return true;
        }
        else {
            return false;
        }
    }
}
