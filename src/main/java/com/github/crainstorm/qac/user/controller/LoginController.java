package com.github.crainstorm.qac.user.controller;

import com.github.crainstorm.qac.pub.entity.UserLogin;
import com.github.crainstorm.qac.user.service.LoginService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by chen on 9/9/17.
 */
@Controller
public class LoginController {
    private static Logger logger = LogManager.getLogger();

    @Autowired
    private LoginService service;

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
}
