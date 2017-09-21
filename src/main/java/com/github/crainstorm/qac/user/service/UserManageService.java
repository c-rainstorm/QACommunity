package com.github.crainstorm.qac.user.service;

import com.github.crainstorm.qac.pub.entity.Notice;
import com.github.crainstorm.qac.pub.entity.User;
import com.github.crainstorm.qac.pub.entity.UserLogin;
import com.github.crainstorm.qac.user.dao.UserManageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.regex.Pattern;

/**
 * Created by chen on 9/17/17.
 */
@EnableTransactionManagement
@Service
public class UserManageService {

    @Autowired
    private UserManageDao dao;

    public boolean checkUserLogin(UserLogin user, HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (dao.checkUserLogin(user) == 1) {
            session.setAttribute("userLoginStatus", true);
            User briefInfo = dao.getUserBriefInfo(user);
            setSessionVar(session, briefInfo);
            return true;
        } else {
            session.setAttribute("userLoginStatus", false);
            return false;
        }
    }

    public boolean addUser(User user, HttpServletRequest request) {
        if(user.name.length() > 32 || user.name.length() < 2
                || user.password.length() > 64 || user.password.length() < 6){
            return false;
        }
        Pattern VALID_EMAIL_ADDRESS_REGEX =
                Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        if(!VALID_EMAIL_ADDRESS_REGEX.matcher(user.email).find()){
            return false;
        }else if(dao.checkUserEmail(user.email) > 0){
            return false;
        }

        HttpSession session = request.getSession();
        if (dao.addUser(user) == 1) {
            session.setAttribute("userLoginStatus", true);
            User briefInfo = dao.getUserBriefInfo(user);
            setSessionVar(session, briefInfo);
            return true;
        } else {
            session.setAttribute("userLoginStatus", false);
            return false;
        }
    }

    private void setSessionVar(HttpSession session, User briefInfo) {
        session.setAttribute("id", briefInfo.id);
        session.setAttribute("name", briefInfo.name);
        session.setAttribute("avatar", briefInfo.avatar);
    }

    public User getUserDetails(int id) {
        User user = dao.getUserDetails(id);
        if(user != null){
            user.follow_num = dao.getUserFollowNum(id);
            user.be_follow_num = dao.getUserFollowerNum(id);
        }
        return dao.getUserDetails(id);
    }

    public User getUserBriefInfo(int id) {
        return dao.getUserBriefInfo(id);
    }

    public boolean followUser(int user_id, int follow_id) {
        return dao.followUser(user_id, follow_id) == 1;
    }

    public Notice getNotice(int user_id) {
        return dao.getNotice(user_id);
    }

}
