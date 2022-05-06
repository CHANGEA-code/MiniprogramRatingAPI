package com.datealive.entity.vo.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 该类是用于在查询用户列表的时候，避免查询过多的字段所采用的类型
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserListObj implements Serializable {
    /**
     * 只传username和avatar 性别
     */
    private Long userId;

    private String nickname;

    private String avatar;

    private Integer gender;

}
