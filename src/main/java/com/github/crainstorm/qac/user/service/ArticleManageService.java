package com.github.crainstorm.qac.user.service;

import com.github.crainstorm.qac.pub.entity.Article;
import com.github.crainstorm.qac.pub.entity.ArticleReport;
import com.github.crainstorm.qac.user.dao.ArticleManageDao;
import com.github.crainstorm.qac.user.dao.LabelManageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

/**
 * Created by chen on 9/17/17.
 */
@Transactional
@Service
public class ArticleManageService {

    @Autowired
    private ArticleManageDao dao;
    @Autowired
    private LabelManageDao labelManageDao;

    public ArrayList<Article> getArticlesByKeyword(String keyword, int maxNumInOnePage, int pageNum) {
        ArrayList<Article> articles = dao.getArticlesByKeyword(
                "%" + keyword + "%", maxNumInOnePage * (pageNum - 1), maxNumInOnePage);
        getArticleCollectAndCommentNum(articles);
        return articles;
    }

    private void getArticleCollectAndCommentNum(ArrayList<Article> articles) {
        for (int i = 0; i < articles.size(); ++i) {
            Article article = articles.get(i);
            article.collect_num = dao.getArticleCollectNumById(article.id);
            article.comment_num = dao.getArticleCommentNumById(article.id);
        }
    }

    public ArrayList<Article> getArticlesByLabel(String label, int maxNumInOnePage, int pageNum) {
        ArrayList<Article> articles = dao.getArticlesByLabel(label, maxNumInOnePage * (pageNum - 1), maxNumInOnePage);
        getArticleCollectAndCommentNum(articles);
        return articles;
    }

    public ArrayList<Article> getArticlesByUserId(int author_id, int maxNumInOnePage, int pageNum) {
        ArrayList<Article> articles = dao.getArticlesByUserId(author_id,
                maxNumInOnePage * (pageNum - 1), maxNumInOnePage);
        getArticleCollectAndCommentNum(articles);
        return articles;
    }

    public Article getArticle(int id) {
        Article article = dao.getArticle(id);
        article.comment_num = dao.getArticleCommentNumById(id);
        article.collect_num = dao.getArticleCollectNumById(id);
        return article;
    }

    public boolean addArticle(Article newArticle) {
        dao.addArticle(newArticle);
        int article_id = dao.getNewestArticleId(newArticle.author_id);
        for (int i = 0; i < newArticle.labels.size(); ++i) {
            labelManageDao.addLabelToArticle(article_id, newArticle.labels.get(i).id);
        }
        return true;
    }

    public boolean updateArticle(Article article) {
        return dao.updateArticle(article) == 1;
    }

    public boolean upDownArticle(int article_id, int user_id, boolean up_down) {
        int upDown = up_down ? 1 : -1;
        if (up_down) {
            dao.upArticle(article_id);
        } else {
            dao.downArticle(article_id);
        }
        return dao.upDownArticle(article_id, user_id, upDown) == 1;
    }

    public boolean reportArticle(ArticleReport report) {
        return dao.reportArticle(report) == 1;
    }

    public boolean collectArticle(int user_id, int article_id) {
        return dao.collectArticle(user_id, article_id) == 1;
    }
}
