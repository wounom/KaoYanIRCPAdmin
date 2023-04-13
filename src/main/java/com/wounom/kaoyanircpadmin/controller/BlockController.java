package com.wounom.kaoyanircpadmin.controller;


import com.wounom.kaoyanircpadmin.entity.Result;
import com.wounom.kaoyanircpadmin.service.BlockService;
import io.swagger.annotations.ApiOperation;
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
    @Resource
    private BlockService blockService;
    /**
     *
     * 板块管理：获取板块
     * @param
     * @return
     * @author litind
     **/
    @GetMapping("/getall")
    @ApiOperation("管理员：板块管理，获取板块列表")
    public Result getBlock(){
       return blockService.getBlock();
    }

    /**
     *
     * 删除板块 (管理员删除板块)
     * @param status ,blockName
     * @return
     * @author litind
     **/
    @DeleteMapping("/delete")
    @ApiOperation("删除板块，记得去TiewenController删除板块内数据(status ,blockName)")
    public Result deleteBlock(@RequestParam("status") int status,@RequestParam("blockName") String blockName){
        if(status == 1 || status == 2){
            return  new Result(400,"官方必要板块，不允许删除");
        } else{ //删除院校板块
           return  blockService.deleteBlock(blockName);
        }
    }
}
