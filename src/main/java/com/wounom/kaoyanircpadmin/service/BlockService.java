package com.wounom.kaoyanircpadmin.service;



import com.wounom.kaoyanircpadmin.entity.Result;

/**
 * @author litind
 * @version 1.0
 * @date 2023/4/7 8:22
 */
public interface BlockService {

    Result deleteBlock(String blockName);

    Result getBlock();
}
