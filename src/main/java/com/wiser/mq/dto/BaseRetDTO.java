package com.wiser.mq.dto;

import java.util.List;

public class BaseRetDTO<T> {

    private String code;
    private String msg;
    private List<T> dataList;
    private T data;


    public  BaseRetDTO<T>  ok(String aCode,String aMsg)
    {
        setCode(aCode);
        setMsg(aMsg);

        return this;
    }

    public BaseRetDTO<T> err(String aCode,String aMsg)
    {
        setCode(aCode);
        setMsg(aMsg);

        return this;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
