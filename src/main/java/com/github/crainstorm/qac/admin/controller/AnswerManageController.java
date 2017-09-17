package com.github.crainstorm.qac.admin.controller;

import com.github.crainstorm.qac.admin.service.AnswerManageService;
import com.github.crainstorm.qac.pub.entity.AnswerReport;
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
public class AnswerManageController {
    
    @Autowired
    private AnswerManageService service;

    @ResponseBody
    @RequestMapping(value = "getAnswerReportList.action", method = RequestMethod.GET)
    public ArrayList<AnswerReport> getAnswerReportList() {
        return service.getAnswerReportList();
    }

    @ResponseBody
    @RequestMapping(value = "shutdownAnswer.action", method = RequestMethod.GET)
    public Result shutdownAnswer(int answer_id) {
        if (service.shutdownAnswer(answer_id)) {
            return Result.TREU;
        }
        return Result.FALSE;
    }

    @ResponseBody
    @RequestMapping(value = "deleteAnswer.action", method = RequestMethod.GET)
    public Result deleteAnswer(int answer_id) {
        if (service.deleteAnswer(answer_id)) {
            return Result.TREU;
        }
        return Result.FALSE;
    }
    
}
