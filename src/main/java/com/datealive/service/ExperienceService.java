package com.datealive.service;

import com.datealive.common.PageResult;
import com.datealive.entity.dto.ExperienceListAndUserID;
import com.datealive.entity.pojo.Experience;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ExperienceService {
    PageResult<Experience> getExperienceListByResumeID(@Param("resumeID")Long resumeID, Integer pageNum);

    boolean createExperience(@RequestBody Experience experience);

    boolean deleteExperience(@Param("expID")Long experienceID);

    boolean updateExperience(@RequestBody Experience experience);

    boolean updateExperienceByList(@RequestBody ExperienceListAndUserID experienceListAndUserID);
}
