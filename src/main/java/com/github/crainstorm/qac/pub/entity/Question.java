package com.github.crainstorm.qac.pub.entity;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Created by chen on 9/16/17.
 */
public class Question {
    public int id;
    public int author_id;
    public String title;
    public String content;
    public Timestamp datetime;
    public int browse_num;
    public int up;
    public int down;
    public short status;
    public int status_remarks;
    public int follow_num;
    public int answer_num;
    public ArrayList<Label> lables;
}
