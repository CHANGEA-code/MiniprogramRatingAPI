package com.datealive.controller;


import com.datealive.aop.PermissionCheck;
import com.datealive.common.PageResult;
import com.datealive.common.Result;
import com.datealive.entity.dto.ExperienceListAndUserID;
import com.datealive.entity.pojo.Experience;
import com.datealive.service.Impl.ExperienceServiceImpl;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/experience")
public class ExperienceController {

    @Autowired
    private ExperienceServiceImpl experienceService;

    @GetMapping("/getExperienceListByResumeID/{resumeID}/{pageNum}")
    public PageResult getExperienceListByResumeID(@PathVariable("resumeID")Long resumeID,@PathVariable("pageNum")Integer pageNum){
        PageResult<Experience> experienceListByResumeID = experienceService.getExperienceListByResumeID(resumeID, pageNum);
        return experienceListByResumeID;
    }

    @PermissionCheck
    @PostMapping("/createExperience")
    public Result createExperience(@RequestBody Experience experience) {
        boolean experience1 = experienceService.createExperience(experience);
        if (experience1) {
            return Result.success("项目经验创建成功");
        } else {
            return Result.error("项目经验创建失败");
        }
    }


    @PermissionCheck
    @PostMapping("/insertExperience")
    public Result createExperienceList(@RequestBody ExperienceListAndUserID experienceListAndUserID){
        boolean b = experienceService.updateExperienceByList(experienceListAndUserID);
        if(b){
            return Result.success("修改成功",b);
        }else{
            return Result.error("修改失败");
        }
    }

    @PermissionCheck
    @DeleteMapping("/deleteExperience/{experienceID}")
    public Result deleteExperience(@PathVariable("experienceID")Long experienceID) {
        boolean aBoolean = experienceService.deleteExperience(experienceID);
        if (aBoolean) {
            return Result.success("项目经历删除成功");
        } else {
            return Result.error("项目经历删除失败");
        }
    }

    @PermissionCheck
    @PutMapping("/updateExperience")
    public Result updateExperience(@RequestBody Experience experience) {
        boolean aBoolean = experienceService.updateExperience(experience);
        if (aBoolean) {
            return Result.success("项目经历更新成功");
        } else {
            return Result.error("项目经历更新失败");
        }
    }


    @PutMapping("/updateExperienceList")
    public Result updateExperienceList(@RequestBody ExperienceListAndUserID experienceListAndUserID){
        boolean b = experienceService.updateExperienceByList(experienceListAndUserID);
        if(b){
            return Result.success("修改成功");
        }else {
            return Result.error("修改失败");
        }

    }

}