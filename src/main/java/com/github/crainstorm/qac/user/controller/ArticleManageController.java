package com.github.crainstorm.qac.user.controller;

import com.github.crainstorm.qac.pub.entity.Article;
import com.github.crainstorm.qac.pub.entity.ArticleReport;
import com.github.crainstorm.qac.pub.entity.Result;
import com.github.crainstorm.qac.pub.entity.ResultWithId;
import com.github.crainstorm.qac.user.service.ArticleManageService;
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
public class ArticleManageController {
    @Autowired
    private ArticleManageService service;

    @ResponseBody
    // todo method need modify
    @RequestMapping(value = "getArticlesByKeyword.action", method = RequestMethod.GET)
    public ArrayList<Article> getArticlesByKeyword(String keyword, int maxNumInOnePage, int pageNum) {
        return service.getArticlesByKeyword(keyword, maxNumInOnePage, pageNum);
    }

    @ResponseBody
    @RequestMapping(value = "getArticlesByLabel.action", method = RequestMethod.GET)
    public ArrayList<Article> getArticlesByLabel(String label, int maxNumInOnePage, int pageNum) {
        return service.getArticlesByLabel(label, maxNumInOnePage, pageNum);
    }

    @ResponseBody
    @RequestMapping(value = "getArticlesByUserId.action", method = RequestMethod.GET)
    public ArrayList<Article> getArticlesByUserId(int author_id, int maxNumInOnePage, int pageNum) {
        return service.getArticlesByUserId(author_id, maxNumInOnePage, pageNum);
    }

    @ResponseBody
    @RequestMapping(value = "getArticle.action", method = RequestMethod.GET)
    public Article getArticle(int id) {
        return service.getArticle(id);
    }

    @ResponseBody
    @RequestMapping(value = "addArticle.action", method = RequestMethod.POST)
    public ResultWithId addArticle(@RequestBody Article newArticle) {
        int articleId = service.addArticle(newArticle);
        if (articleId > 0) {
            return new ResultWithId(articleId);
        }
        return new ResultWithId();
    }

    @ResponseBody
    @RequestMapping(value = "updateArticle.action", method = RequestMethod.POST)
    public Result updateArticle(@RequestBody Article article) {
        if (service.updateArticle(article)) {
            return Result.TREU;
        }
        return Result.FALSE;
    }

    @ResponseBody
    @RequestMapping(value = "up_downArticle.action", method = RequestMethod.GET)
    public Result upDownArticle(int article_id, int user_id, boolean up_down) {
        if (service.upDownArticle(article_id, user_id, up_down)) {
            return Result.TREU;
        }
        return Result.FALSE;
    }

    @ResponseBody
    @RequestMapping(value = "collectArticle.action", method = RequestMethod.GET)
    public Result followArticle(int user_id, int article_id) {
        if (service.collectArticle(user_id, article_id)) {
            return Result.TREU;
        }
        return Result.FALSE;
    }

    @ResponseBody
    @RequestMapping(value = "reportArticle.action", method = RequestMethod.GET)
    public Result reportArticle(ArticleReport report) {
        if (service.reportArticle(report)) {
            return Result.TREU;
        }
        return Result.FALSE;
    }
}
