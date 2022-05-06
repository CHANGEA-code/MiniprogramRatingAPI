package com.datealive.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName: LoginInfo
 * @Description: TODO
 * @author: datealive
 * @date: 2021/4/25  21:43
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ManagerLoginInfo implements Serializable {

    private String username;
    private String password;
}
