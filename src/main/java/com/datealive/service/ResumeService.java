package com.datealive.service;

import com.datealive.common.PageResult;
import com.datealive.entity.dto.ResumeDto;
import com.datealive.entity.pojo.Resume;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ResumeService {

    PageResult<Resume> getAllResume(Integer pageNum);

    boolean setConnectionBetweenResumeAndExperience(@Param("resID")Long resID, @Param("expID")Long expID);

    Long createResume(@RequestBody Resume resume);

    boolean updateResume(@RequestBody Resume resume, HttpServletRequest request);

    Resume getResumeByID(@Param("ID")Long ID);

    ResumeDto getResumeDtoByResumeId(Long ID);
}
