package com.wounom.kaoyanircpadmin.service;

import com.wounom.kaoyanircpadmin.entity.Result;
import com.wounom.kaoyanircpadmin.entity.University;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author litind
 * @version 1.0
 * @date 2023/4/12 18:00
 */
public interface UniversityService {
    Result insertUniversity(University university, MultipartFile file, HttpServletRequest request) throws IOException;
}
