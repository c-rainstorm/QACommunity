package com.github.crainstorm.qac.admin.service;

import com.github.crainstorm.qac.pub.entity.ArticleReport;
import com.github.crainstorm.qac.admin.dao.ArticleManageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.ArrayList;

/**
 * Created by chen on 9/17/17.
 */
@EnableTransactionManagement
@Service
public class ArticleManageService {

    @Autowired
    private ArticleManageDao dao;

    public ArrayList<ArticleReport> getArticleReportList() {
        return dao.getArticleReportList();
    }

    public boolean shutdownArticle(int article_id) {
        return dao.shutdownArticle(article_id) == 1;
    }

    public boolean deleteArticle(int article_id) {
        return dao.deleteArticle(article_id);
    }
}
