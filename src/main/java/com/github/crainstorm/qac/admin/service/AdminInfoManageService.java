package com.github.crainstorm.qac.admin.service;

import com.github.crainstorm.qac.admin.dao.AdminInfoManageDao;
import com.github.crainstorm.qac.pub.entity.Admin;
import com.github.crainstorm.qac.pub.entity.AdminSession;
import com.github.crainstorm.qac.pub.entity.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by chen on 9/17/17.
 */
@EnableTransactionManagement
@Service
public class AdminInfoManageService {
    @Autowired
    private AdminInfoManageDao dao;

    public boolean checkAdminLogin(Admin admin, HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (dao.checkAdminLogin(admin) == 1) {
            AdminSession adminSession = dao.getAdminInfo(admin.id);
            adminSession.adminLoginStatus = true;
            session.setAttribute("adminSession", adminSession);
            return true;
        } else {
            AdminSession adminSession = new AdminSession();
            adminSession.adminLoginStatus = false;
            session.setAttribute("adminSession", adminSession);
            return false;
        }
    }

    public boolean updateAdminInfo(Admin admin) {
        return dao.updateAdminInfo(admin) == 1;
    }

    public boolean addNotice(Notice notice) {
        return dao.addNotice(notice) == 1;
    }

    public AdminSession getAdminSession(HttpServletRequest request) {
        AdminSession adminSession = (AdminSession) request.getSession().getAttribute("adminSession");
        if(adminSession == null){
            adminSession = new AdminSession();
            adminSession.adminLoginStatus = false;
        }
        return adminSession;
    }
}
