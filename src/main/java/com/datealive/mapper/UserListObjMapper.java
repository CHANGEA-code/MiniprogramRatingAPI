package com.datealive.mapper;

import com.datealive.entity.vo.User.UserGradeObj;
import com.datealive.entity.vo.User.UserListObj;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserListObjMapper {

    /**
     * 查询该用户当队长的次数
     * @return
     */
    public List<UserGradeObj> getUserGradeByTeam();

    /**
     * 查询该用户当队员的次数
     * @return
     */
    public List<UserGradeObj> getUserGradeByTeammate();

    /**
     * 根据用户ID查询用户当队长的个数
     * @param userID
     * @return
     */
    UserGradeObj getUserAndRateCntByTeam(@Param("userID")Long userID);

    /**
     * 根据用户ID查询当队员的个数
     * @param userID
     * @return
     */
    UserGradeObj getUserAndRateCntByTeammate(@Param("userID")Long userID);

}
