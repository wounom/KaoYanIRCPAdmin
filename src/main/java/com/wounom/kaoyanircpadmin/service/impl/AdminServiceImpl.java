package com.wounom.kaoyanircpadmin.service.impl;

import cn.hutool.core.date.DateTime;

import com.wounom.kaoyanircpadmin.mapper.AdminMapper;
import com.wounom.kaoyanircpadmin.entity.Admin;
import com.wounom.kaoyanircpadmin.entity.FirstpagePush;
import com.wounom.kaoyanircpadmin.entity.Result;
import com.wounom.kaoyanircpadmin.service.AdminService;
import com.wounom.kaoyanircpadmin.utils.FileUtil;
import com.wounom.kaoyanircpadmin.utils.PasswordUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author litind
 * @version 1.0
 * @date 2023/4/2 9:34
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Resource
    private AdminMapper adminMapper;
    @Override
    public boolean isAdminexsit(String username){
        return adminMapper.selectAdminByUsername(username) != null;
    }

    @Override
    public Admin getAdminByUsername(String username){
        return adminMapper.selectAdminByUsername(username);
    }

    @Override
    public Boolean loginCheck(Admin admin,Admin newadmin){
        String cpw = PasswordUtil.md5Pwd(admin.getPassword(),newadmin.getSalt());
        if (cpw.equals(newadmin.getPassword())){
            return true;
        }else {
            return false;
        }
    }
    /**
     *
     * 增加管理员
     * @param admin
     * @return
     * @author litind
     **/
    @Override
    public Result addAdmin(Admin admin){
        admin.setSalt(PasswordUtil.createSalt());
        admin.setPassword(PasswordUtil.md5Pwd(admin.getPassword(),admin.getSalt()));
        int r = adminMapper.insertAdmin(admin);
        if(r>0){
            return new Result(200,"增加成功");
        }else {
            return new Result(400,"增加失败");
        }
    }
    /**
     *
     * 修改管理员密码
     * @param admin
     * @return
     * @author litind
     **/
    @Override
    public Result updateAdmin(Admin admin){
        admin.setSalt(PasswordUtil.createSalt());
        admin.setPassword(PasswordUtil.md5Pwd(admin.getPassword(),admin.getSalt()));
        int r = adminMapper.updateAdmin(admin);
        if (r>0){
            return new Result(200,"修改成功");
        }else {
            return new Result(400,"修改失败");
        }
    }
    /**
     *
     * 上传首页推送
     * @param firstpagePush
     * @return
     * @author litind
     **/
    @Value("${file.upload-path-firstpage}/")
    private String path;
    @Override
    public  Result updateFpp(FirstpagePush firstpagePush, MultipartFile file, HttpServletRequest request) throws IOException {
        /*String path="D:/JAVA/Project/KaoYanIRCPAdmin/images/firstpage/";*/
            String newFn = FileUtil.saveFile(file,path);
            String url = request.getScheme()+"://43.138.194.191:"+request.getServerPort() +"/images/firstpage/"+newFn;
            String imgPath = path+newFn;
            firstpagePush.setCreate_Time(DateTime.now());
            firstpagePush.setImage(url);
            firstpagePush.setImagePath(imgPath);
            adminMapper.updateFpp(firstpagePush);
            return new Result(200,url);

    }

    /**
     *
     * 删除首页推送
     * @param first_Id
     * @return
     * @author litind
     **/
    @Override
    public Result deleteFpp(int  first_Id) {
        FirstpagePush firstpagePush = adminMapper.selectFppById(first_Id);
        if (firstpagePush==null){
            return new Result(400,"表单为空，请勿重复重置");
        }
        boolean n =  FileUtil.deleteFile(firstpagePush.getImagePath());
        System.out.println(n);
        if(adminMapper.deleteFppById(first_Id)>0){
            return new Result(200,"重置成功");
        }else {
            return new Result(400,"重置失败，请联系开发者");
        }
    }

}
