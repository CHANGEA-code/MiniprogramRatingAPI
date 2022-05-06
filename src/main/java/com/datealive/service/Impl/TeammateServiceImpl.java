package com.datealive.service.Impl;

import com.datealive.common.PageResult;
import com.datealive.common.ResultCode;
import com.datealive.common.StaticFinalCode;
import com.datealive.entity.pojo.Team;
import com.datealive.entity.pojo.Teammate;
import com.datealive.mapper.TeamMapper;
import com.datealive.mapper.TeammateMapper;
import com.datealive.service.TeammateService;
import com.github.pagehelper.PageHelper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Data
public class TeammateServiceImpl implements TeammateService {
    @Autowired
    private TeammateMapper teammateMapper;

    @Autowired
    private TeamMapper teamMapper;

    @Override
    public boolean exitTeamByTeammateID(Long teammateID) {
        return teammateMapper.exitTeamByTeammateID(teammateID);
    }

    @Override
    public boolean exitTeamByUserIDAndTeamID(Long teamID, Long userID) {
        boolean exitTeamByUserIDAndTeamID = teammateMapper.exitTeamByUserIDAndTeamID(teamID, userID);
        if (exitTeamByUserIDAndTeamID) {
            //如果剔除队员成功，将团队数量-1
            int descreaseTeamCnt = teamMapper.descreaseTeamCnt(teamID);
            if (descreaseTeamCnt>0) {
                return true;
            }
        }
        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean joinInTeam(Teammate teammate) {
        Long teamID = teammate.getTeamID();
        Team teamListByTeamID = teamMapper.getTeamListByTeamID(teamID);

        Integer cnt = teamListByTeamID.getCnt();
        Integer cntLimited = teamListByTeamID.getCntLimited();
        if(cnt<cntLimited){

            teamListByTeamID.setCnt(teamListByTeamID.getCnt()+1);

            boolean b = teamMapper.updateTeam(teamListByTeamID);
            b&=teammateMapper.joinInTeam(teammate);
            return b;
        }else {
            return false;
        }

    }

    @Override
    public Teammate getTeammateByTeammateID(Long teammateID) {
        return teammateMapper.getTeammateByTeammateID(teammateID);
    }

    @Override
    public PageResult<Teammate> getTeamListByCaptainID(Long captainID,Integer pageNum) {
        Long allTeammate=teammateMapper.getTeammateCntByUserID(captainID);
        int totalPage=(int)Math.ceil(allTeammate*1.0/ StaticFinalCode.PageSize_10);
        PageHelper.startPage(pageNum,StaticFinalCode.PageSize_10);
        List<Teammate> teammateByUserID = teammateMapper.getTeammateByUserID(captainID);
        if(teammateByUserID.isEmpty()){
            return new PageResult<Teammate>(ResultCode.Not_Found,totalPage,pageNum,null);
        }
        else{
            return new PageResult<Teammate>(ResultCode.Success,totalPage,pageNum,teammateByUserID);
        }
    }

    @Override
    public PageResult<Teammate> getTeammateByTeamID(Long teamID, Integer pageNum) {
        Long allTeammate=teammateMapper.getTeammateCntByTeamID(teamID);
        int totalPage=(int)Math.ceil(allTeammate*1.0/ StaticFinalCode.pageSize_5);
        PageHelper.startPage(pageNum,StaticFinalCode.pageSize_5);
        List<Teammate> teammateByTeammateID = teammateMapper.getTeammateByTeamID(teamID);
        if(teammateByTeammateID.isEmpty()){
            return new PageResult<Teammate>(ResultCode.Not_Found,totalPage,pageNum,null);
        }
        else{
            return new PageResult<Teammate>(ResultCode.Success,totalPage,pageNum,teammateByTeammateID);
        }
    }

}
