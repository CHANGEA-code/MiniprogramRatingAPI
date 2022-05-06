package com.datealive.mapper;

import com.datealive.entity.pojo.Resume;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Mapper
@Repository
public interface ResumeMapper {
    List<Resume> getAllResume();

    boolean setConnectionBetweenUserAndExperience(@Param("resID")Long resID, @Param("expID")Long expID);

    int createResume(@RequestBody Resume resume);

    boolean updateResume(@RequestBody Resume resume);

    Resume getResumeByID(@Param("ID")Long ID);

    Long getAllResumeCnt();


}
