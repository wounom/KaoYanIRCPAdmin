package com.wounom.kaoyanircpadmin.controller;

import com.wounom.kaoyanircpadmin.entity.Result;
import com.wounom.kaoyanircpadmin.entity.University;
import com.wounom.kaoyanircpadmin.service.UniversityService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
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
     * @param university,file,request
     * @return
     * @author litind
     **/
    @PostMapping("/input")
    @ApiOperation("管理员新增院校")
    @ResponseBody
    public Result inputUniversity(@RequestPart("university") University university,@RequestPart("file") MultipartFile file, HttpServletRequest request){
        return universityService.insertUniversity(university,file,request);
    }
    /**
     *
     * 通过地区获取院校
     * @param universityDistrict
     * @return
     * @author litind
     **/
    @GetMapping("/getByDistrict")
    @ApiOperation("通过地区获取学校")
    @ResponseBody
    public Result OutputUniversity(@RequestParam(value = "universityDistrict") String universityDistrict){
        return universityService.getUniversityByDistrict(universityDistrict);
    }
    /**
     *
     * 删除院校
     * @param universityId
     * @return
     * @author litind
     **/
    @DeleteMapping("/delete")
    @ApiOperation("删除院校")
    public Result deleteUniversit(@RequestParam(value = "universityId") Long universityId){
        return universityService.deletUniveristyById(universityId);
    }
    /**
     *
     * 修改院校信息
     * @param
     * @return
     * @author litind
     **/
    @PostMapping("/update")
    @ApiOperation("更新院校信息(修改后的信息与universityId)")
    public Result updateUniversity(@RequestPart("university") University university,@RequestPart(value = "file",required = false) MultipartFile file,HttpServletRequest request){
        return universityService.update(university,file,request);
    }
}
