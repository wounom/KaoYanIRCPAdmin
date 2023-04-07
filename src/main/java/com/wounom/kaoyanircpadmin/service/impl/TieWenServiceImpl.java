package com.wounom.kaoyanircpadmin.service.impl;


import com.wounom.kaoyanircpadmin.dao.TieWenMapper;
import com.wounom.kaoyanircpadmin.entity.Result;
import com.wounom.kaoyanircpadmin.entity.Tiewen;
import com.wounom.kaoyanircpadmin.service.TieWenService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author litind
 * @version 1.0
 * @date 2023/4/3 9:18
 */
@Service
public class TieWenServiceImpl implements TieWenService {
    @Resource
    private TieWenMapper tieWenMapper;

    /**
     *
     * 获取需要审核贴文
     * @return
     * @author litind
     **/
    @Override
    public Result getCheckTiewen() {
        List<Tiewen> needToCheck = tieWenMapper.getCheckTiewen();
        if (needToCheck.size()!=0){
            return new Result(200,"获取成功", needToCheck.size(), needToCheck);
        }else {
            return new Result(400,"暂无需要处理的贴文");
        }
    }

    /**
     *
     * 贴文审核
     * @param status
     * @return com.wounom.kaoyaniep.entity.Result
     * @author litind
     **/

    @Override
    public Result checkTiewen(int tiewenId,int status) {
        //拒绝为-1
        if(status <0){
           int r= tieWenMapper.updateTieWenStatus(tiewenId,status);
           if (r >0){
               return new Result(200,"拒绝成功");
           }else {
               return new Result(400,"系统错误");
           }
        }else {
            int r= tieWenMapper.updateTieWenStatus(tiewenId,status);
            if (r >0){
                return new Result(200,"贴文已通过审核");
            }else {
                return new Result(400,"系统错误");
            }
        }
        //过审为1
    }
    /**
     *
     * 通过用户id获取贴文
     * @param userId
     * @return
     * @author litind
     **/
    @Override
    public Result getTiewenByUser(int userId) {
        List<Tiewen> tiewenList =  tieWenMapper.getTieWenbyUserId(userId);
        if (tiewenList != null){
            return new Result(200,"获取成功",tiewenList.size(),tiewenList);
        }
        else {
            return new Result(400,"未发布贴文");
        }
    }
    /**
     *
     * 通过板块名称删除贴文
     * @param blockName
     * @return
     * @author litind
     **/
    @Override
    public Result deleteTiewenByBlock(String blockName) {
        int r = tieWenMapper.deleteTiewenByBlock(blockName);
        if (r >0){
            return new Result(200,"清除成功");
        }else {
            return new Result(400,"清除失败,表内无该板块数据");
        }
    }
    /**
     *
     * 通过板块名称获取贴文
     * @param blockName,i
     * @return
     * @author litind
     **/
    @Override
    public Result getTieByBlock(int i,String blockName) {

        List<Tiewen> tiewenList = tieWenMapper.getTieByBlock(blockName);
        if (tiewenList.size()>0){
            return new Result(200,"获取成功",tiewenList.size(),tiewenList);
        }else{
            return new Result(400,"获取失败,无该板块数据");
        }
    }
}
