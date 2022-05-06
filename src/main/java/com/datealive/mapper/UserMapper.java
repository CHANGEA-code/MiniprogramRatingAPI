package com.datealive.mapper;

import com.datealive.common.PageResult;
import com.datealive.entity.pojo.Experience;
import com.datealive.entity.pojo.User;
import com.datealive.entity.vo.User.UpdateUserDetail;
import com.datealive.entity.vo.User.UserListObj;
import com.datealive.service.UserService;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.swing.text.StyledEditorKit;
import java.util.List;

@Repository
@Mapper
public interface UserMapper {
    /**
     * 根据用户ID获得用户的所有详细信息，
     * @param userID 参数是用户的ID号
     * @return 返回值是用户详细的信息
     */
    User getUserDetailByID(@Param("ID")Long userID);

    /**
     * 根据前端传递的一套信息，对用户详细信息进行修改
     * @param user 前端传递的信息
     * @return
     */
    boolean updateUserDetailMessageByID(@RequestBody UpdateUserDetail user);

    /**
     * 根据用户ID修改用户的头像
     * @param avatar 头像
     * @param ID ID号，唯一识别user
     * @return
     */
    boolean updateUserAvatar(@Param("avatar")String avatar,@Param("ID")Long ID);

    /**
     * 根据用户昵称找到用户昵称相同的列表
     *
     * @param nickname 昵称
     * @return 返回值是一个user列表
     */
    List<UserListObj> getUserListByNickname(@Param("nickname")String nickname);

    /**
     * 注册用户，但补实现简历的绑定
     * @param user
     * @return
     */
    boolean insertUser(@RequestBody User user);


    /**
     * 根据openID查询用户
     * @param userOpenId
     * @return
     */
    User findUserByOpenId(@Param("openID")String userOpenId);

    String findOpenIdByUserId(@Param("ID")Long userID);


    /**
     * 根据用户ID和email修改用户的邮箱
     * @param email 修改后的邮箱
     * @param userID 用户ID
     * @return
     */
    boolean updateEmail(@Param("email") String email,@Param("ID") Long userID);


    /**
     * 根据用户ID和用户电话号码修改用户电话号码
     * @param phone
     * @param userID
     * @return
     */
    boolean updatePhone(@Param("phone")String phone,@Param("ID")Long userID);

    /**
     * 根据用户ID和用户新的用户名修改用户的用户名
     * @param username 用户名
     * @param userID 用户ID
     * @return
     */
    boolean updateUsername(@Param("username")String username,@Param("ID")Long userID);

    /**
     * 根据用户ID和新创建的简历ID创建一对一连接
     * @param userID 用户ID
     * @param resumeID 用户简历ID
     * @return
     */
    boolean connectUserToResumeByID(@Param("userID")Long userID,@Param("resumeID")Long resumeID);

    /**
     * 根据userID获得user的详细信息
     * @param userID
     * @return
     */
    UserListObj getUserListObjByID(@Param("userID")Long userID);

    /**
     * 根据userID使userIndentify为1
     * @param userID
     * @return
     */
    boolean updateIndentity(@Param("id")Long userID);


}
