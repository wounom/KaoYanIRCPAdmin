package com.wounom.kaoyanircpadmin.mapper;


import com.wounom.kaoyanircpadmin.entity.Block;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author litind
 * @version 1.0
 * @date 2023/4/7 8:21
 */
@Mapper
public interface BlockMapper {
    List<Block> getBlock();

    int deleteBlock(String blockName);
}
