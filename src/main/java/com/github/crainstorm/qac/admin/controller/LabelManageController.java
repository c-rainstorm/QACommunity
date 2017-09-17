package com.github.crainstorm.qac.admin.controller;

import com.github.crainstorm.qac.admin.service.LabelManageService;
import com.github.crainstorm.qac.pub.entity.Label;
import com.github.crainstorm.qac.pub.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

/**
 * Created by chen on 9/17/17.
 */
@Controller
public class LabelManageController {

    @Autowired
    private LabelManageService service;

    @ResponseBody
    @RequestMapping(value = "getLabels.action", method = RequestMethod.GET)
    public ArrayList<Label> getLabels() {
        return service.getLabels();
    }

    @ResponseBody
    @RequestMapping(value = "addLabel.action", method = RequestMethod.GET)
    public Result addLabel(Label label) {
        if (service.addLabel(label)) {
            return Result.TREU;
        }
        return Result.FALSE;
    }

    @ResponseBody
    @RequestMapping(value = "updateLabel.action", method = RequestMethod.GET)
    public Result updateLabel(Label label){
        if(service.updateLabel(label)){
            return Result.TREU;
        }
        return Result.FALSE;
    }

    @ResponseBody
    @RequestMapping(value = "deleteLabel.action", method = RequestMethod.GET)
    public Result deleteLabel(int id){
        if(service.deleteLabel(id)){
            return Result.TREU;
        }
        return Result.FALSE;
    }

}
