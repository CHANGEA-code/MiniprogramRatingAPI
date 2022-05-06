package com.datealive.controller;

import ch.qos.logback.core.pattern.util.RegularEscapeUtil;
import com.datealive.aop.PermissionCheck;
import com.datealive.common.PageResult;
import com.datealive.common.Result;
import com.datealive.entity.dto.BigUserDetail;
import com.datealive.entity.pojo.User;
import com.datealive.entity.vo.User.UpdateUserDetail;
import com.datealive.entity.vo.User.UserGradeObj;
import com.datealive.entity.vo.User.UserListObj;
import com.datealive.service.Impl.UserServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.session.RedisSessionProperties;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/getBigUserByID/{userId}")
    public Result getBigUserByID(@PathVariable("userId") Long userId) {
        BigUserDetail bigUserByID = userService.getBigUserByID(userId);
        if (bigUserByID != null) {
            return Result.success("获取用户的信息与简历信息成功", bigUserByID);
        } else {
            return Result.error("获取用户的信息与简历信息失败");
        }
    }

    @GetMapping("/getUserByID/{ID}")
    public Result getUserByID(@PathVariable("ID")Long ID) {
        User userByID = userService.getUserByID(ID);
        if (userByID != null) {
            return Result.success("根据ID获得用户信息成功", userByID);
        } else {
            return Result.error("根据ID获得用户信息失败");
        }
    }

    @GetMapping("/getUserListByNickname/{nickname}")
    public Result getUserListByNickname(@PathVariable("nickname")String nickname){
        List<UserListObj> userListByNickname = userService.getUserListByNickname(nickname);
        if (userListByNickname.isEmpty()) {
            return Result.error("查询无结果");
        }else{
            return Result.success("查询成功",userListByNickname);
        }
    }

    @GetMapping("/getUserRateCntByUserID/{userID}")
    public Result getUserRateCntByUserID(@PathVariable("userID")Long userID){
        UserGradeObj userAndRateCntByUserID = userService.getUserAndRateCntByUserID(userID);
        if(userAndRateCntByUserID!=null){
            return Result.success("查询成功",userAndRateCntByUserID);
        }else{
            return Result.error("查询失败");

        }
    }

    @GetMapping("/getUserDetailByID/{ID}")
    public Result getUserDetailByID(@PathVariable("ID")Long userID) {
        User userDetailByID = userService.getUserDetailByID(userID);
        if (userDetailByID != null) {
            return Result.success("根据ID查询用户详细信息正常运行", userDetailByID);
        } else {
            return Result.error("根据ID查询用户详细信息出错");
        }
    }

    @GetMapping("/getTop10User")
    public Result getTop10User(){
        List<UserGradeObj> userGradeTop10 = userService.getUserGradeTop10();
        if(userGradeTop10.isEmpty())
        {
            return Result.error("生成前十名失败");
        }else {
            return Result.success("成功生成",userGradeTop10);
        }
    }




    @GetMapping("/findUserByOpenId/{openID}")
    public Result findUserByOpenId(@PathVariable("openID")String openID) {
        User userByOpenId = userService.findUserByOpenId(openID);
        if (userByOpenId != null) {
            return Result.success("根据openID能够查到唯一用户" + userByOpenId);
        } else {
            return Result.error("根据openID不能查到唯一用户");
        }
    }

    @PermissionCheck
    @GetMapping("/getOpenIdByUserId/{userID}")
    public Result getOpenIdByUserId(@PathVariable("userID")Long userID) {
        String openIdByUserId = userService.getOpenIdByUserId(userID);
        if (openIdByUserId==null) {
            return Result.error("openid为空");
        } else {
            return Result.success("openid是", openIdByUserId);
        }
    }


    @PostMapping("/insertUser")
    @PermissionCheck
    public Result insertUser(@RequestBody User user) {
        boolean aBoolean = userService.insertUser(user);
        if (!aBoolean) {
            return Result.error("用户创建失败");
        } else {
            return Result.success("用户创建成功");
        }
    }

    @PermissionCheck
    @PutMapping("/updateEmail/{email}/{userID}")
    public Result updateEmail(@PathVariable("email")String email,@PathVariable("usrID")Long userID) {
        boolean aBoolean = userService.updateEmail(email, userID);
        if (aBoolean) {
            return Result.success("邮箱修改成功");
        } else {
            return Result.error("邮箱修改失败");
        }
    }

    @PermissionCheck
    @PutMapping("/updatePhone/{phone}/{userID}")
    public Result updatePhone(@PathVariable("phone")String phone,@PathVariable("usrID")Long userID) {
        boolean aBoolean = userService.updatePhone(phone, userID);
        if (aBoolean) {
            return Result.success("手机号修改成功");
        } else {
            return Result.error("手机号修改失败");
        }
    }

    @PermissionCheck
    @PutMapping("/updateUsername/{username}/{userID}")
    public Result updateUsername(@PathVariable("username")String username,@PathVariable("userID")Long userID) {
        boolean aBoolean = userService.updateUsername(username, userID);
        if (aBoolean) {
            return Result.success("username修改成功");
        } else {
            return Result.error("username修改失败");
        }
    }

    @PermissionCheck
    @PutMapping("/connect/{userID}/{resumeID}")
    public Result connect(@PathVariable("userID")Long userID,@PathVariable("resumeID")Long resumeID) {
        boolean connect = userService.connect(userID, resumeID);
        if (connect) {
            return Result.success("简历与用户连接成功");
        } else {
            return Result.error("简历与用户连接失败");
        }
    }

    @PermissionCheck
    @PutMapping("/updateUserAvatar/{avatar}/{userID}")
    public Result updateUserAvatar(@PathVariable("avatar")String avatar,@PathVariable("userID")Long userID) {
        boolean aBoolean = userService.updateUserAvatar(avatar, userID);
        if (aBoolean) {
            return Result.success("avatar修改成功");
        } else {
            return Result.error("avatar修改失败");
        }
    }

    @PermissionCheck
    @PutMapping("/updateUserDetail")
    public Result updateUserDetail(@RequestBody UpdateUserDetail updateUserDetail) {
        boolean aBoolean = userService.updateUserDetail(updateUserDetail);
        if (aBoolean) {
            return Result.success("用户详细信息修改成功");
        } else {
            return Result.error("用户详细信息修改失败");
        }
    }





}