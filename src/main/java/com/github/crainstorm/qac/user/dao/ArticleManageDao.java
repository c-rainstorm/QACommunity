package com.github.crainstorm.qac.user.dao;

import com.github.crainstorm.qac.pub.entity.Article;
import com.github.crainstorm.qac.pub.entity.ArticleReport;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * Created by chen on 9/17/17.
 */
@Repository
public interface ArticleManageDao {
    ArrayList<Article> getArticlesByKeyword(String keyword, int maxNumInOnePage, int pageNum);

    int collectArticle(int user_id, int article_id);

    int reportArticle(ArticleReport report);

    ArrayList<Article> getArticlesByLabel(String label, int maxNumInOnePage, int pageNum);

    ArrayList<Article> getArticlesByUserId(int author_id, int maxNumInOnePage, int pageNum);

    Article getArticle(int id);

    int addArticle(Article newArticle);

    int updateArticle(Article article);

    int upDownArticle(int article_id, int user_id, boolean up_down);
}
