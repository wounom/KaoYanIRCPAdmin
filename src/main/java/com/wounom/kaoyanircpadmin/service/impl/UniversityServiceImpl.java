package com.wounom.kaoyanircpadmin.service.impl;

import com.wounom.kaoyanircpadmin.dao.UniversityMapper;
import com.wounom.kaoyanircpadmin.entity.Result;
import com.wounom.kaoyanircpadmin.entity.University;
import com.wounom.kaoyanircpadmin.service.UniversityService;
import com.wounom.kaoyanircpadmin.utils.FileUtil;
import org.springframework.beans.factory.annotation.Value;
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
public class UniversityServiceImpl implements UniversityService {
    @Resource
    private UniversityMapper universityMapper;
    @Value("${file.upload-path-university}/")
    private String imgPath;//图片路径

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
            String url = request.getScheme()+"://172.25.94.245:"+request.getServerPort() +"/images/university/"+newFn;
            /*String Path = imgPath+newFn;*///todo:应当增加一个文件路径的字段，以便日后更新信息时删除文件
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
}
