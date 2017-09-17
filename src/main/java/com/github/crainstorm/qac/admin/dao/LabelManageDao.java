package com.github.crainstorm.qac.admin.dao;

import com.github.crainstorm.qac.pub.entity.Label;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * Created by chen on 9/17/17.
 */
@Repository
public interface LabelManageDao {

    ArrayList<Label> getLabels();

    int addLabel(Label label);

    int updateLabel(Label label);

    int deleteLabel(int id);
}
