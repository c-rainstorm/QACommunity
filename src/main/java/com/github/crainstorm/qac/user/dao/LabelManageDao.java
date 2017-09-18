package com.github.crainstorm.qac.user.dao;

import com.github.crainstorm.qac.pub.entity.Label;

import java.util.ArrayList;

/**
 * Created by chen on 9/19/17.
 */
public interface LabelManageDao {
    ArrayList<Label> geAllLabel();

    ArrayList<Label> getLabelsOfArticle(int article_id);

    ArrayList<Label> getLabelsOfQuestion(int question_id);

    ArrayList<Label> getLabelsOfAnswer(int answer_id);

    int addLabelToArticle(int article_id, int label_id);

    int deleteLabelFromArticle(int article_id, int label_id);

    boolean addLabelToQuestion(int question_id, int label_id);

    boolean deleteLabelFromQuestion(int question_id, int label_id);
}
