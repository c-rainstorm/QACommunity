package com.github.crainstorm.qac.user.dao;

import com.github.crainstorm.qac.pub.entity.Answer;
import com.github.crainstorm.qac.pub.entity.AnswerReport;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * Created by chen on 9/17/17.
 */
@Repository
public interface AnswerManageDao {
    ArrayList<Answer> getAnswersByQuestionId(@Param("question_id") int question_id, @Param("offset") int offset, @Param("maxNumInOnePage") int maxNumInOnePage);

    ArrayList<Answer> getAnswersByUserId(@Param("author_id") int author_id, @Param("offset") int offset, @Param("maxNumInOnePage") int maxNumInOnePage);

    Answer getAnswer(int id);

    int addAnswer(Answer newAnswer);

    int deleteAnswer(int id);

    int upDownAnswer(@Param("answer_id") int answer_id, @Param("user_id") int user_id, @Param("up_down") int up_down);

    void upAnswer(int answer_id);

    void downAnswer(int answer_id);

    int collectAnswer(@Param("user_id") int user_id, @Param("answer_id") int answer_id);

    int reportAnswer(AnswerReport report);

    int updateAnswer(Answer answer);


    int getAnswerCollectNumById(int id);

    int getAnswerCommentNumById(int id);
}
