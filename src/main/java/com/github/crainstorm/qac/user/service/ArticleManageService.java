package com.github.crainstorm.qac.user.service;

import com.github.crainstorm.qac.pub.entity.Article;
import com.github.crainstorm.qac.pub.entity.ArticleReport;
import com.github.crainstorm.qac.user.dao.ArticleManageDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

/**
 * Created by chen on 9/17/17.
 */
public class ArticleManageService {

    @Autowired
    private ArticleManageDao dao;

    public ArrayList<Article> getArticlesByKeyword(String keyword, int maxNumInOnePage, int pageNum) {
        return dao.getArticlesByKeyword(keyword, maxNumInOnePage, pageNum);
    }

    public ArrayList<Article> getArticlesByLabel(String label, int maxNumInOnePage, int pageNum) {
        return dao.getArticlesByLabel(label, maxNumInOnePage, pageNum);
    }

    public ArrayList<Article> getArticlesByUserId(int author_id, int maxNumInOnePage, int pageNum) {
        return dao.getArticlesByUserId(author_id, maxNumInOnePage, pageNum);
    }

    public Article getArticle(int id) {
        return dao.getArticle(id);
    }

    public boolean addArticle(Article newArticle) {
        return dao.addArticle(newArticle) == 1;
    }

    public boolean updateArticle(Article article) {
        return dao.updateArticle(article) == 1;
    }

    public boolean upDownArticle(int article_id, int user_id, boolean up_down) {
        return dao.upDownArticle(article_id, user_id, up_down) == 1;
    }

    public boolean reportArticle(ArticleReport report) {
        return dao.reportArticle(report) == 1;
    }

    public boolean collectArticle(int user_id, int article_id) {
        return dao.collectArticle(user_id, article_id) == 1;
    }
}
