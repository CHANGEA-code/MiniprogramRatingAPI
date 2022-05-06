package com.datealive.entity.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * resumeName 表示简历上用户的真实姓名
 *
 * resumeSchool表示用户所在的学校
 *
 * resumeMajor表示简历的专业
 *
 * resumeGender表示简历上用户的性别
 *
 * resumeIntroduction表示用户的自我介绍
 *
 * resumePower表示用户的技术栈
 *
 * resumeGrade表示用户的年级
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Resume implements Serializable {

    private Long id;
    private String name;
    private String school;
    private String major;
    private Integer gender;
    private String introduction;
    private String power;
    private String grade;

}
