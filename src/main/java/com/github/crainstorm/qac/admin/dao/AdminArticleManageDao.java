package com.github.crainstorm.qac.admin.dao;

import com.github.crainstorm.qac.pub.entity.ArticleReport;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * Created by chen on 9/17/17.
 */
@Repository
public interface ArticleManageDao {
    ArrayList<ArticleReport> getArticleReportList();

    int shutdownArticle(int article_id);

    boolean deleteArticle(int article_id);
}
