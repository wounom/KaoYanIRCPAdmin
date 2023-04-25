package com.wounom.kaoyanircpadmin.service.impl;

import com.wounom.kaoyanircpadmin.mapper.UniversityMapper;
import com.wounom.kaoyanircpadmin.entity.Result;
import com.wounom.kaoyanircpadmin.entity.University;
import com.wounom.kaoyanircpadmin.service.UniversityService;
import com.wounom.kaoyanircpadmin.utils.FileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * @author litind
 * @version 1.0
 * @date 2023/4/12 18:00
 */
@Service
public class UniversityServiceImpl implements UniversityService {
    @Resource
    private UniversityMapper universityMapper;
    @Value("${file.upload-path-university}/")
    private String imgPath;//图片路径

    @Value("${file.upload-ip}")
    private String ip;

    /**
     *
     * 插入学院信息
     * @param university
     * @return com.wounom.kaoyanircpadmin.entity.Result
     * @author litind
     **/
    @Override
    public Result insertUniversity(University university, MultipartFile file, HttpServletRequest request) {
        //通过院校名称查询院校
        University university1 = universityMapper.getByName(university.getUniversityName());
        if (university1!=null){
            return new Result(400,"院校已存在");
        }else {
            //保存院校图片信息
            String newFn = null;
            try {
                newFn = FileUtil.saveFile(file,imgPath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String url = request.getScheme()+"://"+ip+":8080"+"/images/university/"+newFn;
            /*String Path = imgPath+newFn;*/
            university.setImage(url);
            //插入院校信息
            int r =  universityMapper.insert(university);
            if (r>0){
                return new Result(200,"新增成功");
            }else {
                return new Result(400,"新增失败");
            }
        }
    }
    /**
     *
     * 通过地区获取学校信息
     * @param universityDistrict
     * @return
     * @author litind
     **/
    @Override
    public Result getUniversityByDistrict(String universityDistrict) {
        List<University> universities = universityMapper.getByDistrict(universityDistrict);
        if (universities.isEmpty()){
            return new Result(400,"该地区不存在或地区暂无院校信息");
        }else {
            return new Result(200,"获取成功",universities.size(),universities);
        }
    }

    /**
     *
     * 删除院校信息
     * @param universityId
     * @return
     * @author litind
     **/
    @Override
    public Result deletUniveristyById(Long universityId) {
        int r = universityMapper.deleteById(universityId);
        if (r>0){
            return new Result(200,"删除成功");
        }else {
            return new Result(400, "删除失败");
        }
    }
    /**
     *
     * 更新院校信息
     * @param university,file
     * @return
     * @author litind
     **/
    @Override
    public Result update(University university, MultipartFile file,HttpServletRequest request) {
        Long id = university.getUniversityId();
        University university1 = universityMapper.getById(id);
        if (university1==null){
            return new Result(400,"学校不存在");
        }
        String image = university1.getImage();
        if (file!=null){//图片存在的时候更新图片
            if (image!=null) {
                //先将本地存储的图片删除
                String[] str = image.split("/");
                String fileName = str[str.length - 1];
                String filePath = imgPath + fileName;
                FileUtil.deleteFile(filePath);
            }
            //再将新图片存入本地
            try {
                String newFile =  FileUtil.saveFile(file,imgPath);
                String url = request.getScheme()+"://"+ip+":8080"+"/images/university/"+newFile;
                university.setImage(url);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        //更新学校信息
        int r = universityMapper.update(university);
        if (r>0){
            return new Result(200,"更新成功");
        }else {
            return new Result(400, "更新失败");
        }
    }
}
