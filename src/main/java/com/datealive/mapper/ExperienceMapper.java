package com.datealive.mapper;

import com.datealive.entity.pojo.Experience;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Repository
@Mapper
public interface ExperienceMapper {
    List<Experience> getExperienceListByResumeID(@Param("resumeID")Long resumeID);

    boolean createExperience(@RequestBody Experience experience);

    boolean deleteExperience(@Param("expID")Long experienceID);

    boolean updateExperience(@RequestBody Experience experience);

    Long getExperienceListByResumeIDCnt(@Param("resumeID")Long resumeID);

    boolean deleteExperienceByUserID(@Param("userID")Long userID);
}
