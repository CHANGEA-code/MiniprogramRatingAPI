package com.datealive.entity.dto;

import com.datealive.entity.pojo.Resume;
import com.datealive.entity.pojo.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName: BigUserDetail
 * @Description: TODO
 * @author: datealive
 * @date: 2021/5/15  23:06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BigUserDetail implements Serializable {


    private User user;

    private ResumeDto resumeDto;

}
