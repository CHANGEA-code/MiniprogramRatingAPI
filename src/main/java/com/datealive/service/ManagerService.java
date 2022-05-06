package com.datealive.service;

import com.datealive.entity.pojo.Manager;

/**
 * @ClassName: ManagerService
 * @Description: TODO
 * @author: datealive
 * @date: 2021/4/25  17:54
 */
public interface ManagerService {
    /**
     * 根据名称和密码获取管理员信息
     * @param manager_username
     * @param manager_password
     * @return
     */
    Manager getManagerByNameAndPassword(String manager_username,String manager_password);
}
