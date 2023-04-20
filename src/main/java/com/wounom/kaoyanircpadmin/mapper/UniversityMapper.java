package com.wounom.kaoyanircpadmin.mapper;

import com.wounom.kaoyanircpadmin.entity.University;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author litind
 * @version 1.0
 * @date 2023/4/12 18:00
 */
@Mapper
public interface UniversityMapper {

    University getByName(String universityName);

    int insert(University university);

    int deleteById(Long universityId);

    List<University> getByDistrict(String universityDistrict);
    University getById(Long id);
    int update(University university);
}
