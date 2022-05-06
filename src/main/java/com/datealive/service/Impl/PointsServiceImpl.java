package com.datealive.service.Impl;

import com.datealive.entity.pojo.Points;
import com.datealive.mapper.PointsMapper;
import com.datealive.service.PointsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: PointsServiceImpl
 * @Description: TODO
 * @author: datealive
 * @date: 2021/5/3  20:23
 */
@Service
public class PointsServiceImpl implements PointsService {


    @Autowired
    private PointsMapper pointsMapper;


    @Override
    public Points getPointsByPointsId(Long id) {
        return pointsMapper.getPointsByPointsId(id);
    }

    @Override
    public Points getPointsByUserId(Long gzhu_user_id) {
        return pointsMapper.getPointsByUserId(gzhu_user_id);
    }

    @Override
    public boolean insertPoints(Points points) {
        int insertPoints = pointsMapper.insertPoints(points);
        if(insertPoints>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean deletePointsByPointsId(Long pointsId) {
        int deletePointsByPointsId = pointsMapper.deletePointsByPointsId(pointsId);
        if(deletePointsByPointsId>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean deletePointsByUserId(Long gzhu_user_id) {
        int deletePointsByUserId = pointsMapper.deletePointsByUserId(gzhu_user_id);
        if(deletePointsByUserId>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean changePointsByPointsId(Long pointsId, Long points_cnt) {
        int changePointsByPointsId = pointsMapper.changePointsByPointsId(pointsId, points_cnt);
        if(changePointsByPointsId>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean changePointsByUserId(Long gzhu_user_id, Long points_cnt) {
        int changePointsByUserId = pointsMapper.changePointsByUserId(gzhu_user_id, points_cnt);
        if(changePointsByUserId>0){
            return true;
        }
        return false;
    }
}
