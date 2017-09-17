package com.github.crainstorm.qac.admin.service;

import com.github.crainstorm.qac.admin.dao.ReportReasonManageDao;
import com.github.crainstorm.qac.pub.entity.ReportReason;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.ArrayList;

/**
 * Created by chen on 9/17/17.
 */
@EnableTransactionManagement
@Service
public class ReportReasonManageService {
    @Autowired
    private ReportReasonManageDao dao;

    public ArrayList<ReportReason> getReportReasonList() {
        return dao.getReportReasonList();
    }

    public boolean addReportReason(ReportReason reportReason) {
        return dao.addReportReason(reportReason);
    }

    public boolean updateReportReason(ReportReason reportReason) {
        return dao.updateReportReason(reportReason) == 1;
    }

    public boolean deleteReportReason(int id) {
        return dao.deleteReportReason(id);
    }
}
