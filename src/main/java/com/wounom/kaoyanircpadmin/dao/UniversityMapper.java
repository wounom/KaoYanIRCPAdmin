package com.wounom.kaoyanircpadmin.dao;

import com.wounom.kaoyanircpadmin.entity.University;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author litind
 * @version 1.0
 * @date 2023/4/12 18:00
 */
public interface UniversityMapper {
    @Select("select * from university where universityName = #{universityName}")
    University getByName(String universityName);

    @Insert("insert into university(universityName,universityIntroduce,universityDistrict,universityHigherup,ifDouble,ifGraduate,ifIndependent,image,officiaInet,address,post,phone) " +
            "values(#{universityName},#{universityIntroduce},#{universityDistrict},#{universityHigherup},#{ifDouble},#{ifGraduate},#{ifIndependent},#{image},#{officiaInet},#{address},#{post},#{phone})" )
    int insert(University university);
}
