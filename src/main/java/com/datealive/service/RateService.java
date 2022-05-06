package com.datealive.service;

import com.datealive.common.PageResult;
import com.datealive.entity.dto.RateSearchListDto;
import com.datealive.entity.dto.RateTopList;
import com.datealive.entity.pojo.Rate;
import com.datealive.entity.vo.Rate.RateList;
import com.datealive.entity.vo.Rate.RateType;
import org.apache.ibatis.annotations.Param;
import org.apache.tomcat.util.http.fileupload.util.LimitedInputStream;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface RateService {

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
    PageResult<RateList> getRateList(Integer pageNum);

    /**
     * 获取比赛热点top10的list集合
     * @return
     */
    List<RateTopList> getTop10List();


    /**
     * 通过ID获得比赛详细信息
     * @return
     */
    Rate getRateDetailByID(@Param("ID")Long ID);

    /**
     * 多条件查询比赛序列
     * @param typeID
     * @param isTeam
     * @param rateGrade
     * @return
     */
    List<RateSearchListDto> getRateListByMultiCondition(Long typeID,Integer isTeam ,String rateGrade);

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
     * 创建新的竞赛类型
     * @param rateType
     * @return
     */
    boolean createRateType(@RequestBody RateType rateType);

    /**
     * 根据ID删除对应的竞赛类型
     * @param ID
     * @return
     */
    boolean deleteRateType(@Param("ID")Long ID);

    /**
     * 修改竞赛类型
     * @param rateType
     * @return
     */
    boolean updateRateType(@RequestBody RateType rateType);

    /**
     * 查询所有的竞赛类型
     * @return
     */
    List<RateType> getTypeList();

    /**
     * 根据ID查询对应的竞赛类型
     * @param ID
     * @return
     */
    RateType getTypeByRateTypeID(@Param("ID")Long ID);


    /**
     * 根据比赛标题获得比赛列表
     * @param title
     * @return
     */
    List<RateSearchListDto> getRateListByTitle(@Param("title")String title);

}
