package com.datealive.mapper;

import com.datealive.entity.pojo.Points;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName: PointsMapper
 * @Description: TODO
 * @author: datealive
 * @date: 2021/5/2  23:10
 */
@Mapper
public interface PointsMapper {


    /**
     * 根据积分ID获取积分实体类
     * @param points_id
     * @return
     */
    Points getPointsByPointsId(@Param("points_id")Long points_id);

    /**
     * 根据用户 ID 获取积分实体类
     * @param gzhu_user_id
     * @return
     */
    Points getPointsByUserId(@Param("gzhu_user_id")Long gzhu_user_id);

    /**
     * 插入积分实体类
     * @param points
     * @return
     */
     int insertPoints(Points points);

    /**
     * 根据积分ID删除积分实体类
     * @param pointsId
     * @return
     */
     int deletePointsByPointsId(@Param("points_id") Long pointsId);

    /**
     * 根据用户ID删除积分实体类
     * @param gzhu_user_id
     * @return
     */
     int deletePointsByUserId(@Param("gzhu_user_id") Long gzhu_user_id);

    /**
     * 根据积分ID和积分数量修改积分
     * @param pointsId
     * @param points_cnt
     * @return
     */
     int changePointsByPointsId(@Param("points_id")Long pointsId,@Param("points_cnt") Long points_cnt);

    /**
     * 根据用户ID和积分数量修改积分
     * @param gzhu_user_id
     * @param points_cnt
     * @return
     */
     int changePointsByUserId(@Param("gzhu_user_id")Long gzhu_user_id,@Param("points_cnt") Long points_cnt);




}
