package com.wounom.kaoyanircpadmin.controller;


import com.wounom.kaoyanircpadmin.entity.Block;
import com.wounom.kaoyanircpadmin.entity.Result;
import com.wounom.kaoyanircpadmin.service.BlockService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Objects;

/**
 * @author litind
 * @version 1.0
 * @date 2023/4/7 8:21
 */
@RestController
@RequestMapping("/block")
public class BlockController {
    @Autowired
    private BlockService blockService;
    /**
     *
     * 板块管理：获取板块
     * @param
     * @return
     * @author litind
     **/
    @GetMapping("/getall")
    @ApiOperation("管理员获取：板块管理，获取板块列表")
    public Result getBlock(){
       return blockService.getBlock();
    }

    /**
     *
     * 删除板块 (管理员删除板块)
     * @param status , blockName
     * @return
     * @author litind
     **/
    @DeleteMapping("/delete")
    @ApiOperation("删除板块，记得去TiewenController删除板块内数据(status ,blockName)")
    public Result deleteBlock(@RequestParam("status") int status,@RequestParam("blockName") String blockName){
        if(status == 0){
            return  new Result(400,"官方必要板块，不允许删除");
        } else{ //删除院校板块
           return  blockService.deleteBlock(blockName);
        }
    }

    /**
     *
     * 新增板块
     * @param block
     * @return
     * @author litind
     **/
    @PostMapping("/new")
    @ApiOperation("新增板块")
    public Result newBlock(@RequestBody Block block){
        if (block.getStatus()==0){
            return new Result(400,"不允许再新增官方板块");
        }
        return blockService.newBlock(block);
    }
}
