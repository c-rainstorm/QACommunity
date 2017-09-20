package com.github.crainstorm.qac.user.controller;

import com.github.crainstorm.qac.pub.entity.Question;
import com.github.crainstorm.qac.pub.entity.QuestionReport;
import com.github.crainstorm.qac.pub.entity.Result;
import com.github.crainstorm.qac.user.service.ProblemManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

/**
 * Created by chen on 9/16/17.
 */

@Controller
public class ProblemManageController {

    @Autowired
    private ProblemManageService service;

    @ResponseBody
    // todo method need modify
    @RequestMapping(value = "getQuestionsByKeyword.action", method = RequestMethod.GET)
    public ArrayList<Question> getQuestionsByKeyword(String keyword, int maxNumInOnePage, int pageNum) {
        return service.getQuestionsByKeyword(keyword, maxNumInOnePage * (pageNum - 1), maxNumInOnePage);
    }

    @ResponseBody
    @RequestMapping(value = "getQuestionsByLabel.action", method = RequestMethod.GET)
    public ArrayList<Question> getQuestionsByLabel(int label_id, int maxNumInOnePage, int pageNum) {
        return service.getQuestionsByLabel(label_id, maxNumInOnePage * (pageNum - 1), maxNumInOnePage);
    }

    @ResponseBody
    @RequestMapping(value = "getQuestionsByUserId.action", method = RequestMethod.GET)
    public ArrayList<Question> getQuestionsByUserId(int author_id, int maxNumInOnePage, int pageNum) {
        return service.getQuestionsByUserId(author_id, maxNumInOnePage * (pageNum - 1), maxNumInOnePage);
    }

    @ResponseBody
    @RequestMapping(value = "getQuestion.action", method = RequestMethod.GET)
    public Question getQuestion(int id) {
        return service.getQuestion(id);
    }

    @ResponseBody
    @RequestMapping(value = "addQuestion.action", method = RequestMethod.POST)
    public Result addQuestion(@RequestBody Question newQuestion) {
        if (service.addQuestion(newQuestion)) {
            return Result.TREU;
        }
        return Result.FALSE;
    }

    @ResponseBody
    @RequestMapping(value = "updateQuestion.action", method = RequestMethod.GET)
    public Result updateQuestion(Question question) {
        if (service.updateQuestion(question)) {
            return Result.TREU;
        }
        return Result.FALSE;
    }

    @ResponseBody
    @RequestMapping(value = "up_downQuestion.action", method = RequestMethod.GET)
    public Result upDownQuestion(int question_id, int user_id, boolean up_down) {
        if (service.upDownQuestion(question_id, user_id, up_down)) {
            return Result.TREU;
        }
        return Result.FALSE;
    }

    @ResponseBody
    @RequestMapping(value = "followQuestion.action", method = RequestMethod.GET)
    public Result followQuestion(int user_id, int question_id) {
        if (service.followQuestion(user_id, question_id)) {
            return Result.TREU;
        }
        return Result.FALSE;
    }

    @ResponseBody
    @RequestMapping(value = "reportQuestion.action", method = RequestMethod.GET)
    public Result reportQuestion(QuestionReport report) {
        if (service.reportQuestion(report)) {
            return Result.TREU;
        }
        return Result.FALSE;
    }
}
