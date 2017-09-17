package com.github.crainstorm.qac.admin.controller;

import com.github.crainstorm.qac.admin.service.AdminArticleManageService;
import com.github.crainstorm.qac.pub.entity.ArticleReport;
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
public class AdminArticleManageController {
    
    @Autowired
    private AdminArticleManageService service;

    @ResponseBody
    @RequestMapping(value = "getArticleReportList.action", method = RequestMethod.GET)
    public ArrayList<ArticleReport> getArticleReportList() {
        return service.getArticleReportList();
    }

    @ResponseBody
    @RequestMapping(value = "shutdownArticle.action", method = RequestMethod.GET)
    public Result shutdownArticle(int article_id) {
        if (service.shutdownArticle(article_id)) {
            return Result.TREU;
        }
        return Result.FALSE;
    }

    @ResponseBody
    @RequestMapping(value = "adminDeleteArticle.action", method = RequestMethod.GET)
    public Result deleteArticle(int article_id) {
        if (service.deleteArticle(article_id)) {
            return Result.TREU;
        }
        return Result.FALSE;
    }
}
