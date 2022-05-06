package com.datealive.controller;

import com.datealive.common.PageResult;
import com.datealive.common.Result;
import com.datealive.entity.dto.BigUserDetail;
import com.datealive.entity.pojo.Goods;
import com.datealive.service.GoodsService;
import com.datealive.service.TreeHoldService;
import com.datealive.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName: GoodsController
 * @Description: TODO
 * @author: datealive
 * @date: 2021/5/4  14:19
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;


    @GetMapping("/getGoodsByPage/{currentPage}")
    public PageResult getGoodsByPage(@PathVariable("currentPage") Integer currentPage){
        PageResult<Goods> allGoods = goodsService.getAllGoods(currentPage);
        return allGoods;
    }



    @PostMapping("/insertGoods")
    public Result insertGoods(@RequestBody Goods goods){
        if (goodsService.insertGoods(goods)) {
            return Result.success("插入成功");
        }
        return Result.error("插入失败");
    }


    @DeleteMapping("/deleteGoodsByGoodsId/{goodsId}")
    public Result deleteGoodsByGoodsId(@PathVariable("goodsId") Long GoodsId){
        if (goodsService.deleteGoodsByGoodsId(GoodsId)) {
            return Result.success("根据商品ID删除成功");
        }
        return Result.error("根据商品ID删除失败");
    }

    /**
     * 其他接口待实现
     */
    @PutMapping("/updateGoodsSetCntSub/{goodsID}")
    public Result updateGoodsSetCntSub(@PathVariable("goodsID")Long goodsID){
        boolean b = goodsService.descGoodsCnt(goodsID);
        if(b){
            return Result.success("商品兑换成功");
        }else {
            return Result.error("商品兑换失败，余量不足");
        }
    }













}
