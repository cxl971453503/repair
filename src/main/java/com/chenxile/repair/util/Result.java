package com.chenxile.repair.util;

import lombok.Data;

import java.util.Date;

@Data
public class Result<T> {

    private Integer code;
    private String message;
    private Integer total;
    private T data;

    public Result(StatusCode statusCode,T data){
        this.code = statusCode.getCode();
        this.message = statusCode.getMessage();
        this.data = data;
    }
    public Result(StatusCode statusCode) {
        this(statusCode,null);
    }
    public static Result success() {
        return new Result<>(StatusCode.SUCCESS,null);
    }
    public static <E> Result success(E data){
        return new Result<>(StatusCode.SUCCESS,data);
    }

    public static <E> Result<E> fail(StatusCode statusCode,E data) {

        return new Result<>(statusCode,data);
    }

    public static Result fail(StatusCode statusCode) {
        return   new Result<>(statusCode,null);
    }

}
