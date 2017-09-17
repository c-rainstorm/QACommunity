package com.github.crainstorm.qac.user.dao;

import com.github.crainstorm.qac.pub.entity.AnswerComment;
import com.github.crainstorm.qac.pub.entity.ArticleComment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * Created by chen on 9/17/17.
 */
@Repository
public interface CommentManageDao {
    ArrayList<AnswerComment> getAnswerComments(int answer_id);

    int addAnswerComment(AnswerComment comment);

    int deleteAnswerComment(int id);

    int reportAnswerComment(int id);

    ArrayList<ArticleComment> getArticleComments(int article_id);

    int addArticleComment(ArticleComment comment);

    int deleteArticleComment(int id);

    int reportArticleComment(int id);
}
