package com.datealive.controller;

import com.datealive.aop.PermissionCheck;
import com.datealive.common.Result;
import com.datealive.entity.dto.PointsUpdByPointsId;
import com.datealive.entity.dto.PointsUpdByUserId;
import com.datealive.entity.pojo.Points;
import com.datealive.service.PointsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName: PointsController
 * @Description: TODO
 * @author: datealive
 * @date: 2021/5/3  20:29
 */
@RestController
@RequestMapping("/points")
@Slf4j
public class PointsController {

    @Autowired
    private PointsService pointsService;


    @GetMapping("/getPointsByPointsId/{PointsId}")
    public Result getPointsByPointsId(@PathVariable("PointsId")Long PointsId){
        Points pointsByPointsId = pointsService.getPointsByPointsId(PointsId);
        if(pointsByPointsId!=null){
            return Result.success("获取成功",pointsByPointsId);
        }
        return Result.error("获取失败");
    }



    @GetMapping("/getPointsByUserId/{UserId}")
    public Result getPointsByUserId(@PathVariable("UserId")Long UserId){
        Points pointsByUserId = pointsService.getPointsByUserId(UserId);
        if(pointsByUserId!=null){
            return Result.success("获取成功",pointsByUserId);
        }
        return Result.error("获取失败");
    }


    @PermissionCheck
    @PostMapping("/insertPoints")
    public Result insertPoints(@RequestBody Points points){
        if (pointsService.insertPoints(points)) {
            return Result.success("插入成功");
        }
        return Result.error("插入失败");
    }

    @PermissionCheck
    @DeleteMapping("/deletePointsByPointsId/{postId}")
    public Result deletePointsByPointsId(@PathVariable("postId") Long PointsId){
        if (pointsService.deletePointsByPointsId(PointsId)) {
            return Result.success("根据积分ID删除成功");
        }
        return Result.error("根据积分ID删除失败");
    }

    @PermissionCheck
    @DeleteMapping("/deletePointsByUserId/{userId}")
    public Result deletePointsByUserId(@PathVariable("userId") Long UserId){

        if (pointsService.deletePointsByUserId(UserId)) {
            return Result.success("根据用户ID删除成功");
        }
        return Result.error("根据用户ID删除失败");
    }


    @PermissionCheck
    @PutMapping("/changePointsByPointsId")
    public Result changePointsByPointsId(@RequestBody PointsUpdByPointsId pointsUpdByPointsId){
        if (pointsService.changePointsByPointsId(pointsUpdByPointsId.getPointsId(),
                                            pointsUpdByPointsId.getPointsCnt())) {
            return Result.success("根据积分ID修改积分成功",pointsUpdByPointsId.getPointsCnt());
        }
        return Result.error("根据积分ID修改积分失败");

    }

    @PermissionCheck
    @PutMapping("/changePointsByUserId")
    public Result changePointsByUserId(@RequestBody PointsUpdByUserId pointsUpdByUserId){
        if (pointsService.changePointsByUserId(pointsUpdByUserId.getGzhuUserId(),
                pointsUpdByUserId.getPointsCnt())) {
            return Result.success("根据用户ID修改积分成功",pointsUpdByUserId.getPointsCnt());
        }
        return Result.error("根据用户ID修改积分失败");

    }





}
