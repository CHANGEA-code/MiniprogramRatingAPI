package com.datealive;

import com.datealive.common.PageResult;
import com.datealive.entity.dto.BigUserDetail;
import com.datealive.entity.pojo.*;
import com.datealive.entity.vo.Rate.RateList;
import com.datealive.entity.vo.Rate.RateType;
import com.datealive.entity.vo.User.UserGradeObj;
import com.datealive.mapper.ManagerMapper;
import com.datealive.mapper.ResumeMapper;
import com.datealive.mapper.TeammateMapper;
import com.datealive.mapper.UserMapper;
import com.datealive.service.ExperienceService;
import com.datealive.service.Impl.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;
import java.util.List;
import java.util.Random;

@SpringBootTest
class GzhuteamApplicationTests {

    @Autowired
    ManagerServiceImpl managerService;
    @Autowired
    UserServiceImpl userService;
    @Autowired
    ResumeServiceImpl resumeService;
    @Autowired
    TreeHoldServiceImpl treeHoldService;
    @Autowired
    ExperienceServiceImpl experienceService;
    @Autowired
    RateServiceImpl rateService;
    @Autowired
    LoveServiceImpl loveService;
    @Autowired
    TeamServiceImpl teamService;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ResumeMapper resumeMapper;
    @Autowired
    private PointsServiceImpl pointsService;

    @Autowired
    UserMapper userMapper;


    @Autowired
    TeammateMapper teammateMapper;
    @Test
    void contextLoads() {
        Resume resumeByID = resumeService.getResumeByID(new Long(3));
        resumeByID.setPower("saddjkasldasld");
        int resume = resumeMapper.createResume(resumeByID);
        System.out.println(resumeByID.getId());

    }

}
