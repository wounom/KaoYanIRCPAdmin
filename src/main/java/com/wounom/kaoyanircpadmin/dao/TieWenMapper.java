package com.wounom.kaoyanircpadmin.dao;


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
    @Select("select * from tiewen where user_id = #{userId}")
    List<Tiewen> getTieWenbyUserId(long id);


    @Select("select * from tiewen where status = 0")
    List<Tiewen> getCheckTiewen();

    @Update("update tiewen set status = #{status} where tiewenId = #{tiewenId}")
    int updateTieWenStatus(int tiewenId, int status);
    @Delete("delete from tiewen where blockName = #{blockName}")
    int deleteTiewenByBlock(String blockName);

    @Select("select * from tiewen where blockName = #{blockName}")
    List<Tiewen> getTieByBlock(String blockName);
    @Select("select * from tiewen_official where blockName = #{blockName}")
    List<TiewenOfficial> getTieOfByBlock(String blockName);

    @Delete("delete from tiewen_official where tiewenID = #{tiewenId}")
    int deleteTieOfById();
    @Delete("delete from tiewen where tiewenId = #{tiewenId}")
    int deleteTieById(int tiewenId);

    @Insert("insert into tiewen_official(adminID,title,createTime,content,blockName) values (#{adminId},#{title},#{createTime},#{content},#{blockName})")
    int insertTiewenOfficial(TiewenOfficial tiewenOfficial);

    @Insert("insert into tiewen(userId,title,createTime,content,blockName,status) values (#{userId},#{title},#{createTime},#{content},#{blockName},#{status})")
    int insertTiewen(Tiewen tiewen);
}
