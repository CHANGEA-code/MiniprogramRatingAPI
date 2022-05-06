package com.datealive.entity.dto;

import com.datealive.entity.pojo.Resume;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResumeAndUserID {
    private Resume resume;

    private Long userID;
}
