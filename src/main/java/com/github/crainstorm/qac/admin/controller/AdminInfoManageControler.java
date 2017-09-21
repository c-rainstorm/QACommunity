package com.github.crainstorm.qac.admin.controller;

import com.github.crainstorm.qac.admin.service.AdminInfoManageService;
import com.github.crainstorm.qac.pub.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
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

    @ResponseBody
    @RequestMapping(value = "checkAdminLogin.action", method = RequestMethod.POST)
    public Result checkAdminLogin(@RequestBody Admin admin, HttpServletRequest request) {
        if (service.checkAdminLogin(admin, request)) {
            return Result.TREU;
        }
        return Result.FALSE;
    }

    @ResponseBody
    @RequestMapping(value = "getAdminSession.action", method = RequestMethod.GET)
    public AdminSession getAdminSession(HttpServletRequest request) {
        return service.getAdminSession(request);
    }

    @ResponseBody
    @RequestMapping(value = "updateAdminInfo.action", method = RequestMethod.POST)
    public Result updateAdminInfo(@RequestBody Admin admin) {
        if (service.updateAdminInfo(admin)) {
            return Result.TREU;
        }

        return Result.FALSE;
    }

    @ResponseBody
    @RequestMapping(value = "addNotice.action", method = RequestMethod.GET)
    public Result addNotice(Notice notice) {
        if (service.addNotice(notice)) {
            return Result.TREU;
        }
        return Result.FALSE;
    }
}
