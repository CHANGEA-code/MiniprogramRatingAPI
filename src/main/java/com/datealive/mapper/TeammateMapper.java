package com.datealive.mapper;

import com.datealive.entity.pojo.Team;
import com.datealive.entity.pojo.Teammate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Mapper
public interface TeammateMapper {
    /**
     * 根据队员的ID号退出
     * @param teammateID
     * @return
     */
    boolean exitTeamByTeammateID(@Param("teammateID")Long teammateID);

    /**
     * 根据队员自身的userID和队伍ID，唯一确定一个teammate，并且实现退出
     * @param teamID
     * @param userID
     * @return
     */
    boolean exitTeamByUserIDAndTeamID(@Param("teamID")Long teamID,@Param("userID")Long userID);

    /**
     * 加入队伍
     * @param teammate
     * @return
     */
    boolean joinInTeam(@RequestBody Teammate teammate);

    /**
     * 获得队员信息
     * @param teammateID
     * @return
     */
    Teammate getTeammateByTeammateID(@Param("teammateID")Long teammateID);

    /**
     * 根据userID，获得当前用户以队员的身份下有多少个队伍
     * @param userID
     * @return
     */
    List<Teammate> getTeammateByUserID(@Param("userID")Long userID);

    /**
     * 根据队伍得ID获得所有的队员
     * @param teamID
     * @return
     */
    List<Teammate> getTeammateByTeamID(@Param("teamID")Long teamID);


    /**
     * 获得根据该user的所有队员数量
     * @param userID
     * @return
     */
    Long getTeammateCntByUserID(@Param("userID")Long userID);

    /**
     * 获得该user的所有队长数量
     * @param teamID
     * @return
     */
    Long getTeammateCntByTeamID(@Param("teamID")Long teamID);

    List<Team> getTeamByUserID(@Param("userID")Long userID);

}
