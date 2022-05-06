package com.datealive.mapper;

import com.datealive.entity.vo.Rate.RateType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Mapper
public interface RateTypeMapper {

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
     * 获得比赛类型的总数
     * @return
     */
    Long getRateTypeCnt();

}
