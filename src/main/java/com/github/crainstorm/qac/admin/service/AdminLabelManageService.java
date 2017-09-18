package com.github.crainstorm.qac.admin.service;

import com.github.crainstorm.qac.admin.dao.AdminLabelManageDao;
import com.github.crainstorm.qac.pub.entity.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.ArrayList;

/**
 * Created by chen on 9/17/17.
 */
@EnableTransactionManagement
@Service
public class AdminLabelManageService {

    @Autowired
    private AdminLabelManageDao dao;

    public ArrayList<Label> getLabels() {
        return dao.getLabels();
    }

    public boolean addLabel(Label label) {
        return dao.addLabel(label) == 1;
    }

    public boolean updateLabel(Label label) {
        return dao.updateLabel(label) == 1;
    }

    public boolean deleteLabel(int id) {
        return dao.deleteLabel(id) == 1;
    }
}
