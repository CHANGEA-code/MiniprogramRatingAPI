package com.datealive.service;


import com.datealive.entity.vo.User.UserGradeObj;
import com.datealive.entity.vo.User.UserListObj;

import java.util.List;

public interface UserListObjService {
    public List<UserGradeObj> getUserGradeTop10();

}
