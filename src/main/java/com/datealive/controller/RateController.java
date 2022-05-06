package com.datealive.controller;

import com.datealive.aop.PermissionCheck;
import com.datealive.common.PageResult;
import com.datealive.common.Result;
import com.datealive.entity.dto.MultiCondtion;
import com.datealive.entity.dto.RateSearchListDto;
import com.datealive.entity.dto.RateTopList;
import com.datealive.entity.pojo.AdAndRate;
import com.datealive.entity.pojo.Rate;
import com.datealive.entity.pojo.RateAdRelation;
import com.datealive.entity.vo.Rate.RateType;
import com.datealive.service.Impl.RateADRelationServiceImpl;
import com.datealive.service.Impl.RateServiceImpl;
import lombok.Data;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rate")
@Data
public class RateController {
    @Autowired
    private RateServiceImpl rateService;

    @Autowired
    private RateADRelationServiceImpl rateADRelationService;

    @GetMapping("/getRateList/{pageNum}")
    public PageResult getRateList(@PathVariable("pageNum") Integer pageNum){
        return rateService.getRateList(pageNum);
    }

    @GetMapping("/getRateDetailByID/{ID}")
    public Result getRateDetailByID(@PathVariable("ID")Long ID){
        Rate rateDetailByID = rateService.getRateDetailByID(ID);
        if(rateDetailByID!=null){
            return Result.success("根据比赛"+ID+"获得比赛详细信息成功",rateDetailByID);
        }
        else {
            return Result.error("根据比赛"+ID+"获得比赛详细信息失败");
        }
    }

    @GetMapping("/getTypeList")
    public Result getTypeList(){
        List<RateType> typeList = rateService.getTypeList();
        if (typeList.isEmpty()) {
            return Result.error("数据为空");
        }else{
            return Result.success("获取数据成功",typeList);
        }
    }

    @GetMapping("/getTypeByRateTypeID/{ID}")
    public Result getTypeByRateTypeID(@PathVariable("ID")Long ID) {
        RateType typeByRateTypeID = rateService.getTypeByRateTypeID(ID);
        if (typeByRateTypeID != null) {
            return Result.success("根据类型ID:" + ID + ",获得比赛类型成功", typeByRateTypeID);
        } else {
            return Result.error("根据类型ID:" + ID + ",获得比赛类型失败");
        }
    }

    @GetMapping("/getRateAdRelationByRelationID/{relationID}")
    public Result getRateAdRelationByRelationID(@PathVariable Long relationID) {
        RateAdRelation rateAdRelationByRelationID = rateADRelationService.getRateAdRelationByRelationID(relationID);
        if (rateAdRelationByRelationID != null) {
            return Result.success("根据ID：" + relationID + "获得广告与比赛关系成功", rateAdRelationByRelationID);
        } else {
            return Result.error("根据ID：" + relationID + "获得广告与比赛关系失败");
        }
    }

    @GetMapping("/getTopRate")
    public Result getTopRate(){
        List<RateTopList> top10List = rateService.getTop10List();
        if(top10List.isEmpty()){
            return Result.error("推荐比赛生成失败");
        }else{
            return Result.success("推荐比赛生成成功",top10List);
        }
    }

    @GetMapping("/getRelationByRateID/{rateID}/{pageNum}")
    public PageResult getRelationByRateID(@PathVariable("rateID") Long rateID,@PathVariable("pageNum")Integer pageNum){
        return rateADRelationService.getRelationByRateID(rateID,pageNum);
    }

    @GetMapping("/getRelationByADID/{adID}/{pageNum}")
    public PageResult getRelationByADID(@PathVariable("adID")Long adID,@PathVariable("pageNum")Integer pageNum){
        return rateADRelationService.getRelationByADID(adID,pageNum);
    }

    @PostMapping("/getRateListByMulCondition")
    public Result getRateListByMulCondition(@RequestBody MultiCondtion condtion){
        String grade = condtion.getGrade();
        Long typeID = condtion.getTypeID();
        Integer isTeam = condtion.getIsTeam();
        List<RateSearchListDto> rateListByMultiCondition = rateService.getRateListByMultiCondition(typeID, isTeam, grade);
        if(rateListByMultiCondition.isEmpty()){
            return Result.error("没有需要查询的比赛");
        }else {
            return Result.success("查询成功",rateListByMultiCondition);
        }
    }
    //放到admin
    @DeleteMapping("/deleteRateByID/{ID}")
    public Result deleteRateByID(@PathVariable("ID")Long ID) {
        boolean aBoolean = rateService.deleteRateByID(ID);
        if (aBoolean) {
            return Result.success("根据ID:" + ID + "删除比赛成功");
        } else {
            return Result.error("根据ID:" + ID + "删除比赛失败");
        }
    }

    @DeleteMapping("/deleteRateTypeByID/{ID}")
    public Result deleteRateTypeByID(@PathVariable("ID")Long ID) {
        boolean aBoolean = rateService.deleteRateType(ID);
        if (aBoolean) {
            return Result.success("比赛已根据ID=" + ID + "删除成功");
        }
        else {
            return Result.error("比赛根据ID=" + ID + "删除失败");
        }
    }

    @DeleteMapping("/deleteRelationByRelationID/{ID}")
    public Result deleteRelationByRelationID(@PathVariable("ID") Long relationID){
        boolean aBoolean = rateADRelationService.deleteRelationByRelationID(relationID);
        if(aBoolean){
            return Result.success("根据ID："+relationID+"删除关系信息成功");
        }else {
            return Result.error("根据ID："+relationID+"删除关系信息失败");
        }
    }

    @PostMapping("/createRelation")
    public Result createRelation(@RequestBody RateAdRelation rateAdRelation) {
        boolean relation = rateADRelationService.createRelation(rateAdRelation);
        if (relation) {
            return Result.success("比赛与广告的关系创建成功");
        } else {
            return Result.error("比赛与广告的关系创建失败");
        }
    }

    @PostMapping("/createRate")
    public Result createRate(@RequestBody Rate rate) {
        boolean rate1 = rateService.createRate(rate);
        if (rate1) {
            return Result.success("比赛创建成功");
        } else {
            return Result.error("比赛创建失败");
        }
    }

    @PostMapping("/createRateType")
    public Result createRateType(@RequestBody RateType rateType) {
        boolean rateType1 = rateService.createRateType(rateType);
        if (rateType1) {
            return Result.success("比赛类型创建成功");
        } else {
            return Result.error("比赛类型创建失败");
        }
    }

    @PutMapping("/updateRate")
    public Result updateRate(@RequestBody Rate rate) {
        boolean aBoolean = rateService.updateRateByID(rate);
        if (aBoolean) {
            return Result.success("比赛信息修改成功");
        } else {
            return Result.error("比赛信息修改失败");
        }
    }

    @PutMapping("/updateRateType")
    public Result updateRateType(@RequestBody RateType rateType) {
        boolean aBoolean = rateService.updateRateType(rateType);
        if (aBoolean) {
            return Result.success("比赛类型信息修改成功");
        } else {
            return Result.error("比赛信息修改失败");
        }
    }


}