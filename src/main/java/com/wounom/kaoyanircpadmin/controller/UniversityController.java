package com.wounom.kaoyanircpadmin.controller;

import com.wounom.kaoyanircpadmin.entity.Result;
import com.wounom.kaoyanircpadmin.entity.University;
import com.wounom.kaoyanircpadmin.service.UniversityService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author litind
 * @version 1.0
 * @date 2023/4/11 21:07
 */
@RestController
@RequestMapping("/university")
public class UniversityController {
    @Resource
    private UniversityService universityService;
    /**
     *
     * 根据地区新增院校
     * @param university
     * @return
     * @author litind
     **/
    @PostMapping("/input")
    @ApiOperation("管理员新增院校")
    public Result inputUniversity(@RequestBody University university, MultipartFile file, HttpServletRequest request){
        return universityService.insertUniversity(university,file,request);
    }

}
