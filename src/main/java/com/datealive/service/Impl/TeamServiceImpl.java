package com.datealive.service.Impl;

import com.datealive.common.PageResult;
import com.datealive.common.ResultCode;
import com.datealive.common.StaticFinalCode;
import com.datealive.entity.dto.TeamRate;
import com.datealive.entity.dto.TeamUser;
import com.datealive.entity.pojo.Rate;
import com.datealive.entity.pojo.Team;
import com.datealive.entity.pojo.Teammate;
import com.datealive.entity.pojo.User;
import com.datealive.mapper.RateMapper;
import com.datealive.mapper.TeamMapper;
import com.datealive.mapper.TeammateMapper;
import com.datealive.mapper.UserMapper;
import com.datealive.service.TeamService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.events.Event;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {
    @Autowired
    private TeamMapper teamMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RateMapper rateMapper;

    @Autowired
    private TeammateMapper teammateMapper;

    @Override
    public boolean createTeam(Team team) {
        Long rateID = team.getRateID();
        Rate rateDetailByID = rateMapper.getRateDetailByID(rateID);
        Integer countLimited = rateDetailByID.getCountLimited();
        team.setCntLimited(countLimited);
        team.setCnt(1);
        return teamMapper.createTeam(team);
    }

    @Override
    public boolean deleteTeamByTeamID(Long teamID) {
        return teamMapper.deleteTeamByTeamID(teamID);
    }

    @Override
    public boolean deleteTeamByRateID(Long rateID) {
        return teamMapper.deleteTeamByRateID(rateID);
    }

    @Override
    public boolean updateTeam(Team team) {
        return teamMapper.updateTeam(team);
    }

    @Override
    public PageResult<Team> getTeamListByCaptainID(Long captainID, Integer pageNum) {
        Long totalCnt=teamMapper.getTeamListByUserIDCnt(captainID);
        int totalPage=(int)Math.ceil(totalCnt*1.0/ StaticFinalCode.pageSize_5);
        PageHelper.startPage(pageNum,StaticFinalCode.pageSize_5);
        List<Team> teamListByUserID = teamMapper.getTeamListByCaptainID(captainID);

        if(teamListByUserID.isEmpty()){
            return new PageResult<Team>(ResultCode.Not_Found,pageNum,totalPage,null);
        }else {
            return new PageResult<Team>(ResultCode.Success,pageNum,totalPage,teamListByUserID);
        }
    }



    @Override
    public PageResult<Team> getTeamListByRateID(Long rateID,Integer pageNum) {
        Long totalCnt=teamMapper.getTeamListByRateIDCnt(rateID);
        int totalPage=(int)Math.ceil(1.0*totalCnt/StaticFinalCode.PageSize_10);
        PageHelper.startPage(pageNum,StaticFinalCode.PageSize_10);
        List<Team> teamListByRateID = teamMapper.getTeamListByRateID(rateID);
        if(teamListByRateID.isEmpty()){
            return new PageResult<Team>(ResultCode.Not_Found,totalPage,pageNum,null);
        }else {
            return new PageResult<Team>(ResultCode.Success,totalPage,pageNum,teamListByRateID);
        }
    }

    @Override
    public Team getTeamListByTeamID(Long teamID) {
        return teamMapper.getTeamListByTeamID(teamID);
    }

    @Override
    public List<Team> getTeamListByTeamName(String teamName) {
        return teamMapper.getTeamListByTeamName(teamName);
    }

    @Override
    public List<Team> getTeamListByUserID(Long userID) {
        List<Team> teamListByCaptainID = teamMapper.getTeamListByCaptainID(userID);
        List<Team> teamByUserID = teammateMapper.getTeamByUserID(userID);
        for (Team team : teamByUserID) {
            teamListByCaptainID.add(team);
        }
        return teamListByCaptainID;
    }

    @Override
    public Long getTeamCntByRateID(Long rateID) {
        List<Team> teamListByRateID = teamMapper.getTeamListByRateID(rateID);
        int size = teamListByRateID.size();
        return new Long(size);
    }

    @Override
    public long getAllTeamCnt() {
        return teamMapper.getAllTeamCnt();
    }

    @Override
    public TeamUser getTeamAllUser(Long teamID) {
        Team teamListByTeamID = teamMapper.getTeamListByTeamID(teamID);
        TeamUser ret=new TeamUser();
        ret.setTeam(teamListByTeamID);

        Long captainID = teamListByTeamID.getCaptainID();
        User userDetailByID = userMapper.getUserDetailByID(captainID);
        ret.setCaptain(userDetailByID);

        List<Teammate> teammateByTeamID = teammateMapper.getTeammateByTeamID(teamID);
        List<User> userList=new ArrayList<>();
        for (Teammate teammate : teammateByTeamID) {
            Long userID = teammate.getUserID();
            User userDetail = userMapper.getUserDetailByID(userID);
            userList.add(userDetail);
        }
        ret.setTeammate(userList);
        return ret;
    }

    @Override
    public PageResult<TeamRate> getAllTeam(Integer pageNum) {
        long allTeamCnt = teamMapper.getAllTeamCnt();
        int totalPage=(int)Math.ceil(allTeamCnt*1.0/StaticFinalCode.pageSize_5);
        PageHelper.startPage(pageNum,StaticFinalCode.pageSize_5);
        List<TeamRate> allTeam = teamMapper.getAllTeam();

        if(allTeam.isEmpty()){
            return new PageResult<>(ResultCode.Not_Found,totalPage,pageNum,null);
        }else {
            return new PageResult<>(ResultCode.Success,totalPage,pageNum,allTeam);
        }

    }


}
