package com.github.crainstorm.qac.user.service;

import com.github.crainstorm.qac.pub.entity.AnswerComment;
import com.github.crainstorm.qac.pub.entity.ArticleComment;
import com.github.crainstorm.qac.user.dao.CommentManageDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

/**
 * Created by chen on 9/17/17.
 */
@Transactional
@Service
public class CommentManageService {

    private static Logger logger = LogManager.getLogger(CommentManageService.class);

    @Autowired
    private CommentManageDao dao;

    public ArrayList<AnswerComment> getAnswerComments(int answer_id) {
        return dao.getAnswerComments(answer_id);
    }

    public int addAnswerComment(AnswerComment comment) {
        dao.addAnswerComment(comment);
        return dao.getLatestAnswerCommentId(comment.user_id);
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

    public int addArticleComment(ArticleComment comment) {
        dao.addArticleComment(comment);
        return dao.getLatestArticleCommentId(comment.user_id);
    }

    public boolean deleteArticleComment(int id) {
        return dao.deleteArticleComment(id) == 1;
    }

    public boolean reportArticleComment(int id) {
        return dao.reportArticleComment(id) == 1;
    }
}
