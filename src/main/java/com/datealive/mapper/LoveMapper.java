package com.datealive.mapper;

import com.datealive.entity.pojo.Love;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Mapper
public interface LoveMapper {

    /**
     * 根据用户的ID获得用户关注信息
     * @param userID 用户ID
     * @return
     */
    List<Love> getLoveListByUserID(@Param("userID")Long userID);

    /**
     * 根据比赛ID获得关注信息
     * @param rateID
     * @return
     */
    List<Love> getLoveListByRateID(@Param("rateID")Long rateID);

    /**
     * 根据LoveID获得关注信息
     * @param loveID
     * @return
     */
    Love getLoveByLoveID(@Param("loveID")Long loveID);

    /**
     * 加入关注
     * @param love
     * @return
     */
    boolean createLove(@RequestBody Love love);

    /**
     * 根据关注的ID删除关注
     * @param loveID
     * @return
     */
    boolean deleteLove(@Param("loveID")Long loveID);

    /**
     * 根据用户ID查询该用户当前收藏的比赛总数
     * @param userID
     * @return
     */
    Long getLoveByUserIDByCnt(@Param("userID")Long userID);

    /**
     * 根据比赛的ID号获得当前比赛被收藏的次数
     * @param rateID
     * @return
     */
    Long getLoveByRateIDByCnt(@Param("rateID")Long rateID);

}
