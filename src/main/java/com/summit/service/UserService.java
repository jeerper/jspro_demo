package com.summit.service;


import com.summit.dao.entity.UserModel;
import com.summit.util.page.Page;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * @author: jeerper
 * @since: 2020/12/24 17:42
 */
public interface UserService {

    void insertUserInfo(UserModel userInfo);

    UserModel findUserByUserName(String username);

    Page<UserModel> findUsersByPages(String username, String name, String sex, Date start, Date end,Integer pageIndex,Integer pageSize);

    String fileImport(String username, String sex, MultipartFile file) throws Exception;

    void exportTemplate(HttpServletRequest request, HttpServletResponse response);

    void export(UserModel user, HttpServletRequest request, HttpServletResponse response) throws Exception;
}
