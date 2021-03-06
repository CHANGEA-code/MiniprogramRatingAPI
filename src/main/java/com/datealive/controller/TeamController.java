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
            return Result.success("????????????????????????",teamCntByRateID);
        }else{
            return Result.error("??????????????????");
        }
    }

    @GetMapping("/getTeamByUserID/{userID}")
    public Result getTeamByUserID(@PathVariable("userID")Long userID){
        List<Team> teamListByUserID = teamService.getTeamListByUserID(userID);
        if(teamListByUserID.isEmpty()){
            return Result.error("??????????????????????????????");
        }else{
            return Result.success("????????????",teamListByUserID);
        }
    }

    @GetMapping("/getTeamListByTeamID/{teamID}")
    public Result getTeamListByTeamID(@PathVariable("teamID")Long teamID) {
        Team teamListByTeamID = teamService.getTeamListByTeamID(teamID);
        if (teamListByTeamID != null) {
            return Result.success("????????????ID:" + teamID + "????????????", teamListByTeamID);
        } else {
            return Result.error("????????????ID???" + teamID + "????????????");
        }
    }

    @GetMapping("/getTeamDetailByTeamID/{teamID}")
    public Result getTeamDetailByTeamID(@PathVariable("teamID")Long teamID){
        TeamUser teamAllUser = teamService.getTeamAllUser(teamID);
        if(teamAllUser!=null){
            return Result.success("????????????",teamAllUser);
        }else {
            return Result.error("????????????");
        }
    }

    @GetMapping("/getTeammateByTeammateID/{teammateID}")
    public Result getTeammateByTeammateID(@PathVariable("teammateID")Long teammateID) {
        Teammate teammateByTeammateID = teammateService.getTeammateByTeammateID(teammateID);
        if (teammateByTeammateID != null) {
            return Result.success("??????teammateID" + teammateID + "????????????????????????", teammateByTeammateID);
        } else {
            return Result.error("??????teammateID" + teammateID + "????????????????????????");
        }
    }


    @GetMapping("/getTeammateByTeamID/{teamID}/{pageNum}")
    public PageResult getTeammateByTeamID(@PathVariable("teamID")Long teamID,@PathVariable("pageNum")Integer pageNum){
        return teammateService.getTeammateByTeamID(teamID,pageNum);
    }

    @GetMapping("/getAllTeamCnt")
    public Result getAllTeamCnt(){
        return Result.success("????????????",teamService.getAllTeamCnt());
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
            return Result.success("??????????????????");
        } else {
            return Result.error("??????????????????");
        }
    }

    //admin
    @DeleteMapping("/deleteTeamByRateID/{rateID}")
    public Result deleteTeamByRateID(@PathVariable("rateID")Long rateID) {
        boolean b = teamService.deleteTeamByRateID(rateID);
        if (b) {
            return Result.success("????????????ID?????????????????????");
        } else {
            return Result.error("????????????ID,??????????????????");
        }
    }

    @PermissionCheck
    @DeleteMapping("/exitTeamByUserIDAndTeamID/{teamID}/{userID}")
    public Result exitTeamByUserIDAndTeamID(@PathVariable("teamID")Long teamID,@PathVariable("userID")Long userID) {
        boolean b = teammateService.exitTeamByUserIDAndTeamID(teamID, userID);
        if (b) {
            return Result.success("??????id???"+userID+"??????????????????");
        } else {
            return Result.error("??????id???"+userID+"????????????????????????");
        }
    }

    @PermissionCheck
    @DeleteMapping("/exitTeamByTeammateID/{teammateID}")
    public Result exitTeamByTeammateID(@PathVariable("teammateID")Long teammateID) {
        boolean b = teammateService.exitTeamByTeammateID(teammateID);
        if (b) {
            return Result.success("????????????????????????");
        } else {
            return Result.error("????????????????????????");
        }
    }

    @PermissionCheck
    @PostMapping("/createTeam")
    public Result createTeam(@RequestBody Team team) {
        boolean team1 = teamService.createTeam(team);
        if (team1) {
            return Result.success("??????????????????");
        } else {
            return Result.error("??????????????????");
        }
    }
    @PermissionCheck
    @PostMapping("/joinInTeam")
    public Result joinInTeam(@RequestBody Teammate teammate) {
        boolean b = teammateService.joinInTeam(teammate);
        if (b) {
            return Result.success("????????????????????????");
        } else {
            return Result.error("????????????????????????");
        }
    }

    @PermissionCheck
    @PutMapping("/updateTeam")
    public Result updateTeam(@RequestBody Team team) {
        boolean b = teamService.updateTeam(team);
        if (b) {
            return Result.success("????????????????????????");
        } else {
            return Result.error("????????????????????????");
        }
    }

    @PermissionCheck
    @PostMapping("/JoinTeamByApply")
    public Result JoinTeamByApply(@RequestBody JoinAndMessageDto joinAndMessageDto){
        joinTeamByApply.run(joinAndMessageDto.getJoinTeamDto(),joinAndMessageDto.getTemplateMessage());
        return Result.success("???????????????");
    }

    @PermissionCheck
    @PostMapping("/JoinTeamByInvite")
    public Result JoinTeamByInvite(@RequestBody JoinAndMessageDto joinAndMessageDto){
        joinTeamByInvite.run(joinAndMessageDto.getJoinTeamDto(), joinAndMessageDto.getTemplateMessage());
        return Result.success("???????????????");
    }

    @PermissionCheck
    @PostMapping("/ResponseTeamFromApply")
    public Result ResponseTeamFromApply(@RequestBody ResponseAndMessageDto responseAndMessageDto){
        responseTeamFromApply.run(responseAndMessageDto.getResopnseTeamDto(), responseAndMessageDto.getTemplateMessage());
        return Result.success("????????????");
    }

    @PermissionCheck
    @PostMapping("/ResponseTeamFronInvite")
    public Result ResponseTeamFronInvite(@RequestBody ResponseAndMessageDto responseAndMessageDto){
        responseTeamFronInvite.run(responseAndMessageDto.getResopnseTeamDto(), responseAndMessageDto.getTemplateMessage());
        return Result.success("????????????");
    }





}