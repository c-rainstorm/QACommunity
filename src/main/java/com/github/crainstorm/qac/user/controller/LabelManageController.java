package com.github.crainstorm.qac.user.controller;

import com.github.crainstorm.qac.pub.entity.Label;
import com.github.crainstorm.qac.pub.entity.Result;
import com.github.crainstorm.qac.user.service.LabelManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

/**
 * Created by chen on 9/19/17.
 */
@Controller
public class LabelManageController {

    @Autowired
    private LabelManageService service;

    @ResponseBody
    @RequestMapping(value = "getAllLabel.action", method = RequestMethod.GET)
    public ArrayList<Label> getAllLabel() {
        return service.getAllLabel();
    }



    @ResponseBody
    @RequestMapping(value = "getLabelsOfArticle.action", method = RequestMethod.GET)
    public ArrayList<Label> getLabelsOfArticle(int article_id) {
        return service.getLabelsOfArticle(article_id);
    }

    @ResponseBody
    @RequestMapping(value = "addLabelToArticle.action", method = RequestMethod.GET)
    public Result addLabelToArticle(int article_id, int label_id) {
        if (service.addLabelToArticle(article_id, label_id)) {
            return Result.TREU;
        }
        return Result.FALSE;
    }

    @ResponseBody
    @RequestMapping(value = "deleteLabelFromArticle.action", method = RequestMethod.GET)
    public Result deleteLabelFromArticle(int article_id, int label_id) {
        if (service.deleteLabelFromArticle(article_id, label_id)) {
            return Result.TREU;
        }
        return Result.FALSE;
    }

    // getLabelsOfQuestion
    @ResponseBody
    @RequestMapping(value = "getLabelsOfQuestion.action", method = RequestMethod.GET)
    public ArrayList<Label> getLabelsOfQuestion(int question_id) {
        return service.getLabelsOfQuestion(question_id);
    }

    @ResponseBody
    @RequestMapping(value = "addLabelToQuestion.action", method = RequestMethod.GET)
    public Result addLabelToQuestion(int question_id, int label_id) {
        if (service.addLabelToQuestion(question_id, label_id)) {
            return Result.TREU;
        }
        return Result.FALSE;
    }

    @ResponseBody
    @RequestMapping(value = "deleteLabelFromQuestion.action", method = RequestMethod.GET)
    public Result deleteLabelFromQuestion(int question_id, int label_id) {
        if (service.deleteLabelFromQuestion(question_id, label_id)) {
            return Result.TREU;
        }
        return Result.FALSE;
    }

}
