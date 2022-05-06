package com.datealive.controller;

import com.datealive.aop.PermissionCheck;
import com.datealive.common.PageResult;
import com.datealive.common.Result;
import com.datealive.entity.dto.JoinAndMessageDto;
import com.datealive.entity.dto.ResponseAndMessageDto;
import com.datealive.entity.dto.TeamUser;
import com.datealive.entity.pojo.Team;
import com.datealive.entity.pojo.Teammate;
import com.datealive.service.Impl.TeamServiceImpl;
import com.datealive.service.Impl.TeammateServiceImpl;
import com.datealive.service.Impl.team.Request.JoinTeamByApply;
import com.datealive.service.Impl.team.Request.JoinTeamByInvite;
import com.datealive.service.Impl.team.Request.JoinTeamTemplate;
import com.datealive.service.Impl.team.Response.ResponseTeamFromApply;
import com.datealive.service.Impl.team.Response.ResponseTeamFronInvite;
import com.datealive.service.Impl.team.Response.ResponseTeamTemplate;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/team")
public class TeamController {
    @Autowired
    private TeamServiceImpl teamService;

    @Autowired
    private TeammateServiceImpl teammateService;

    @Autowired
    private JoinTeamByApply joinTeamByApply;

    @Autowired
    private JoinTeamByInvite joinTeamByInvite;

    @Autowired
    private ResponseTeamFromApply responseTeamFromApply;

    @Autowired
    private ResponseTeamFronInvite responseTeamFronInvite;



    @GetMapping("/getTeamListByUserID/{captainID}/{pageNum}")
    public PageResult getTeamListByUserID(@PathVariable("captainID") Long captainID, @PathVariable("pageNum") Integer pageNum){
        return teamService.getTeamListByCaptainID(captainID,pageNum);
    }

    @GetMapping("/getTeamListByRateID/{rateID}/{pageNum}")
    public PageResult getTeamListByRateID(@PathVariable("rateID")Long rateID,@PathVariable("pageNum")Integer pageNum) {
        return teamService.getTeamListByRateID(rateID,pageNum);
    }

    @GetMapping("/getTeamCntByRateID/{rateID}")
    public Result getTeamCntByRateID(@PathVariable("rateID")Long rateID){
        Long teamCntByRateID = teamService.getTeamCntByRateID(rateID);
        if(teamCntByRateID!=null){
            return Result.success("队伍数量查询成功",teamCntByRateID);
        }else{
            return Result.error("数量查询出错");
        }
    }

    @GetMapping("/getTeamByUserID/{userID}")
    public Result getTeamByUserID(@PathVariable("userID")Long userID){
        List<Team> teamListByUserID = teamService.getTeamListByUserID(userID);
        if(teamListByUserID.isEmpty()){
            return Result.error("您还没有加入任何队伍");
        }else{
            return Result.success("搜索成功",teamListByUserID);
        }
    }

    @GetMapping("/getTeamListByTeamID/{teamID}")
    public Result getTeamListByTeamID(@PathVariable("teamID")Long teamID) {
        Team teamListByTeamID = teamService.getTeamListByTeamID(teamID);
        if (teamListByTeamID != null) {
            return Result.success("根据队伍ID:" + teamID + "查询成功", teamListByTeamID);
        } else {
            return Result.error("根据队伍ID：" + teamID + "查询失败");
        }
    }

    @GetMapping("/getTeamDetailByTeamID/{teamID}")
    public Result getTeamDetailByTeamID(@PathVariable("teamID")Long teamID){
        TeamUser teamAllUser = teamService.getTeamAllUser(teamID);
        if(teamAllUser!=null){
            return Result.success("查询成功",teamAllUser);
        }else {
            return Result.error("查询失败");
        }
    }

    @GetMapping("/getTeammateByTeammateID/{teammateID}")
    public Result getTeammateByTeammateID(@PathVariable("teammateID")Long teammateID) {
        Teammate teammateByTeammateID = teammateService.getTeammateByTeammateID(teammateID);
        if (teammateByTeammateID != null) {
            return Result.success("根据teammateID" + teammateID + "获得队员信息成功", teammateByTeammateID);
        } else {
            return Result.error("根据teammateID" + teammateID + "获得队员信息失败");
        }
    }


    @GetMapping("/getTeammateByTeamID/{teamID}/{pageNum}")
    public PageResult getTeammateByTeamID(@PathVariable("teamID")Long teamID,@PathVariable("pageNum")Integer pageNum){
        return teammateService.getTeammateByTeamID(teamID,pageNum);
    }

