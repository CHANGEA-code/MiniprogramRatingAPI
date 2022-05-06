package com.datealive.service.Impl;

import com.datealive.common.PageResult;
import com.datealive.common.ResultCode;
import com.datealive.common.StaticFinalCode;
import com.datealive.entity.dto.ExperienceListAndUserID;
import com.datealive.entity.pojo.Experience;
import com.datealive.mapper.ExperienceMapper;
import com.datealive.service.ExperienceService;
import com.datealive.service.RedisService;
import com.github.pagehelper.PageHelper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Data
@Service
public class ExperienceServiceImpl implements ExperienceService {
    @Autowired
    private ExperienceMapper experienceMapper;

    @Autowired
    private RedisService redisService;

    @Override
    public PageResult<Experience> getExperienceListByResumeID(Long resumeID, Integer pageNum) {
        Long totalCnt= experienceMapper.getExperienceListByResumeIDCnt(resumeID);
        int totalPage=(int)Math.ceil(1.0*totalCnt/ StaticFinalCode.pageSize_5);
        PageHelper.startPage(pageNum,StaticFinalCode.pageSize_5);

        List<Experience> experienceListByResumeID = experienceMapper.getExperienceListByResumeID(resumeID);
        if(experienceListByResumeID.isEmpty()) {
            return new PageResult<Experience>(ResultCode.Not_Found, totalPage, pageNum, null);
        }
        else {
            return new PageResult<Experience>(ResultCode.Success, totalPage, pageNum, experienceListByResumeID);
        }
    }

    @Override
    public boolean createExperience(Experience experience) {
        return experienceMapper.createExperience(experience);
    }

    @Override
    public boolean deleteExperience(Long experienceID) {
        return experienceMapper.deleteExperience(experienceID);
    }

    @Override
    public boolean updateExperience(Experience experience) {
        return experienceMapper.updateExperience(experience);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateExperienceByList(ExperienceListAndUserID experienceListAndUserID) {
        List<Experience> experiences=experienceListAndUserID.getExperiences();
        Long userID = experienceListAndUserID.getUserID();
        boolean b = experienceMapper.deleteExperienceByUserID(userID);

        for (Experience experience : experiences) {
            experience.setUserID(userID);
            boolean experience1 = experienceMapper.createExperience(experience);
            b&=experience1;
            if(!b) {
                return false;
            }
        }
        redisService.hdelKeyAndItem(StaticFinalCode.UserAndResumeKey, String.valueOf(userID));
        return b;
    }
}
