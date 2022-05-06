package com.datealive.service;

import com.datealive.common.PageResult;
import com.datealive.entity.dto.BigUserDetail;
import com.datealive.entity.pojo.User;
import com.datealive.entity.vo.User.UpdateUserDetail;
import com.datealive.entity.vo.User.UserGradeObj;
import com.datealive.entity.vo.User.UserListObj;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface UserService {

    List<UserGradeObj> getUserGradeTop10();

    BigUserDetail getBigUserByID(Long userId);

    User getUserByID(@Param("ID") Long ID);

    boolean updateUserDetail(@RequestBody UpdateUserDetail updateUserDetail);

    boolean updateUserAvatar(@Param("avatar")String avatar,@Param("ID")Long ID);

    List<UserListObj> getUserListByNickname(@Param("nickname")String nickname);

    boolean insertUser(@RequestBody User user);

    User findUserByOpenId(String openId);

    String getOpenIdByUserId(Long userID);

    boolean findOpenIdByUserId(Long userID,String openId);

    User getUserDetailByID(@Param("ID") Long userID);

    boolean updateEmail(@Param("email") String email,@Param("ID") Long userID);

    boolean updatePhone(@Param("phone")String phone,@Param("ID")Long userID);

    boolean updateUsername(@Param("username")String username, @Param("ID")Long userID);

    boolean connect(@Param("userID")Long userID,@Param("resumeID")Long resumeID);

    UserGradeObj getUserAndRateCntByUserID(@Param("userID")Long userID);

}
