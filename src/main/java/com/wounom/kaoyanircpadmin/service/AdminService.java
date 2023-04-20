package com.wounom.kaoyanircpadmin.service;


import com.wounom.kaoyanircpadmin.entity.Admin;
import com.wounom.kaoyanircpadmin.entity.FirstpagePush;
import com.wounom.kaoyanircpadmin.entity.Result;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author litind
 * @version 1.0
 * @date 2023/4/2 9:34
 */
public interface AdminService {
    boolean isAdminexsit(String email);

    Admin getAdminByUsername(String username);

    Boolean loginCheck(Admin admin,Admin newadmin);

    Result addAdmin(Admin admin);

    Result updateAdmin(Admin admin);

    Result updateFpp(FirstpagePush firstpagePush, MultipartFile file, HttpServletRequest request) throws IOException;

    Result deleteFpp(int firstId);


}
