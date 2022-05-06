package com.datealive.controller;

import com.datealive.aop.PermissionCheck;
import com.datealive.common.PageResult;
import com.datealive.common.Result;
import com.datealive.entity.dto.ResumeAndUserID;
import com.datealive.entity.dto.ResumeDto;
import com.datealive.entity.pojo.Resume;
import com.datealive.service.Impl.ResumeServiceImpl;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/resume")
public class ResumeController {

    @Autowired
    private ResumeServiceImpl resumeService;

    @GetMapping("/getAllResume/{pageNum}")
    public PageResult<Resume> getAllResume(@PathVariable("pageNum")Integer pageNum){
        return resumeService.getAllResume(pageNum);
    }

    @GetMapping("/getResumeByID/{ID}")
    public Result getResumeByID(@PathVariable("ID")Long ID) {
        Resume resumeByID = resumeService.getResumeByID(ID);
        if (resumeByID != null) {
            return Result.success("根据ID查询简历成功", resumeByID);
        } else {
            return Result.error("根据ID查询简历失败");
        }
    }

    @GetMapping("/getResumeDtoByResumeId/{ID}")
    public Result getResumeDtoByResumeId(@PathVariable("ID")Long ID) {
        ResumeDto resumeDtoByResumeId = resumeService.getResumeDtoByResumeId(ID);
        if (resumeDtoByResumeId != null) {
            return Result.success("根据ID查询简历并传输成功", resumeDtoByResumeId);
        } else {
            return Result.error("根据ID查询简历失败");
        }
    }

    @PermissionCheck
    @PutMapping("/updateResume")
    public Result updateResume(@RequestBody Resume resume, HttpServletRequest request) {
        boolean aBoolean = resumeService.updateResume(resume,request);
        if (aBoolean) {
            return Result.success("成功更新简历", resume);
        } else {
            return Result.error("更新简历失败");
        }
    }

    @PermissionCheck
    @PutMapping("/setConnectionBetweenResumeAndExperience/{resID}/{expID}")
    public Result setConnectionBetweenResumeAndExperience(@PathVariable("resID")Long resID,@PathVariable("expID")Long expID) {
        boolean aBoolean = resumeService.setConnectionBetweenResumeAndExperience(resID, expID);
        if (aBoolean) {
            return Result.success("用户与简历连接成功");
        } else {
            return Result.error("用户与简历连接失败");
        }
    }





}