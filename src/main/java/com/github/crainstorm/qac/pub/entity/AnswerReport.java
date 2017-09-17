package com.github.crainstorm.qac.pub.entity;

import java.sql.Timestamp;

/**
 * Created by chen on 9/17/17.
 */
public class AnswerReport {
    public int user_id;
    public int answer_id;
    public int report_reason_id;
    public String remarks;
    public boolean status;
    public Timestamp datetime;
}
