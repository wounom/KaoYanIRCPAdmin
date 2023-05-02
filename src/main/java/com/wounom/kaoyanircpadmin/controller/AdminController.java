package com.wounom.kaoyanircpadmin.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wounom.kaoyanircpadmin.entity.Admin;
import com.wounom.kaoyanircpadmin.entity.FirstpagePush;
import com.wounom.kaoyanircpadmin.entity.Result;
import com.wounom.kaoyanircpadmin.service.AdminService;
import com.wounom.kaoyanircpadmin.utils.TokenUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author litind
 * @version 1.0
 * @date 2023/4/2 9:16
 */
@RestController
@CrossOrigin
@RequestMapping("/admin")
public class AdminController {
    @Resource
    private AdminService adminService;

    /**
     *
     * 管理员登录
     * 账号，密码
     * @return com.wounom.kaoyaniep.entity.Result
     * @author litind
     **/
    @PostMapping("/login")
    @ApiOperation("管理员登录")
    public Result login(/*HttpServletRequest request,*/@RequestBody Admin admin){
        Map<String,Object> map = new HashMap<>();
        if(!adminService.isAdminexsit(admin.getUsername())){
            return new Result(400,"账号不存在,或不是管理员账号");
        }
        Admin newadmin = adminService.getAdminByUsername(admin.getUsername());//查询已经注册的该邮箱账户
        if(adminService.loginCheck(admin,newadmin)){
            String token = TokenUtils.CreateToken(newadmin);
            map.put("token",token);
            map.put("admin",newadmin);
            return new Result(200,"登录成功",map.size(),map);
        }else {
            return new Result(400,"用户名或密码错误");
        }
    }

    /**
     *
     * 运维增加管理员
     * 账号，密码
     * @param admin
     * @return
     * @author litind
     **/

    @PostMapping("/newAdmin")
    @ApiOperation("新建管理员")
    public Result newAdmin(@RequestBody Admin admin){
        if(adminService.isAdminexsit(admin.getUsername())){
            return new Result(400,"管理员账号已存在");
        }
        return adminService.addAdmin(admin);
    }

    /**
     *
     * 运维修改密码
     * 账号,新密码
     * @param admin
     * @return
     * @author litind
     **/
    @PostMapping("/resetpwd")
    @ApiOperation("修改管理员密码")
    public  Result updateAdmin(@RequestBody Admin admin){
        if(!adminService.isAdminexsit(admin.getUsername())){
            return new Result(400,"管理员账号不存在");
        }
        return adminService.updateAdmin(admin);
    }

    /**
     *
     * 上传首页推送
     * @param firstpagePush
     * @return
     * @author litind
     **/
    @PostMapping("/Fppush")
    @ApiOperation("上传首页推送 first_Id,url,file,title")
    public Result updateFpagePush(@RequestPart("firstpagePush") FirstpagePush firstpagePush,@RequestPart("file") MultipartFile file,HttpServletRequest request) throws IOException {
        if (file.isEmpty()){
            return new Result(400,"图片为空");
        }
        System.out.println(firstpagePush.getTitle());
        return adminService.updateFpp(firstpagePush,file,request);
    }

    /**
     *
     * 重置首页推送（删除）
     * @param
     * @return
     * @author litind
     **/
    @PostMapping("/deletepush")
    @ApiOperation("重置首页推送(first_Id)")
    public Result resetFpagePush(@RequestParam(value = "first_Id") int first_Id){
        /*JSONObject json = JSON.parseObject(String.valueOf(first_Id));
        first_Id = (Integer) json.get("first_Id");*/
        return adminService.deleteFpp(first_Id);
    }

}
