package com.github.crainstorm.qac.user.dao;

import com.github.crainstorm.qac.pub.entity.User;

/**
 * Created by chen on 9/10/17.
 */
public interface RegisterDao {
    int checkUserEmail(String email);

    int addUser(User user);
}
