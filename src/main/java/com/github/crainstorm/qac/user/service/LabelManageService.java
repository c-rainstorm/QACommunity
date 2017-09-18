package com.github.crainstorm.qac.user.service;

import com.github.crainstorm.qac.pub.entity.Label;
import com.github.crainstorm.qac.user.dao.LabelManageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

/**
 * Created by chen on 9/19/17.
 */
@Transactional
@Service
public class LabelManageService {

    @Autowired
    private LabelManageDao dao;

    public ArrayList<Label> getAllLabel() {
        return dao.geAllLabel();
    }

    public ArrayList<Label> getLabelsOfArticle(int article_id) {
        return dao.getLabelsOfArticle(article_id);
    }


    public ArrayList<Label> getLabelsOfQuestion(int question_id) {
        return dao.getLabelsOfQuestion(question_id);
    }

    public ArrayList<Label> getLabelsOfAnswer(int answer_id) {
        return dao.getLabelsOfAnswer(answer_id);
    }

    public boolean addLabelToArticle(int article_id, int label_id) {
        return dao.addLabelToArticle(article_id, label_id) == 1;
    }

    public boolean deleteLabelFromArticle(int article_id, int label_id) {
        return dao.deleteLabelFromArticle(article_id, label_id) == 1;
    }

    public boolean addLabelToQuestion(int question_id, int label_id) {
        return dao.addLabelToQuestion(question_id, label_id);
    }

    public boolean deleteLabelFromQuestion(int question_id, int label_id) {
        return dao.deleteLabelFromQuestion(question_id, label_id);
    }
}
