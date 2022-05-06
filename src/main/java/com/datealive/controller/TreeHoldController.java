package com.datealive.controller;

import com.datealive.aop.PermissionCheck;
import com.datealive.common.PageResult;
import com.datealive.common.Result;
import com.datealive.entity.dto.TreeHoldDto;
import com.datealive.entity.pojo.TreeHold;
import com.datealive.service.Impl.TreeHoldServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/treeHold")
public class TreeHoldController {

    @Autowired
    private TreeHoldServiceImpl treeHoldService;


    @GetMapping("/getTreeHoldByID/{ID}")
    public Result getTreeHoldByID(@PathVariable("ID")Long id) {
        TreeHoldDto treeHoldByID = treeHoldService.getTreeHoldByID(id);
        if (treeHoldByID != null) {
            return Result.success("根据ID查询动态成功", treeHoldByID);
        } else {
            return Result.error("根据ID查询动态失败");
        }
    }

    @GetMapping("/getTreeHoldListByUserID/{ID}")
    public Result getTreeHoldListByUserID(@PathVariable("ID")Long userID){
        List<TreeHoldDto> treeHoldListByUserID = treeHoldService.getTreeHoldListByUserID(userID);
        if(treeHoldListByUserID.isEmpty()){
            return Result.error("你暂时还没有发布任何动态");
        }else {
            return Result.success("该用户动态查询成功",treeHoldListByUserID);
        }
    }

    @GetMapping("/getTreeHoldList/{pageNum}")
    public PageResult getTreeHoldList(@PathVariable("pageNum")Integer pageNum){
        PageResult<TreeHoldDto> treeHoldList = treeHoldService.getTreeHoldList(pageNum);
        return treeHoldList;
    }

    @PermissionCheck
    @PutMapping("/updateTreeHoldContent/{ID}/{content}")
    public Result updateTreeHoldContent(@PathVariable("ID")Long id,@PathVariable("content")String content) {
        boolean aBoolean = treeHoldService.updateTreeHoldContent(id, content);
        if (aBoolean) {
            return Result.success("修改动态内容成功");
        } else {
            return Result.error("修改动态内容失败");
        }
    }

    @PermissionCheck
    @PostMapping("/createTreeHold")
    public Result createTreeHold(@RequestBody TreeHold treeHold) {
        boolean treeHold1 = treeHoldService.createTreeHold(treeHold);
        if (treeHold1) {
            return Result.success("发布动态成功");
        } else {
            return Result.error("发布动态失败");
        }
    }

    @PermissionCheck
    @DeleteMapping("/deleteTreeHold/{ID}")
    public Result deleteTreeHold(@PathVariable("ID")Long ID) {
        boolean aBoolean = treeHoldService.deleteTreeHold(ID);
        if (aBoolean) {
            return Result.success("删除动态成功");
        } else {
            return Result.error("删除动态失败");
        }
    }
}