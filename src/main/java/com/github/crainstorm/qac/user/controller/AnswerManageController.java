package com.github.crainstorm.qac.user.controller;

import com.github.crainstorm.qac.pub.entity.Answer;
import com.github.crainstorm.qac.pub.entity.AnswerReport;
import com.github.crainstorm.qac.pub.entity.Result;
import com.github.crainstorm.qac.user.service.AnswerManageService;
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
    @RequestMapping(value = "getAnswersByQuestionId.action", method = RequestMethod.GET)
    public ArrayList<Answer> getAnswersByQuestionId(int question_id, int maxNumInOnePage, int pageNum) {
        return service.getAnswersByQuestionId(question_id, maxNumInOnePage, pageNum);
    }

    @ResponseBody
    @RequestMapping(value = "getAnswersByUserId.action", method = RequestMethod.GET)
    public ArrayList<Answer> getAnswersByUserId(int author_id, int maxNumInOnePage, int pageNum) {
        return service.getAnswersByUserId(author_id, maxNumInOnePage, pageNum);
    }

    @ResponseBody
    @RequestMapping(value = "getAnswer.action", method = RequestMethod.GET)
    public Answer getAnswer(int id) {
        return service.getAnswer(id);
    }

    @ResponseBody
    @RequestMapping(value = "addAnswer.action", method = RequestMethod.GET)
    public Result addAnswer(Answer newAnswer) {
        if (service.addAnswer(newAnswer)) {
            return Result.TREU;
        }
        return Result.FALSE;
    }

    @ResponseBody
    @RequestMapping(value = "deleteAnswer.action", method = RequestMethod.GET)
    public Result deleteAnswer(int id) {
        if (service.deleteAnswer(id)) {
            return Result.TREU;
        }
        return Result.FALSE;
    }

    @ResponseBody
    @RequestMapping(value = "up_downAnswer.action", method = RequestMethod.GET)
    public Result upDownAnswer(int answer_id, int user_id, boolean up_down) {
        if (service.upDownAnswer(answer_id, user_id, up_down)) {
            return Result.TREU;
        }
        return Result.FALSE;
    }

    @ResponseBody
    @RequestMapping(value = "collectAnswer.action", method = RequestMethod.GET)
    public Result collectAnswer(int user_id, int answer_id) {
        if (service.collectAnswer(user_id, answer_id)) {
            return Result.TREU;
        }
        return Result.FALSE;
    }

    @ResponseBody
    @RequestMapping(value = "reportAnswer.action", method = RequestMethod.GET)
    public Result reportAnswer(AnswerReport report) {
        if (service.reportAnswer(report)) {
            return Result.TREU;
        }
        return Result.FALSE;
    }

}
