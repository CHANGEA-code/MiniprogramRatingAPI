package com.datealive.service;


import com.datealive.common.PageResult;
import com.datealive.entity.dto.TreeHoldDto;
import com.datealive.entity.pojo.TreeHold;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface TreeHoldService {

    boolean createTreeHold(@RequestBody TreeHold treeHold);

    boolean deleteTreeHold(@Param("treeHoldID")Long id);

    boolean updateTreeHoldContent(@Param("treeHoldID")Long id,@Param("content")String content);

    TreeHoldDto getTreeHoldByID(@Param("treeHoldID")Long id);

    List<TreeHoldDto> getTreeHoldListByUserID(@Param("userId")Long userID);

    PageResult<TreeHoldDto> getTreeHoldList(Integer pageNum);
}
