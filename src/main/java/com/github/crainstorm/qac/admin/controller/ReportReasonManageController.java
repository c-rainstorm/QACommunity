package com.github.crainstorm.qac.admin.controller;

import com.github.crainstorm.qac.admin.service.ReportReasonManageService;
import com.github.crainstorm.qac.pub.entity.ReportReason;
import com.github.crainstorm.qac.pub.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

/**
 * Created by chen on 9/17/17.
 */
@Controller
public class ReportReasonManageController {

    @Autowired
    private ReportReasonManageService service;

    @ResponseBody
    @RequestMapping(value = "getReportReasonList.action", method = RequestMethod.GET)
    public ArrayList<ReportReason> getReportReasonList() {
        return service.getReportReasonList();
    }

    @ResponseBody
    @RequestMapping(value = "addReportReason.action", method = RequestMethod.GET)
    public Result addReportReason(ReportReason reportReason) {
        if (service.addReportReason(reportReason)) {
            return Result.TREU;
        }
        return Result.FALSE;
    }

    @ResponseBody
    @RequestMapping(value = "updateReportReason.action", method = RequestMethod.GET)
    public Result updateReportReason(ReportReason reportReason) {
        if (service.updateReportReason(reportReason)) {
            return Result.TREU;
        }
        return Result.FALSE;
    }

    @ResponseBody
    @RequestMapping(value = "deleteReportReason.action", method = RequestMethod.GET)
    public Result deleteReportReason(int id) {
        if (service.deleteReportReason(id)) {
            return Result.TREU;
        }
        return Result.FALSE;
    }
}
