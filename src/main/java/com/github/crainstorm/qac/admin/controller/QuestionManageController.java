package com.github.crainstorm.qac.admin.controller;

import com.github.crainstorm.qac.admin.service.QuestionManageService;
import com.github.crainstorm.qac.pub.entity.QuestionReport;
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
public class QuestionManageController {

    @Autowired
    private QuestionManageService service;

    @ResponseBody
    @RequestMapping(value = "getQuestionReportList.action", method = RequestMethod.GET)
    public ArrayList<QuestionReport> getQuestionReportList() {
        return service.getQuestionReportList();
    }

    @ResponseBody
    @RequestMapping(value = "shutdownQuestion.action", method = RequestMethod.GET)
    public Result shutdownQuestion(int question_id) {
        if (service.shutdownQuestion(question_id)) {
            return Result.TREU;
        }
        return Result.FALSE;
    }

    @ResponseBody
    @RequestMapping(value = "deleteQuestion.action", method = RequestMethod.GET)
    public Result deleteQuestion(int question_id) {
        if (service.deleteQuestion(question_id)) {
            return Result.TREU;
        }
        return Result.FALSE;
    }

}
