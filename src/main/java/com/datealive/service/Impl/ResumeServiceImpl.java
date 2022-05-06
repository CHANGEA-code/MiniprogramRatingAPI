package com.datealive.service.Impl;

import com.datealive.common.PageResult;
import com.datealive.common.ResultCode;
import com.datealive.common.StaticFinalCode;
import com.datealive.entity.dto.ResumeDto;
import com.datealive.entity.pojo.Experience;
import com.datealive.entity.pojo.Resume;
import com.datealive.entity.pojo.User;
import com.datealive.mapper.ExperienceMapper;
import com.datealive.mapper.ResumeMapper;
import com.datealive.mapper.UserMapper;
import com.datealive.service.RedisService;
import com.datealive.service.ResumeService;
import com.datealive.utils.RedisUtil;
import com.github.pagehelper.PageHelper;
import lombok.Data;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
@Data
public class ResumeServiceImpl implements ResumeService {
    @Autowired
    private ResumeMapper resumeMapper;

    @Autowired
    private ExperienceMapper experienceMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private RedisService redisService;

    @Override
    public PageResult<Resume> getAllResume(Integer pageNum) {

        Long totalCnt=resumeMapper.getAllResumeCnt();
        int totalPage=(int)Math.ceil(1.0*totalCnt/ StaticFinalCode.pageSize_5);
        PageHelper.startPage(pageNum,StaticFinalCode.pageSize_5);
        List<Resume> allResume = resumeMapper.getAllResume();

        if (!allResume.isEmpty())
        {
            return new PageResult<Resume>(ResultCode.Not_Found,totalPage,pageNum,null);
        }
        else
        {
            return new PageResult<Resume>(ResultCode.Success,totalPage,pageNum,allResume);
        }
    }

    @Override
    public boolean setConnectionBetweenResumeAndExperience(@Param("resID") Long resID, @Param("expID") Long expID) {
        return resumeMapper.setConnectionBetweenUserAndExperience(resID,expID);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createResume(@RequestBody Resume resume) {
        resumeMapper.createResume(resume);
        return resume.getId();
    }

    @Override
    public boolean updateResume(@RequestBody Resume resume, HttpServletRequest request) {
        boolean b = resumeMapper.updateResume(resume);
        if (b) {
            String userId = request.getHeader("UserId");
            if (userId!=null) {
                redisService.hdelKeyAndItem(StaticFinalCode.UserAndResumeKey,userId);
                return true;
            }

        }
        return false;
    }

    @Override
    public Resume getResumeByID(@Param("ID") Long ID) {
        return resumeMapper.getResumeByID(ID);
    }

    @Override
    public ResumeDto getResumeDtoByResumeId(Long ID) {
        ResumeDto resumeDto = new ResumeDto();
        //先根据简历ID获取简历信息  然后再获取竞赛经历信息
        Resume resume = resumeMapper.getResumeByID(ID);
        if (resume!=null) {
            resumeDto.setResume(resume);
            List<Experience> experienceListByResumeID = experienceMapper.getExperienceListByResumeID(resume.getId());
            resumeDto.setExperienceList(experienceListByResumeID);
            return resumeDto;
        }
        return null;
    }
}
