package com.nineya.springboot.DTO;

import com.github.pagehelper.Page;

import java.util.HashMap;
import java.util.Map;

/**
 * 项目名称：springboot
 * 类 名 称：ResultDto
 * 类 描 述：TODO
 * 创建时间：2023/7/27 下午6:24
 * 创 建 人：wteng
 */

public class ResultDto<T> {
    private int code;
    private long total;
    private String message;
    private T data;


    private Map map = new HashMap(); //动态数据

    public ResultDto(){};

    public static <T> ResultDto<T> success(T object) {
        ResultDto<T> r = new ResultDto<T>();
        r.data = object;
        r.code = 200;
        return r;
    }

    public static <T> ResultDto<T> error(String message) {
        ResultDto r = new ResultDto();
        r.message = message;
        r.code = 500;
        return r;
    }

    public ResultDto<T> add(String key, Object value) {
        this.map.put(key, value);
        return this;
    }
    public ResultDto(T data){
        if(data instanceof Page){
            Page<?> page = (Page)data;
            this.total = page.getTotal();
        }
        this.data = data;
    }

    public ResultDto(long total, T data){
        this.total = total;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