    @GetMapping("/getAllTeamCnt")
    public Result getAllTeamCnt(){
        return Result.success("查询成功",teamService.getAllTeamCnt());
    }

    @GetMapping("/getAllTeam/{pageNum}")
    public PageResult getAllTeam(@PathVariable("pageNum")Integer pageNum){
        return teamService.getAllTeam(pageNum);
    }

    @PermissionCheck
    @DeleteMapping("/deleteTeamByTeamID/{teamID}")
    public Result deleteTeamByTeamID(@PathVariable("teamID") Long teamID) {
        boolean b = teamService.deleteTeamByTeamID(teamID);
        if (b) {
            return Result.success("队伍删除成功");
        } else {
            return Result.error("队伍删除失败");
        }
    }

    //admin
    @DeleteMapping("/deleteTeamByRateID/{rateID}")
    public Result deleteTeamByRateID(@PathVariable("rateID")Long rateID) {
        boolean b = teamService.deleteTeamByRateID(rateID);
        if (b) {
            return Result.success("根据比赛ID，删除队伍成功");
        } else {
            return Result.error("根据比赛ID,删除队伍失败");
        }
    }

    @PermissionCheck
    @DeleteMapping("/exitTeamByUserIDAndTeamID/{teamID}/{userID}")
    public Result exitTeamByUserIDAndTeamID(@PathVariable("teamID")Long teamID,@PathVariable("userID")Long userID) {
        boolean b = teammateService.exitTeamByUserIDAndTeamID(teamID, userID);
        if (b) {
            return Result.success("队员id为"+userID+"退出队伍成功");
        } else {
            return Result.error("队员id为"+userID+"队员退出队伍失败");
        }
    }

    @PermissionCheck
    @DeleteMapping("/exitTeamByTeammateID/{teammateID}")
    public Result exitTeamByTeammateID(@PathVariable("teammateID")Long teammateID) {
        boolean b = teammateService.exitTeamByTeammateID(teammateID);
        if (b) {
            return Result.success("队员退出队伍成功");
        } else {
            return Result.error("队员退出队伍失败");
        }
    }

    @PermissionCheck
    @PostMapping("/createTeam")
    public Result createTeam(@RequestBody Team team) {
        boolean team1 = teamService.createTeam(team);
        if (team1) {
            return Result.success("比赛创建成功");
        } else {
            return Result.error("比赛创建失败");
        }
    }
    @PermissionCheck
    @PostMapping("/joinInTeam")
    public Result joinInTeam(@RequestBody Teammate teammate) {
        boolean b = teammateService.joinInTeam(teammate);
        if (b) {
            return Result.success("队员加入队伍成功");
        } else {
            return Result.error("队员加入队伍失败");
        }
    }

    @PermissionCheck
    @PutMapping("/updateTeam")
    public Result updateTeam(@RequestBody Team team) {
        boolean b = teamService.updateTeam(team);
        if (b) {
            return Result.success("队伍信息修改成功");
        } else {
            return Result.error("队伍信息修改失败");
        }
    }

    @PermissionCheck
    @PostMapping("/JoinTeamByApply")
    public Result JoinTeamByApply(@RequestBody JoinAndMessageDto joinAndMessageDto){
        joinTeamByApply.run(joinAndMessageDto.getJoinTeamDto(),joinAndMessageDto.getTemplateMessage());
        return Result.success("申请审核中");
    }

    @PermissionCheck
    @PostMapping("/JoinTeamByInvite")
    public Result JoinTeamByInvite(@RequestBody JoinAndMessageDto joinAndMessageDto){
        joinTeamByInvite.run(joinAndMessageDto.getJoinTeamDto(), joinAndMessageDto.getTemplateMessage());
        return Result.success("邀请审核中");
    }

    @PermissionCheck
    @PostMapping("/ResponseTeamFromApply")
    public Result ResponseTeamFromApply(@RequestBody ResponseAndMessageDto responseAndMessageDto){
        responseTeamFromApply.run(responseAndMessageDto.getResopnseTeamDto(), responseAndMessageDto.getTemplateMessage());
        return Result.success("响应成功");
    }

    @PermissionCheck
    @PostMapping("/ResponseTeamFronInvite")
    public Result ResponseTeamFronInvite(@RequestBody ResponseAndMessageDto responseAndMessageDto){
        responseTeamFronInvite.run(responseAndMessageDto.getResopnseTeamDto(), responseAndMessageDto.getTemplateMessage());
        return Result.success("响应成功");
    }





}