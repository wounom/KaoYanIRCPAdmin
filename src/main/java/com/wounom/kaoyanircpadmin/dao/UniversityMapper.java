package com.wounom.kaoyanircpadmin.dao;

import com.wounom.kaoyanircpadmin.entity.University;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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

    @Delete("delete from university where universityId = #{universityId}")
    int deleteById(Long universityId);
    @Select("select * from university where universityDistrict = #{universityDistrict}")
    List<University> getByDistrict(String universityDistrict);
    @Select("select * from university where universityId = #{universityId}")
    University getById(Long id);

    @Update("update university set universityName=#{universityName},universityIntroduce=#{universityIntroduce},universityDistrict=#{universityDistrict},universityHigherup=#{universityHigherup},ifDouble=#{ifDouble},ifGraduate=#{ifGraduate},ifIndependent=#{ifIndependent}," +
            "image=#{image},officiaInet=#{officiaInet},address=#{address},post=#{post},phone=#{phone} where universityId = #{universityId}")
    int update(University university);
}
