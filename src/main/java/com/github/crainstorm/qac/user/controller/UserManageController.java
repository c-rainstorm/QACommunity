package com.github.crainstorm.qac.user.controller;

import com.github.crainstorm.qac.pub.entity.*;
import com.github.crainstorm.qac.user.service.UserManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    // todo method maybe POST
    @RequestMapping(value = "/checkUserLogin.action", method = RequestMethod.GET)
    public String checkUserLogin(UserLogin user, HttpServletRequest request){
        if(user.email == null || user.password == null){
            throw new IllegalArgumentException("email or password cannot be null");
        }
        if(service.checkUserLogin(user, request)){
            return "redirect:/";
        }
        // todo 重定向登陆页
        return "";
    }

    //--------------------------- Register --------------------

    //    @RequestMapping(value = "/checkUserName.action", method = RequestMethod.GET)
    public Result checkUserName(@RequestParam("name") String username) {
        // todo
        return Result.FALSE;
    }

    //    @ResponseBody
//    @RequestMapping(value = "/checkUserPassword.action", method = RequestMethod.GET)
    public Result checkUserPassword(String password) {
//        if (service.checkUserPassword(password)) {
//            return Result.TREU;
//        }
        // todo
        return Result.FALSE;
    }

    @ResponseBody
    @RequestMapping(value = "/checkUserEmail.action", method = RequestMethod.GET)
    public Result checkUserEmail(String email) {
        if (service.checkUserEmail(email)) {
            return Result.TREU;
        }
        return Result.FALSE;
    }

    @RequestMapping(value = "/addUser.action", method = RequestMethod.GET)
    public String addUser(User user, HttpServletRequest request) {
        if (service.addUser(user, request)) {
            return "redirect:/";
        } else {
            // todo 登陆页
            return "";
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
