package com.github.crainstorm.qac.user.controller;

import com.github.crainstorm.qac.pub.entity.Result;
import com.github.crainstorm.qac.pub.entity.User;
import com.github.crainstorm.qac.user.service.RegisterService;
import com.google.gson.Gson;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by chen on 9/9/17.
 */
@Controller
public class RegisterController {

    @Autowired
    private RegisterService service;

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

    @Test
    public void test() {
        String result = "true";
        System.out.println(new Gson().toJson(Result.TREU));
    }
}
