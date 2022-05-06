package com.datealive.service;

import com.datealive.entity.pojo.Points;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName: PointsService
 * @Description: TODO
 * @author: datealive
 * @date: 2021/5/3  20:16
 */
public interface PointsService {

    public Points getPointsByPointsId(Long id);

    public Points getPointsByUserId(Long gzhu_user_id);

    public boolean insertPoints(Points points);

    public boolean deletePointsByPointsId(Long pointsId);

    public boolean deletePointsByUserId(Long gzhu_user_id);

    public boolean changePointsByPointsId(Long pointsId,Long points_cnt);

    public boolean changePointsByUserId(Long gzhu_user_id,Long points_cnt);

}
