package com.datealive.mapper;

import com.datealive.entity.dto.RateSearchListDto;
import com.datealive.entity.dto.RateTopList;
import com.datealive.entity.pojo.Rate;
import com.datealive.entity.vo.Rate.RateList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@Mapper
public interface RateMapper {
    /**
     * 创建比赛
     * @param rate
     * @return
     */
    boolean createRate(@RequestBody Rate rate);

    /**
     * 获得比赛队列
     * @return
     */
    List<RateList> getRateList();

    /**
     * 根据id获取热门比赛信息
     * @param ID
     * @return
     */
    RateTopList getTopRateByRateID(@Param("ID")Long ID);

    /**
     * 通过ID获得比赛详细信息
     * @return
     */
    Rate getRateDetailByID(@Param("ID")Long ID);

    /**
     * 根据ID号删除该比赛
     * @param ID
     * @return
     */
    boolean deleteRateByID(@Param("ID")Long ID);

    /**
     * 修改比赛信息
     * @param rate
     * @return
     */
    boolean updateRateByID(@RequestBody Rate rate);

    /**
     * 获得比赛的总数
     * @return
     */
    Long getRateCnt();

    /**
     * 根据比赛等级获得比赛列表
     * @param grade
     * @return
     */
    List<RateSearchListDto> getRateListByMultiCondition(@Param("grade")String grade,@Param("typeID")Long typeID,@Param("isTeam")Integer isTeam);

    /**
     * 根据比赛标题获得比赛列表
     * @param title
     * @return
     */
    List<RateSearchListDto> getRateListByTitle(@Param("title")String title);

}
