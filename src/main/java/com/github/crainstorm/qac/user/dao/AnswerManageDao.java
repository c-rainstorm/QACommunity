package com.github.crainstorm.qac.user.dao;

import com.github.crainstorm.qac.pub.entity.Answer;
import com.github.crainstorm.qac.pub.entity.AnswerReport;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * Created by chen on 9/17/17.
 */
@Repository
public interface AnswerManageDao {
    ArrayList<Answer> getAnswersByQuestionId(int question_id, int maxNumInOnePage, int pageNum);

    ArrayList<Answer> getAnswersByUserId(int author_id, int maxNumInOnePage, int pageNum);

    Answer getAnswer(int id);

    int addAnswer(Answer newAnswer);

    int deleteAnswer(int id);

    int upDownAnswer(int answer_id, int user_id, boolean up_down);

    int collectAnswer(int user_id, int answer_id);

    int reportAnswer(AnswerReport report);
}
