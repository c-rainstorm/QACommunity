package com.github.crainstorm.qac.user.dao;

import com.github.crainstorm.qac.pub.entity.Label;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * Created by chen on 9/19/17.
 */
@Repository
public interface LabelManageDao {
    ArrayList<Label> geAllLabel();

    ArrayList<Label> getLabelsOfArticle(@Param("article_id") int article_id);

    ArrayList<Label> getLabelsOfQuestion(@Param("question_id") int question_id);

    int addLabelToArticle(@Param("article_id") int article_id, @Param("label_id") int label_id);

    int deleteLabelFromArticle(@Param("article_id") int article_id, @Param("label_id") int label_id);

    boolean addLabelToQuestion(@Param("question_id") int question_id, @Param("label_id") int label_id);

    boolean deleteLabelFromQuestion(@Param("question_id") int question_id, @Param("label_id") int label_id);
}
