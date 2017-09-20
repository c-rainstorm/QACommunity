package com.github.crainstorm.qac.user.dao;

import com.github.crainstorm.qac.pub.entity.Question;
import com.github.crainstorm.qac.pub.entity.QuestionReport;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * Created by chen on 9/16/17.
 */
@Repository
public interface ProblemManageDao {
    ArrayList<Question> getQuestionsByKeyword(@Param("keyword") String keyword, @Param("offset") int offset, @Param("maxNumInOnePage") int maxNumInOnePage);

    ArrayList<Question> getQuestionsByLabel(@Param("label_id") int label_id, @Param("offset") int offset, @Param("maxNumInOnePage") int maxNumInOnePage);

    ArrayList<Question> getQuestionsByUserId(@Param("author_id") int author_id, @Param("offset") int offset, @Param("maxNumInOnePage") int maxNumInOnePage);

    Question getQuestion(int id);

    int addQuestion(Question newQuestion);

    int updateQuestion(Question question);

    int upDownQuestion(@Param("question_id") int question_id, @Param("user_id") int user_id, @Param("up_down") boolean up_down);

    void upQuestion(int question_id);

    void downQuestion(int question_id);

    int followQuestion(@Param("user_id") int user_id,@Param("question_id") int question_id);

    int reportQuestion(QuestionReport report);

    int getQuestionFollowNum(int id);

    int getQuestionAnswerNum(int id);

    int getNewestQuestionId(int author_id);


}
