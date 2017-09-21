package com.github.crainstorm.qac.pub.entity;

/**
 * Created by chen on 9/21/17.
 */
public class ResultWithId {
    public boolean result;
    public int id;

    public ResultWithId(int id) {
        this.result = true;
        this.id = id;
    }

    public ResultWithId() {
        this.result = false;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
