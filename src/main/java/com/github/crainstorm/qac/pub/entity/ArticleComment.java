package com.github.crainstorm.qac.pub.entity;

import java.sql.Timestamp;

/**
 * Created by chen on 9/17/17.
 */
public class ArticleComment {
    public int id;
    public int user_id;
    public int article_id;
    public String content;
    public Timestamp datetime;
    public int up;
    public int down;
    public int reply_comment_id;
}
