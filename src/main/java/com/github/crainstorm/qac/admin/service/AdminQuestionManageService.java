package com.github.crainstorm.qac.admin.service;

import com.github.crainstorm.qac.admin.dao.AdminQuestionManageDao;
import com.github.crainstorm.qac.pub.entity.QuestionReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.ArrayList;

/**
 * Created by chen on 9/17/17.
 */
@EnableTransactionManagement
@Service
public class AdminQuestionManageService {

    @Autowired
    private AdminQuestionManageDao dao;

    public ArrayList<QuestionReport> getQuestionReportList() {
        return dao.getQuestionReportList();
    }

    public boolean shutdownQuestion(int question_id) {
        return dao.shutdownQuestion(question_id);
    }

    public boolean deleteQuestion(int question_id) {
        return dao.deleteQuestion(question_id);
    }
}
