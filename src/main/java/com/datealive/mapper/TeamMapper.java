package com.datealive.mapper;

import com.datealive.entity.dto.TeamRate;
import com.datealive.entity.pojo.Team;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Mapper
public interface TeamMapper {
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
    List<Team> getTeamListByCaptainID(@Param("captainID")Long captainID);

    /**
     * 查看当前比赛参赛的队伍
     * @param rateID
     * @return
     */
    List<Team> getTeamListByRateID(@Param("rateID")Long rateID);

    /**
     * 根据队伍ID查找队伍
     * @param teamID
     * @return
     */
    Team getTeamListByTeamID(@Param("teamID")Long teamID);

    /**
     * 根据用户ID获得用户加入的队伍的个数
     * @param captainID
     * @return
     */
    Long getTeamListByUserIDCnt(@Param("captainID")Long captainID);

    /**
     * 根据比赛ID获得队伍列表的数目
     * @param rateID
     * @return
     */
    Long getTeamListByRateIDCnt(@Param("rateID")Long rateID);

    /**
     * 根据队伍名称搜索队伍列表
     * @param teamName
     * @return
     */
    List<Team> getTeamListByTeamName(@Param("teamName")String teamName);

    long getAllTeamCnt();

    /**
     * 根据比赛ID获得该比赛的参赛次数
     * @param rateID
     * @return
     */
    Long getRateCntByRateID(@Param("id")Long rateID);

    /**
     * 获取所有的队伍
     * @return
     */
    List<TeamRate> getAllTeam();

    /**
     * 该team人数+1
     * @param id
     * @return
     */
    int increaseTeamCnt(@Param("id")Long id);

    /**
     * 该team人数-1
     * @param id
     * @return
     */
    int descreaseTeamCnt(@Param("id")Long id);
}
