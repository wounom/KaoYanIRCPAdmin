package com.wounom.kaoyanircpadmin.mapper;


import com.wounom.kaoyanircpadmin.entity.Tiewen;
import com.wounom.kaoyanircpadmin.entity.TiewenOfficial;
import org.apache.ibatis.annotations.*;

import java.util.List;


/**
 * @author litind
 * @version 1.0
 * @date 2023/4/3 9:18
 */
@Mapper
public interface TieWenMapper {

    List<Tiewen> getTieWenbyUserId(long id);

    List<Tiewen> getCheckTiewen();
    int updateTieWenStatus(int tiewenId, int status);
    int deleteTiewenByBlock(String blockName);

    List<Tiewen> getTieByBlock(String blockName);
    List<TiewenOfficial> getTieOfByBlock(String blockName);

    int deleteTieOfById(int tiewenId);
    int deleteTieById(int tiewenId);
    int insertTiewenOfficial(TiewenOfficial tiewenOfficial);
    int insertTiewen(Tiewen tiewen);
}
