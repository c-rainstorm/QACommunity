package com.github.crainstorm.qac.admin.service;

import com.github.crainstorm.qac.admin.dao.AdminInfoManageDao;
import com.github.crainstorm.qac.pub.entity.Admin;
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
            session.setAttribute("adminLoginStatus", true);
            admin = dao.getAdminInfo(admin);
            setSessionVar(session, admin);
            return true;
        } else {
            session.setAttribute("adminLoginStatus", false);
            return false;
        }
    }
    private void setSessionVar(HttpSession session, Admin admin) {
        session.setAttribute("admin_id", admin.id);
        session.setAttribute("admin_name", admin.name);
    }

    public boolean updateAdminInfo(Admin admin) {
        return dao.updateAdminInfo(admin) == 1;
    }

    public boolean addNotice(Notice notice) {
        return dao.addNotice(notice) == 1;
    }
}
