package com.datealive.service.Impl;

import com.datealive.entity.pojo.Manager;
import com.datealive.mapper.ManagerMapper;
import com.datealive.service.ManagerService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @ClassName: ManagerServiceImpl
 * @Description: TODO
 * @author: datealive
 * @date: 2021/4/25  17:56
 */
@Data
@Service
public class ManagerServiceImpl implements ManagerService, UserDetailsService {

    @Autowired
    private ManagerMapper managerMapper;

    @Override
    public Manager getManagerByNameAndPassword(String manager_username,String manager_password) {
        return managerMapper.getManagerByNameAndPassword(manager_username,manager_password);
    }

    @Override
    public UserDetails loadUserByUsername(String manager_username) throws UsernameNotFoundException {
        Manager managerByName = managerMapper.getManagerByName(manager_username);
        if (managerByName==null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        return managerByName;
    }



}
