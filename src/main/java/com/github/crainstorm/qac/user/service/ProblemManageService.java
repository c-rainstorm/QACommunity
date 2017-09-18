package com.github.crainstorm.qac.user.service;

import com.github.crainstorm.qac.pub.entity.Question;
import com.github.crainstorm.qac.pub.entity.QuestionReport;
import com.github.crainstorm.qac.user.dao.ProblemManageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

/**
 * Created by chen on 9/16/17.
 */
@EnableTransactionManagement
@Transactional
@Service
public class ProblemManageService {

    @Autowired
    private ProblemManageDao dao;

    public ArrayList<Question> getQuestionsByKeyword(String keyword, int maxNumInOnePage, int pageNum) {
        return dao.getQuestionsByKeyword(keyword, maxNumInOnePage, pageNum);
    }

    public ArrayList<Question> getQuestionsByLabel(String label, int maxNumInOnePage, int pageNum) {
        return dao.getQuestionsByLable(label, maxNumInOnePage, pageNum);
    }

    public ArrayList<Question> getQuestionsByUserId(int author_id, int maxNumInOnePage, int pageNum) {
        return dao.getQuestionsByUserId(author_id, maxNumInOnePage, pageNum);
    }

    public Question getQuestion(int id) {
        return dao.getQuestion(id);
    }

    // todo labels
    public boolean addQuestion(Question newQuestion) {
        return dao.addQuestion(newQuestion) == 1;
    }

    public boolean updateQuestion(Question question) {
        return dao.updateQuestion(question) == 1;
    }

    public boolean upDownQuestion(int question_id, int user_id, boolean up_down) {
        return dao.upDownQuestion(question_id, user_id, up_down) == 1;
    }

    public boolean followQuestion(int user_id, int question_id) {
        return dao.followQuestion(user_id, question_id) == 1;
    }

    public boolean reportQuestion(QuestionReport report) {
        return dao.reportQuestion(report) == 1;
    }
}
