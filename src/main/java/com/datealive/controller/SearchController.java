package com.datealive.controller;

import com.datealive.common.PageResult;
import com.datealive.common.Result;
import com.datealive.entity.dto.RateSearchListDto;
import com.datealive.entity.pojo.Team;
import com.datealive.entity.vo.User.UserListObj;
import com.datealive.service.Impl.RateServiceImpl;
import com.datealive.service.Impl.TeamServiceImpl;
import com.datealive.service.Impl.TeammateServiceImpl;
import com.datealive.service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {
    @Autowired
    private RateServiceImpl rateService;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private TeamServiceImpl teamService;


    @GetMapping("/getUserListByNickName/{nickname}")
    public Result getUserListByNickName(@PathVariable("nickname")String nickname){
        List<UserListObj> userListByNickname = userService.getUserListByNickname(nickname);

        if(userListByNickname.isEmpty()){
            return Result.error("没有需要搜索的用户信息");
        }else
        {
            return Result.success("搜索成功",userListByNickname);
        }
    }

    @GetMapping("/getTeamListByTeamName/{teamName}")
    public Result getTeamListByTeamName(@PathVariable("teamName")String teamName){
        List<Team> teamListByTeamName = teamService.getTeamListByTeamName(teamName);
        if(teamListByTeamName.isEmpty()){
            return Result.error("没有需要搜索的队伍信息");
        }else{
            return Result.success("搜索成功",teamListByTeamName);
        }
    }

    @GetMapping("/getRateListByRateTitle/{title}")
    public Result getRateListByRateTitle(@PathVariable("title")String title){
        List<RateSearchListDto> rateListByTitle = rateService.getRateListByTitle(title);
        if(rateListByTitle.isEmpty()){
            return Result.error("没有需要搜索的比赛信息");
        }else
        {
            return Result.success("搜索成功",rateListByTitle);
        }
    }
}
