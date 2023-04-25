package com.wounom.kaoyanircpadmin.controller;


import com.wounom.kaoyanircpadmin.entity.Result;
import com.wounom.kaoyanircpadmin.entity.Tiewen;
import com.wounom.kaoyanircpadmin.entity.TiewenOfficial;
import com.wounom.kaoyanircpadmin.service.TieWenService;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;

/**
 * @author litind
 * @version 1.0
 * @date 2023/4/3 9:18
 */
@RestController
@RequestMapping("/tiewen")
public class TieWenController {


    @Resource
    private TieWenService tieWenService;

    /**
     *
     * 获取需要审核的贴文
     * @param
     * @return
     * @author litind
     **/
    @GetMapping("/getCheck")
    @ApiOperation("获取需要审核的贴文")
    public Result getCheckTiewen(){
        return tieWenService.getCheckTiewen();
    }
    /**
     *
     * 审核管理
     * @param tiewen
     * @return
     * @author litind
     **/
    @PostMapping("/check")
    @ApiOperation("审核推文 tiewenId status")
    public Result CheckTiewen(@RequestBody Tiewen tiewen){
        Long tiewenId = tiewen.getTiewenId();
        int status = tiewen.getStatus();
        //删除为-1
        //过审为1
        return tieWenService.checkTiewen(tiewenId,status);
    }
    /**
     *
     * 获取用户发帖
     * @param userId
     * @return
     * @author litind
     **/
    /*@GetMapping("/getByUserid")
    @ApiOperation("通过用户id获取帖子")
    public Result GetTieWenByUser(int userId*//*HttpServletRequest request*//*){
        *//*User user = (User) request.getSession().getAttribute("user");
        int userId = (int) user.getId();*//*
        return tieWenService.getTiewenByUser(userId);
    }*/

    /**
     *
     * 管理员删除板块的同时删除板块内数据
     * @param blockName
     * @return
     * @author litind
     **/
    @DeleteMapping("/deleteByBlock")
    @ApiOperation("删除板块时，同时删除板块数据")
    public Result deleteTiewenByBlock(@RequestParam("blockName") String blockName){
        return tieWenService.deleteTiewenByBlock(blockName);
    }


    /**
     *
     * 通过板块获取贴文
     * @param blockName,i
     * @return
     * @author litind
     **/
    @GetMapping("/getByBlock")
    @ApiOperation("获取某个板块的所有贴文")
    public Result getTieByBlock(@RequestParam("i") int i,@RequestParam("blockName") String blockName){
        return tieWenService.getTieByBlock(i,blockName);
    }

    /**
     *
     * 通过贴文id删除贴文
     * @param i,tiewenId
     * @return
     * @author litind
     **/
    @DeleteMapping("/deleteByid")
    @ApiOperation("通过帖子id删除帖子 (i=0时表示是官方贴文)i,tiewenId")
    public Result deleteTieByid(/*@RequestBody Map<String,Object> map*/@RequestParam(value = "i")int i,@RequestParam("tiewenId") int tiewenId){
        /*int i = (int) map.get("i");
        int tiewenId = (int) map.get("tiewenId");*/
        return tieWenService.deleteTiewenById(i,tiewenId);
    }

    /**
     *
     * 发布官方贴文
     * @param ofMap
     * @return
     * @author litind
     **/
    @PostMapping("/pushOfTie")
    @ApiOperation("发布官方帖子,传入(i=0时表示是官方贴文)i,title,content,blockName")
    public Result pushOfficialTie( @RequestBody Map<String,Object> ofMap, HttpServletRequest request){
        TiewenOfficial tiewenOfficial = new TiewenOfficial();
        tiewenOfficial.setTitle((String) ofMap.get("title"));
        tiewenOfficial.setContent((String) ofMap.get("content"));
        tiewenOfficial.setBlockName((String) ofMap.get("blockName"));
        int i = (int) ofMap.get("i");
        /*int i = (int) map.get("i");
        TiewenOfficial tiewenOfficial = (TiewenOfficial) map.get("tiewenOfficial");*/
        return tieWenService.pushOfficialTie(i,tiewenOfficial,request);
    }
}
