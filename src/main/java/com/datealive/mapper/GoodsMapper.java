package com.datealive.mapper;

import com.datealive.entity.pojo.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;

/**
 * @ClassName: GoodsMapper
 * @Description: TODO
 * @author: datealive
 * @date: 2021/5/3  21:31
 */
@Mapper
public interface GoodsMapper {


    /**
     * 获取所有的积分商品 使用分页插件
     * @return
     */
     List<Goods> getAllGoods();


    /**
     * 获取所有的商品数量
     * @return
     */
     Long getAllGoodsCnt();


    /**
     * 根据商品ID获取商品
     * @param goods_id
     * @return
     */
     Goods getGoodsByGoodsId(@Param("goods_id")Long goods_id);

    /**
     * 插入商品
     * @param goods
     * @return
     */
     int insertGoods(Goods goods);

    /**
     * 根据商品ID删除商品
     * @param goods_id
     * @return
     */
     int deleteGoodsByGoodsId(@Param("goods_id")Long goods_id);

    /**
     * 根据商品ID修改商品的价格 即所需积分数量
     * @param goods_id
     * @param goods_price
     * @return
     */
     int updateGoodsByGoodsIdAndGoodsPrice(@Param("goods_id")Long goods_id,@Param("goods_price")Long goods_price);

    /**
     * 根据商品id修改商品数量
     * @param goods_id
     * @param goods_cnt
     * @return
     */
     int updateGoodsByGoodsIdAndGoodsCnt(@Param("goods_id")Long goods_id,@Param("goods_cnt")Long goods_cnt);



}
