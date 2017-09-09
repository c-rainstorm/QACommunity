package com.github.crainstorm.qac.pub.entity;

/**
 * Created by chen on 9/9/17.
 */
public class Result {
    public static Result TREU = new Result("true");
    public static Result FALSE = new Result("false");
    public String result;

    public Result(String result) {
        this.result = result;
    }
}
