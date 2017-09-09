package com.github.crainstorm.qac.user.dao;

import com.github.crainstorm.qac.pub.entity.User;
import com.github.crainstorm.qac.pub.entity.UserLogin;

/**
 * Created by chen on 9/9/17.
 */
public interface LoginDao {
    int checkUserLogin(UserLogin user);

    /**
     *
     * @param user - user.email 邮箱
     *             - user.password 密码
     * @return User
     *             - id
     *             - name
     *             - avatar
     */
    User getBriefInfo(UserLogin user);
}
