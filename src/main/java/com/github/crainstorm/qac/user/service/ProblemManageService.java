package com.github.crainstorm.qac.user.service;

import com.github.crainstorm.qac.pub.entity.Question;
import com.github.crainstorm.qac.pub.entity.QuestionReport;
import com.github.crainstorm.qac.user.dao.LabelManageDao;
import com.github.crainstorm.qac.user.dao.ProblemManageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

/**
 * Created by chen on 9/16/17.
 */
@Transactional
@Service
public class ProblemManageService {

    @Autowired
    private ProblemManageDao dao;
    @Autowired
    private LabelManageDao labelManageDao;

    public ArrayList<Question> getQuestionsByKeyword(String keyword, int maxNumInOnePage, int pageNum) {
        ArrayList<Question> questions = dao.getQuestionsByKeyword("%" + keyword + "%", maxNumInOnePage, pageNum);
        getQuestionFollowAndAnswerNum(questions);
        return questions;
    }

    private void getQuestionFollowAndAnswerNum(ArrayList<Question> questions) {
        for (int i = 0; i < questions.size(); ++i) {
            Question question = questions.get(i);
            question.answer_num = dao.getQuestionAnswerNum(question.id);
            question.follow_num = dao.getQuestionFollowNum(question.id);
        }
    }

    public ArrayList<Question> getQuestionsByLabel(int label_id, int maxNumInOnePage, int pageNum) {
        ArrayList<Question> questions = dao.getQuestionsByLabel(label_id, maxNumInOnePage, pageNum);
        getQuestionFollowAndAnswerNum(questions);
        return questions;
    }

    public ArrayList<Question> getQuestionsByUserId(int author_id, int maxNumInOnePage, int pageNum) {
        ArrayList<Question> questions = dao.getQuestionsByUserId(author_id, maxNumInOnePage, pageNum);
        getQuestionFollowAndAnswerNum(questions);
        return questions;
    }

    public Question getQuestion(int id) {
        Question question = dao.getQuestion(id);
        if (question != null) {
            question.follow_num = dao.getQuestionFollowNum(id);
            question.answer_num = dao.getQuestionAnswerNum(id);
            question.labels.addAll(labelManageDao.getLabelsOfQuestion(id));
        }
        return dao.getQuestion(id);
    }

    public boolean addQuestion(Question newQuestion) {
        dao.addQuestion(newQuestion);
        int question_id = dao.getNewestQuestionId(newQuestion.author_id);
        for (int i = 0; i < newQuestion.labels.size(); ++i) {
            labelManageDao.addLabelToQuestion(question_id, newQuestion.labels.get(i).id);
        }
        return true;
    }

    public boolean updateQuestion(Question question) {
        return dao.updateQuestion(question) == 1;
    }

    public boolean upDownQuestion(int question_id, int user_id, boolean up_down) {
        if (up_down) {
            dao.upQuestion(question_id);
        } else {
            dao.downQuestion(question_id);
        }
        return dao.upDownQuestion(question_id, user_id, up_down) == 1;
    }

    public boolean followQuestion(int user_id, int question_id) {
        return dao.followQuestion(user_id, question_id) == 1;
    }

    public boolean reportQuestion(QuestionReport report) {
        return dao.reportQuestion(report) == 1;
    }
}
