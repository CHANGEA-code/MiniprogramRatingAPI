package com.datealive.service;

import com.datealive.common.PageResult;
import com.datealive.entity.pojo.Goods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName: GoodsService
 * @Description: TODO
 * @author: datealive
 * @date: 2021/5/3  22:25
 */
public interface GoodsService {

    /**
     * 获取所有的积分商品 使用分页插件
     * @return
     */
    public PageResult<Goods> getAllGoods(Integer pageNum);


    /**
     * 获取所有的商品数量
     * @return
     */
    public Long getAllGoodsCnt();


    /**
     * 根据商品ID获取商品
     * @param goods_id
     * @return
     */
    public Goods getGoodsByGoodsId(Long goods_id);

    /**
     * 插入商品
     * @param goods
     * @return
     */
    public boolean insertGoods(Goods goods);

    /**
     * 根据商品ID删除商品
     * @param goods_id
     * @return
     */
    public boolean deleteGoodsByGoodsId(Long goods_id);

    /**
     * 根据商品ID修改商品的价格 即所需积分数量
     * @param goods_id
     * @param goods_price
     * @return
     */
    public boolean updateGoodsByGoodsIdAndGoodsPrice(Long goods_id,Long goods_price);

    /**
     * 根据商品id修改商品数量
     * @param goods_id
     * @param goods_cnt
     * @return
     */
    public boolean updateGoodsByGoodsIdAndGoodsCnt(Long goods_id,Long goods_cnt);

    /**
     * 根据货物的ID减少货物的数量
     * @param goodsID
     * @return
     */
    public boolean descGoodsCnt(Long goodsID);

}
