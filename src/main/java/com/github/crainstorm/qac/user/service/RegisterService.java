package com.github.crainstorm.qac.user.service;

import com.github.crainstorm.qac.pub.entity.User;
import com.github.crainstorm.qac.user.dao.LoginDao;
import com.github.crainstorm.qac.user.dao.RegisterDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by chen on 9/9/17.
 */
@EnableTransactionManagement
@Transactional
@Service
public class RegisterService {

    @Autowired
    private RegisterDao registerDao;

    @Autowired
    private LoginDao loginDao;
    public boolean checkUserEmail(String email) {
        return registerDao.checkUserEmail(email) > 0 ? false : true;
    }

    public boolean addUser(User user, HttpServletRequest request) {
        HttpSession session = request.getSession();
        if(registerDao.addUser(user) == 1){
            session.setAttribute("userLoginStatus", true);
            User briefInfo = loginDao.getBriefInfo(user);
            session.setAttribute("id", briefInfo.id);
            session.setAttribute("name", briefInfo.name);
            session.setAttribute("avatar", briefInfo.avatar);
            return true;
        }else{
            session.setAttribute("userLoginStatus", false);
            return false;
        }
    }
}
