package com.github.crainstorm.qac.user.service;

import com.github.crainstorm.qac.pub.entity.User;
import com.github.crainstorm.qac.pub.entity.UserLogin;
import com.github.crainstorm.qac.user.dao.LoginDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by chen on 9/9/17.
 */
@Service
@Transactional
@EnableTransactionManagement
public class LoginService {

    @Autowired
    private LoginDao dao;

    @Transactional(readOnly = true)
    public boolean checkUserLogin(UserLogin user, HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (dao.checkUserLogin(user) == 1) {
            session.setAttribute("userLoginStatus", true);
            User briefInfo = dao.getBriefInfo(user);
            session.setAttribute("id", briefInfo.id);
            session.setAttribute("name", briefInfo.name);
            session.setAttribute("avatar", briefInfo.avatar);
            return true;
        } else {
            session.setAttribute("userLoginStatus", false);
            return false;
        }
    }
}
