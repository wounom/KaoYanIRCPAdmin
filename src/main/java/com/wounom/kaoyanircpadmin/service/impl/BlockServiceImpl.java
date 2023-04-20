package com.wounom.kaoyanircpadmin.service.impl;



import com.wounom.kaoyanircpadmin.mapper.BlockMapper;
import com.wounom.kaoyanircpadmin.entity.Block;
import com.wounom.kaoyanircpadmin.entity.Result;
import com.wounom.kaoyanircpadmin.service.BlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author litind
 * @version 1.0
 * @date 2023/4/7 8:22
 */
@Service
public class BlockServiceImpl implements BlockService {
    @Autowired
    private BlockMapper blockMapper;
    @Override
    public Result getBlock() {

        List<Block> blockList =  blockMapper.getBlock();
        if(blockList.size()>0){
            return new Result(200,"获取成功",blockList.size(),blockList);
        }else {
            return new Result(400,"获取失败，该项目下无板块");
        }
    }

    @Override
    public Result deleteBlock(String blockName) {
        int r = blockMapper.deleteBlock(blockName);
        if (r>0){
            return new Result(200,"删除成功");
        }else{
            return new Result(400,"系统错误");
        }
    }
}
