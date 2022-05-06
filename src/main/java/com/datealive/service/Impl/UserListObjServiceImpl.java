package com.datealive.service.Impl;

import com.datealive.entity.vo.User.UserGradeObj;
import com.datealive.entity.vo.User.UserListObj;
import com.datealive.mapper.UserListObjMapper;
import com.datealive.service.UserListObjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.QSort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserListObjServiceImpl implements UserListObjService {

    @Autowired
    private UserListObjMapper userListObjMapper;
    @Override
    public List<UserGradeObj> getUserGradeTop10() {
        //是否需要添加的标记
        Boolean flag=true;

        List<UserGradeObj> userGradeByTeammate = userListObjMapper.getUserGradeByTeammate();
        List<UserGradeObj> userGradeByTeam = userListObjMapper.getUserGradeByTeam();
        List<UserGradeObj> addList=new ArrayList<>();
        for(UserGradeObj user : userGradeByTeam){
            for(UserGradeObj user2:userGradeByTeammate){
                if(user.getUserID().equals(user2.getUserID()))
                {
                    flag=false;
                    user2.setRateCnt(user2.getRateCnt()+user.getRateCnt());
                }

            }
            if(flag)
            {
                addList.add(user);
            }
            flag=true;
        }

        for (UserGradeObj userGradeObj : addList) {
            userGradeByTeammate.add(userGradeObj);
        }
        //排序算法
        List<UserGradeObj> sort = Sort(userGradeByTeammate);
        return sort;
    }

    private List<UserGradeObj> Sort(List<UserGradeObj> waitsort){

        List<UserGradeObj> sortedList=new ArrayList<>();
        final int sortedNumber=10;
        int cnt=0;
        int max=-1;
        int index=0;
        while (cnt<sortedNumber){
            for(int i=0;i<waitsort.size();i++){
                //找最值，添加后并且移除
                if(max<waitsort.get(i).getRateCnt()){
                    max= (int) waitsort.get(i).getRateCnt();
                    index=i;
                }

            }
            sortedList.add(waitsort.get(index));
            waitsort.remove(index);
            max=-1;
            cnt++;
        }
        List<UserGradeObj> ret=new ArrayList<>();
//        for (int i=9;i>=0;i--){
//            ret.add(sortedList.get(i));
//        }
        return sortedList;
    }
}
