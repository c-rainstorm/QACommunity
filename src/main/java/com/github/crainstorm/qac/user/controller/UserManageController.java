package com.github.crainstorm.qac.user.controller;

import com.github.crainstorm.qac.pub.entity.*;
import com.github.crainstorm.qac.user.service.UserManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Created by chen on 9/17/17.
 */
@Controller
public class UserManageController {

    @Autowired
    private UserManageService service;

    //--------------------------- Login ---------------------

    @RequestMapping(value = "/checkUserLogin.action", method = RequestMethod.POST)
    public String checkUserLogin(UserLogin user, HttpServletRequest request){
        if(user.email == null || user.password == null){
            return "/user/login.html";
        }
        if(service.checkUserLogin(user, request)){
            return "redirect:/";
        }
        return "/user/login.html";
    }

    @ResponseBody
    @RequestMapping(value = "/addUser.action", method = RequestMethod.POST)
    public Result addUser(@RequestBody User user, HttpServletRequest request) {
        if (service.addUser(user, request)) {
            return Result.TREU;
        } else {
            return Result.FALSE;
        }
    }

    //-------------------------------- User info ---------------------

    @ResponseBody
    @RequestMapping(value = "getUserDetails.action", method = RequestMethod.GET)
    public User getUserDetails(int id) {
        return service.getUserDetails(id);
    }

    @ResponseBody
    @RequestMapping(value = "getUserBriefInfo.action", method = RequestMethod.GET)
    public User getUserBriefInfo(int id) {
        return service.getUserBriefInfo(id);
    }

    @ResponseBody
    @RequestMapping(value = "followUser.action", method = RequestMethod.GET)
    public Result followUser(int user_id, int follow_id) {
        if (service.followUser(user_id, follow_id)) {
            return Result.TREU;
        }

        return Result.FALSE;
    }

    //------------------------------- Notice ------------------------

    @ResponseBody
    @RequestMapping(value = "getNotice.action", method = RequestMethod.GET)
    public Notice getNotice(int user_id){
        return service.getNotice(user_id);
    }


}
