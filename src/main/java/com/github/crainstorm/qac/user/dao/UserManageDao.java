package com.github.crainstorm.qac.user.dao;

import com.github.crainstorm.qac.pub.entity.Notice;
import com.github.crainstorm.qac.pub.entity.User;
import com.github.crainstorm.qac.pub.entity.UserLogin;
import com.github.crainstorm.qac.pub.entity.UserSession;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by chen on 9/17/17.
 */
@Repository
public interface UserManageDao {
    int checkUserLogin(UserLogin user);

    int checkUserEmail(String email);

    UserSession getUserBriefInfoByEmail(UserLogin user);

    User getUserBriefInfoById(int id);

    int addUser(User user);

    User getUserDetails(int id);

    int followUser(@Param("user_id") int user_id, @Param("follow_id") int follow_id);

    Notice getNotice(int user_id);

    int getUserFollowNum(int id);

    int getUserFollowerNum(int id);
}
