package com.github.crainstorm.qac.admin.service;

import com.github.crainstorm.qac.admin.dao.AdminAnswerManageDao;
import com.github.crainstorm.qac.pub.entity.AnswerReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.ArrayList;

/**
 * Created by chen on 9/17/17.
 */

@EnableTransactionManagement
@Service
public class AdminAnswerManageService {

    @Autowired
    private AdminAnswerManageDao dao;

    public ArrayList<AnswerReport> getAnswerReportList() {
        return dao.getAnswerReportList();
    }

    public boolean shutdownAnswer(int answer_id) {
        return dao.shutdownAnswer(answer_id) == 1;
    }

    public boolean deleteAnswer(int answer_id) {
        return dao.deleteAnswer(answer_id) == 1;
    }
}
