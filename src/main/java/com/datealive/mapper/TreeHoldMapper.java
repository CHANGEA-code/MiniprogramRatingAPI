package com.datealive.mapper;

import com.datealive.entity.pojo.TreeHold;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Mapper
@Repository
public interface TreeHoldMapper {

    boolean createTreeHold(@RequestBody TreeHold treeHold);

    boolean deleteTreeHold(@Param("treeHoldID")Long id);

    boolean updateTreeHoldContext(@Param("treeHoldID")Long id,@Param("content")String content);

    TreeHold getTreeHoldByID(@Param("treeHoldID")Long id);

    List<TreeHold> getTreeHoldList();

    Long getAllTreeHoldCnt();

    List<TreeHold> getTreeHoldListByUserID(@Param("userId")Long userID);

}
