package com.datealive.mapper;

import com.datealive.entity.pojo.Manager;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @ClassName: ManagerMapper
 * @Description: TODO
 * @author: datealive
 * @date: 2021/4/25  17:44
 */
@Repository
@Mapper
public interface ManagerMapper {

    /**
     * 根据名字获取管理员
     * @param manager_username
     * @return
     */
    Manager getManagerByName(@Param("manager_username")String manager_username);

    /**
     * 根据名称和密码获取管理员信息
     * @param manager_username
     * @param manager_password
     * @return
     */
    Manager getManagerByNameAndPassword(@Param("manager_username")String manager_username,@Param("manager_password")String manager_password);


}
