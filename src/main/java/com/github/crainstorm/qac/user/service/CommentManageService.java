package com.github.crainstorm.qac.user.service;

import com.github.crainstorm.qac.pub.entity.AnswerComment;
import com.github.crainstorm.qac.pub.entity.ArticleComment;
import com.github.crainstorm.qac.user.dao.CommentManageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.ArrayList;

/**
 * Created by chen on 9/17/17.
 */
@EnableTransactionManagement
@Service
public class CommentManageService {

    @Autowired
    private CommentManageDao dao;

    public ArrayList<AnswerComment> getAnswerComments(int answer_id) {
        return dao.getAnswerComments(answer_id);
    }

    public boolean addAnswerComment(AnswerComment comment) {
        return dao.addAnswerComment(comment) == 1;
    }

    public boolean deleteAnswerComment(int id) {
        return dao.deleteAnswerComment(id) == 1;
    }

    public boolean reportAnswerComment(int id) {
        return dao.reportAnswerComment(id) == 1;
    }

    public ArrayList<ArticleComment> getArticleComments(int article_id) {
        return dao.getArticleComments(article_id);
    }

    public boolean addArticleComment(ArticleComment comment) {
        return dao.addArticleComment(comment) == 1;
    }

    public boolean deleteArticleComment(int id) {
        return dao.deleteArticleComment(id) == 1;
    }

    public boolean reportArticleComment(int id) {
        return dao.reportArticleComment(id) == 1;
    }
}
