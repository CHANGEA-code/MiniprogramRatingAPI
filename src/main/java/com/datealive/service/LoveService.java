package com.datealive.service;

import com.datealive.common.PageResult;
import com.datealive.common.Result;
import com.datealive.entity.pojo.Love;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface LoveService {
    /**
     * 根据用户的ID获得用户关注信息
     * @param userID 用户ID
     * @return
     */
    PageResult<Love> getLoveListByUserID(@Param("userID")Long userID, Integer pageNum);

    /**
     * 根据比赛ID获得关注信息
     * @param rateID
     * @return
     */
    PageResult<Love> getLoveListByRateID(@Param("rateID")Long rateID,Integer pageNum);

    Result getLoveListByUserIDWithouPageNum(@Param("userID")Long userID);

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

}
