package com.wounom.kaoyanircpadmin.service;


import com.wounom.kaoyanircpadmin.entity.Result;
import com.wounom.kaoyanircpadmin.entity.TiewenOfficial;

import javax.servlet.http.HttpServletRequest;

/**
 * @author litind
 * @version 1.0
 * @date 2023/4/3 9:18
 */
public interface TieWenService {

    Result getCheckTiewen();

    Result checkTiewen(int tiewenId, int status);

    Result getTiewenByUser(int userId);

    Result deleteTiewenByBlock(String blockName);

    Result getTieByBlock(int i,String blockName);

    Result deleteTiewenById(int i, int tiewenId);

    Result pushOfficialTie(int i, TiewenOfficial tiewenOfficial, HttpServletRequest request);
}
