package com.wounom.kaoyanircpadmin.mapper;


import com.wounom.kaoyanircpadmin.entity.Admin;
import com.wounom.kaoyanircpadmin.entity.FirstpagePush;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author litind
 * @version 1.0
 * @date 2023/4/2 9:56
 */
@Mapper
public interface AdminMapper{

    Admin selectAdminByUsername(String username);//获取库中已有的管理员账号

    int insertAdmin(Admin admin);

    int updateAdmin(Admin admin);

    int updateFpp(FirstpagePush firstpagePush);

    int deleteFppById(FirstpagePush firstpagePush);

    FirstpagePush selectFppById(int first_Id);

}
