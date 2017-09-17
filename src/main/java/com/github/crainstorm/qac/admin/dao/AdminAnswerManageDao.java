package com.github.crainstorm.qac.admin.dao;

import com.github.crainstorm.qac.pub.entity.AnswerReport;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * Created by chen on 9/17/17.
 */
@Repository
public interface AdminAnswerManageDao {
    ArrayList<AnswerReport> getAnswerReportList();

    int shutdownAnswer(int answer_id);

    int deleteAnswer(int answer_id);
}
