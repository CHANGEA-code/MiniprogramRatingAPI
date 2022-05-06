package com.datealive.entity.dto;

import com.datealive.entity.pojo.Experience;
import com.datealive.entity.pojo.Resume;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: ResumeDto
 * @Description: TODO
 * @author: datealive
 * @date: 2021/5/16  13:18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResumeDto implements Serializable {

    private Resume resume;

    private List<Experience> experienceList;
}
