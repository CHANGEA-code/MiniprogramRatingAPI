package com.datealive.controller;

import com.datealive.aop.PermissionCheck;
import com.datealive.common.PageResult;
import com.datealive.common.Result;
import com.datealive.entity.pojo.Love;
import com.datealive.service.Impl.LoveServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/love")
public class LoveController {
    @Autowired
    private LoveServiceImpl loveService;

    @GetMapping("/getLoveListByUserID/{userID}/{pageNum}")
    public PageResult getLoveListByUserID(@PathVariable("userID")Long userID,@PathVariable("pageNum")Integer pageNum){
        return loveService.getLoveListByUserID(userID,pageNum);
    }

    @GetMapping("/getLoveListByUserIDWithoutPageNum/{userID}")
    public Result getLoveListByUserIDWithoutPageNum(@PathVariable("userID")Long userID){
        return loveService.getLoveListByUserIDWithouPageNum(userID);
    }


    @GetMapping("/getLoveListByRateID/{rateID}/{pageNum}")
    public PageResult getLoveListByRateID(@PathVariable("rateID")Long rateID,@PathVariable("pageNum")Integer pageNum){
        return loveService.getLoveListByRateID(rateID,pageNum);
    }

    @GetMapping("/getLoveByLoveID/{loveID}")
    public Result getLoveByLoveID(@PathVariable("loveID")Long loveID) {
        Love loveByLoveID = loveService.getLoveByLoveID(loveID);
        if (loveByLoveID != null) {
            return Result.success("根据ID查询收藏成功", loveByLoveID);
        } else {
            return Result.error("根据ID查询收藏失败");
        }
    }

    @PermissionCheck
    @PostMapping("/createLove")
    public Result createLove(@RequestBody Love love) {
        boolean love1 = loveService.createLove(love);
        if (love1) {
            return Result.success("收藏成功",love.getLoveID());
        } else {
            return Result.error("收藏失败");
        }
    }

    @PermissionCheck
    @DeleteMapping("/deleteLove/{loveID}")
    public Result deleteLove(@PathVariable("loveID")Long loveID) {
        boolean aBoolean = loveService.deleteLove(loveID);
        if (aBoolean) {
            return Result.success("取消收藏成功");
        } else {
            return Result.error("取消收藏失败");
        }
    }


}