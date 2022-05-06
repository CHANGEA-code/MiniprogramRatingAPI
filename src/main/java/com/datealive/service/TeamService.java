package com.datealive.service;

import com.datealive.common.PageResult;
import com.datealive.entity.dto.TeamRate;
import com.datealive.entity.dto.TeamUser;
import com.datealive.entity.pojo.Team;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface TeamService {
    /**
     *
     * @param team
     * @return
     */
    boolean createTeam(@RequestBody Team team);

    /**
     * 根据队伍ID删除队伍
     * @param teamID
     * @return
     */
    boolean deleteTeamByTeamID(@Param("teamID")Long teamID);

    /**
     * 比赛结束，根据比赛ID删除该比赛的所欲队伍
     * @param rateID 比赛ID
     * @return
     */
    boolean deleteTeamByRateID(@Param("rateID")Long rateID);

    /**
     * 修改队伍信息,只能修改名字，数量，还有简介
     * @param team
     * @return
     */
    boolean updateTeam(@RequestBody Team team);

    /**
     * 查看当前状态下自己做队长的队伍有哪几个
     * @param captainID 用户ID
     * @return
     */
    PageResult<Team> getTeamListByCaptainID(@Param("captainID")Long captainID, Integer pageNum);

    /**
     * 查看当前比赛参赛的队伍
     * @param rateID
     * @return
     */
    PageResult<Team> getTeamListByRateID(@Param("rateID")Long rateID,Integer pageNum);

    /**
     * 根据队伍ID查找队伍
     * @param teamID
     * @return
     */
    Team getTeamListByTeamID(@Param("teamID")Long teamID);

    /**
     * 根据队伍名字获得队伍列表
     * @param teamName
     * @return
     */
    List<Team> getTeamListByTeamName(@Param("teamName")String teamName);

    /**
     * 根据userID获得该用户的所有队伍
     * @param userID
     * @return
     */
    List<Team> getTeamListByUserID(@Param("userID")Long userID);

    /**
     * 根据比赛ID获得参加比赛的队伍数量
     * @param rateID
     * @return
     */
    Long getTeamCntByRateID(@Param("rateID")Long rateID);

    /**
     * 获得所有的队伍的数量
     * @return
     */
    long getAllTeamCnt();

    /**
     * 根据teamID获得队伍详细信息
     * @param teamID
     * @return
     */
    TeamUser getTeamAllUser(@Param("teamID")Long teamID);

    /**
     * 获取所有的比赛与队伍信息的结合集合
     * @return
     */
    PageResult<TeamRate> getAllTeam(@Param("pageNum")Integer pageNum);
}
