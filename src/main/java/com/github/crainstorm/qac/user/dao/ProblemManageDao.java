package com.github.crainstorm.qac.user.dao;

import com.github.crainstorm.qac.pub.entity.Question;
import com.github.crainstorm.qac.pub.entity.QuestionReport;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * Created by chen on 9/16/17.
 */
@Repository
public interface ProblemManageDao {
    ArrayList<Question> getQuestionsByKeyword(String keyword, int maxNumInOnePage, int pageNum);

    ArrayList<Question> getQuestionsByLable(String label, int maxNumInOnePage, int pageNum);

    ArrayList<Question> getQuestionsByUserId(int author_id, int maxNumInOnePage, int pageNum);

    Question getQuestion(int id);

    int addQuestion(Question newQuestion);

    int updateQuestion(Question question);

    int upDownQuestion(int question_id, int user_id, boolean up_down);

    int followQuestion(int user_id, int question_id);

    int reportQuestion(QuestionReport report);
}
