package com.github.crainstorm.qac.pub.entity;

import java.sql.Timestamp;

/**
 * Created by chen on 9/17/17.
 */
public class Answer {
    public int id;
    public int question_id;
    public int author_id;
    public String content;
    public Timestamp datetime;
    public int up;
    public int down;
    public short status;
    public int status_remarks;
    public int collect_num;
    public int comment_num;
}
