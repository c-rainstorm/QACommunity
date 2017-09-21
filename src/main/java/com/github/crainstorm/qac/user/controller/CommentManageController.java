package com.github.crainstorm.qac.user.controller;

import com.github.crainstorm.qac.pub.entity.AnswerComment;
import com.github.crainstorm.qac.pub.entity.ArticleComment;
import com.github.crainstorm.qac.pub.entity.Result;
import com.github.crainstorm.qac.user.service.CommentManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

/**
 * Created by chen on 9/17/17.
 */
@Controller
public class CommentManageController {

    @Autowired
    private CommentManageService service;

    //------------------------------ Answer --------------------------------

    @ResponseBody
    @RequestMapping(value = "getAnswerComments.action", method = RequestMethod.GET)
    public ArrayList<AnswerComment> getAnswerComments(int answer_id) {
        return service.getAnswerComments(answer_id);
    }

    @ResponseBody
    @RequestMapping(value = "addAnswerComment.action", method = RequestMethod.POST)
    public Result addAnswerComment(@RequestBody  AnswerComment comment) {
        if (service.addAnswerComment(comment)) {
            return Result.TREU;
        }
        return Result.FALSE;
    }

    @ResponseBody
    @RequestMapping(value = "deleteAnswerComment.action", method = RequestMethod.GET)
    public Result deleteAnswerComment(int id) {
        if (service.deleteAnswerComment(id)) {
            return Result.TREU;
        }
        return Result.FALSE;
    }

    @ResponseBody
    @RequestMapping(value = "reportAnswerComment.action", method = RequestMethod.GET)
    public Result reportAnswerComment(int id) {
        if (service.reportAnswerComment(id)) {
            return Result.TREU;
        }
        return Result.FALSE;
    }


    //------------------------------ Article -------------------------

    @ResponseBody
    @RequestMapping(value = "getArticleComments.action", method = RequestMethod.GET)
    public ArrayList<ArticleComment> getArticleComments(int article_id) {
        return service.getArticleComments(article_id);
    }

    @ResponseBody
    @RequestMapping(value = "addArticleComment.action", method = RequestMethod.POST)
    public Result addArticleComment(@RequestBody ArticleComment comment) {
        if (service.addArticleComment(comment)) {
            return Result.TREU;
        }
        return Result.FALSE;
    }

    @ResponseBody
    @RequestMapping(value = "deleteArticleComment.action", method = RequestMethod.GET)
    public Result deleteArticleComment(int id) {
        if (service.deleteArticleComment(id)) {
            return Result.TREU;
        }
        return Result.FALSE;
    }

    @ResponseBody
    @RequestMapping(value = "reportArticleComment.action", method = RequestMethod.GET)
    public Result reportArticleComment(int id) {
        if (service.reportArticleComment(id)) {
            return Result.TREU;
        }
        return Result.FALSE;
    }
}
