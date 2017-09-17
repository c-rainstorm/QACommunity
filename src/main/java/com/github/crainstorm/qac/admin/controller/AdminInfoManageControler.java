package com.github.crainstorm.qac.admin.controller;

import com.github.crainstorm.qac.admin.service.AdminInfoManageService;
import com.github.crainstorm.qac.pub.entity.Admin;
import com.github.crainstorm.qac.pub.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by chen on 9/17/17.
 */
@Controller
public class AdminInfoManageControler {
    @Autowired
    private AdminInfoManageService service;

    @RequestMapping(value = "checkAdminLogin.action", method = RequestMethod.GET)
    public String checkAdminLogin(Admin admin, HttpServletRequest request) {
        if (service.checkAdminLogin(admin, request)) {
            //todo admin back end
            return "redirect:/";
        }
        // todo 重定向登陆页
        return "";
    }

    @ResponseBody
    @RequestMapping(value = "updateAdminInfo.action", method = RequestMethod.GET)
    public Result updateAdminInfo(Admin admin) {
        if (service.updateAdminInfo(admin)) {
            return Result.TREU;
        }

        return Result.FALSE;
    }
}
