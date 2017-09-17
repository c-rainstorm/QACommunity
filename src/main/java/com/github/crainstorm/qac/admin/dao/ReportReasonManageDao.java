package com.github.crainstorm.qac.admin.dao;

import com.github.crainstorm.qac.pub.entity.ReportReason;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * Created by chen on 9/17/17.
 */
@Repository
public interface ReportReasonManageDao {
    ArrayList<ReportReason> getReportReasonList();

    boolean addReportReason(ReportReason reportReason);

    int updateReportReason(ReportReason reportReason);

    boolean deleteReportReason(int id);
}
