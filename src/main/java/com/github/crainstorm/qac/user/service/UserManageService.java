package com.github.crainstorm.qac.user.service;

import com.github.crainstorm.qac.pub.entity.Notice;
import com.github.crainstorm.qac.pub.entity.User;
import com.github.crainstorm.qac.pub.entity.UserLogin;
import com.github.crainstorm.qac.pub.entity.UserSession;
import com.github.crainstorm.qac.user.dao.UserManageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 * Created by chen on 9/17/17.
 */
@EnableTransactionManagement
@Service
public class UserManageService {

    @Autowired
    private UserManageDao dao;
    @Autowired
    private String imageDir;

    public boolean checkUserLogin(UserLogin user, HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (dao.checkUserLogin(user) == 1) {
            UserSession usersession = dao.getUserBriefInfoByEmail(user);
            usersession.userLoginStatus = true;
            session.setAttribute("userSession", usersession);
            return true;
        } else {
            UserSession userSession = new UserSession();
            userSession.userLoginStatus = false;
            session.setAttribute("userSession", userSession);
            return false;
        }
    }

    public boolean addUser(User user, HttpServletRequest request) {
        if (user.name.length() > 32 || user.name.length() < 2
                || user.password.length() > 64 || user.password.length() < 6) {
            return false;
        }
        Pattern VALID_EMAIL_ADDRESS_REGEX =
                Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        if (!VALID_EMAIL_ADDRESS_REGEX.matcher(user.email).find()) {
            return false;
        } else if (dao.checkUserEmail(user.email) > 0) {
            return false;
        }

        HttpSession session = request.getSession();
        if (dao.addUser(user) == 1) {
            UserSession usersession = dao.getUserBriefInfoByEmail(user);
            usersession.userLoginStatus = true;
            session.setAttribute("userSession", usersession);
            return true;
        } else {
            UserSession userSession = new UserSession();
            userSession.userLoginStatus = false;
            session.setAttribute("userSession", userSession);
            return false;
        }
    }

    public boolean updateUser(User user, MultipartFile avatar) {
        if (user.name.length() > 32 || user.name.length() < 2) {
            return false;
        }
        File target = new File(imageDir + File.separator + "avatars" + File.separator + user.id + ".png");
        File targetCopy = new File(target.getAbsolutePath() + ".copy");
        try {
            if (target.exists()) {
                target.renameTo(targetCopy);
            }
            avatar.transferTo(target);
        } catch (IOException e) {
            target.deleteOnExit();
            if (targetCopy.exists()) {
                targetCopy.renameTo(target);
            }
            return false;
        }
        user.avatar = user.id + ".png";
        return dao.updateUser(user) == 1;
    }

    public User getUserDetails(int id) {
        User user = dao.getUserDetails(id);
        if (user != null) {
            user.follow_num = dao.getUserFollowNum(id);
            user.be_follow_num = dao.getUserFollowerNum(id);
        }
        return dao.getUserDetails(id);
    }

    public User getUserBriefInfo(int id) {
        return dao.getUserBriefInfoById(id);
    }

    public boolean followUser(int user_id, int follow_id) {
        return dao.followUser(user_id, follow_id) == 1;
    }

    public Notice getNotice(int user_id) {
        return dao.getNotice(user_id);
    }

    public UserSession getUserSession(HttpServletRequest request) {
        UserSession session = (UserSession) request.getSession().getAttribute("userSession");
        if (session == null) {
            session = new UserSession();
            session.userLoginStatus = false;
        }
        return session;
    }

}
