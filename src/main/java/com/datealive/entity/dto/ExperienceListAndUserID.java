package com.datealive.entity.dto;

import com.datealive.entity.pojo.Experience;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExperienceListAndUserID {

    private List<Experience> experiences;

    private Long userID;

}
