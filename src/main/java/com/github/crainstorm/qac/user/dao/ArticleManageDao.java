package com.github.crainstorm.qac.user.dao;

import com.github.crainstorm.qac.pub.entity.Article;
import com.github.crainstorm.qac.pub.entity.ArticleReport;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * Created by chen on 9/17/17.
 */
@Repository
public interface ArticleManageDao {
    ArrayList<Article> getArticlesByKeyword(@Param("keyword") String keyword, @Param("offset") int offset, @Param("maxNumInOnePage") int maxNumInOnePage);

    ArrayList<Article> getArticlesByLabel(@Param("label") String label, @Param("offset") int offset, @Param("maxNumInOnePage") int maxNumInOnePage);

    ArrayList<Article> getArticlesByUserId(@Param("author_id") int author_id, @Param("offset") int offset, @Param("maxNumInOnePage") int maxNumInOnePage);

    Article getArticle(int id);

    int addArticle(Article newArticle);

    int collectArticle(@Param("user_id") int user_id, @Param("article_id") int article_id);

    int reportArticle(ArticleReport report);


    int updateArticle(Article article);


    int upDownArticle(@Param("article_id") int article_id, @Param("user_id") int user_id, @Param("up_down") int up_down);

    void upArticle(int article_id);

    void downArticle(int article_id);

    int getNewestArticleId(int author_id);

    int getArticleCollectNumById(int id);

    int getArticleCommentNumById(int id);
}
