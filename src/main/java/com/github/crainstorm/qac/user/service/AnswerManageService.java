package com.github.crainstorm.qac.user.service;

import com.github.crainstorm.qac.pub.entity.Answer;
import com.github.crainstorm.qac.pub.entity.AnswerReport;
import com.github.crainstorm.qac.user.dao.AnswerManageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

/**
 * Created by chen on 9/17/17.
 */
@EnableTransactionManagement
@Transactional
@Service
public class AnswerManageService {

    @Autowired
    private AnswerManageDao dao;

    public ArrayList<Answer> getAnswersByQuestionId(int question_id, int maxNumInOnePage, int pageNum) {
        return dao.getAnswersByQuestionId(question_id, maxNumInOnePage, pageNum);
    }

    public ArrayList<Answer> getAnswersByUserId(int author_id, int maxNumInOnePage, int pageNum) {
        return dao.getAnswersByUserId(author_id, maxNumInOnePage, pageNum);
    }

    public Answer getAnswer(int id) {
        return dao.getAnswer(id);
    }

    public boolean addAnswer(Answer newAnswer) {
        return dao.addAnswer(newAnswer) == 1;
    }

    public boolean deleteAnswer(int id) {
        return dao.deleteAnswer(id) == 1;
    }

    public boolean upDownAnswer(int answer_id, int user_id, boolean up_down) {
        return dao.upDownAnswer(answer_id, user_id, up_down) == 1;
    }

    public boolean collectAnswer(int user_id, int answer_id) {
        return dao.collectAnswer(user_id, answer_id) == 1;
    }

    public boolean reportAnswer(AnswerReport report) {
        return dao.reportAnswer(report) == 1;
    }
}
