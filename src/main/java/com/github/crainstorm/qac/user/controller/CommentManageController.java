package com.github.crainstorm.qac.user.controller;

import com.github.crainstorm.qac.pub.entity.AnswerComment;
import com.github.crainstorm.qac.pub.entity.ArticleComment;
import com.github.crainstorm.qac.pub.entity.Result;
import com.github.crainstorm.qac.pub.entity.ResultWithId;
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
    public ResultWithId addAnswerComment(@RequestBody  AnswerComment comment) {
        int commentId = service.addAnswerComment(comment);
        if(commentId > 0) {
            return new ResultWithId(commentId);
        }
        return new ResultWithId();
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
    public ResultWithId addArticleComment(@RequestBody ArticleComment comment) {
        int commentId = service.addArticleComment(comment);
        if(commentId > 0) {
            return new ResultWithId(commentId);
        }
        return new ResultWithId();
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
