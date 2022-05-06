package com.datealive.entity.vo.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserGradeObj {
    private Long userID;

    private String nickname;

    private String avatar;

    private Integer gender;

    private long rateCnt;
}
