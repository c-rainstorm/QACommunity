package com.github.crainstorm.qac.admin.dao;

import com.github.crainstorm.qac.pub.entity.Admin;
import com.github.crainstorm.qac.pub.entity.AdminSession;
import com.github.crainstorm.qac.pub.entity.Notice;
import org.springframework.stereotype.Repository;

/**
 * Created by chen on 9/17/17.
 */
@Repository
public interface AdminInfoManageDao {
    int checkAdminLogin(Admin admin);

    int updateAdminInfo(Admin admin);

    int addNotice(Notice notice);

    AdminSession getAdminInfo(int id);
}
