package com.github.crainstorm.qac.admin.dao;

import com.github.crainstorm.qac.pub.entity.QuestionReport;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * Created by chen on 9/17/17.
 */

@Repository
public interface AdminQuestionManageDao {
    ArrayList<QuestionReport> getQuestionReportList();

    boolean shutdownQuestion(int question_id);

    boolean deleteQuestion(int question_id);
}
