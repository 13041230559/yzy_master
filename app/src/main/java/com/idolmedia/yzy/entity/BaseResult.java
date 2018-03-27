package com.idolmedia.yzy.entity;

import java.io.Serializable;

/**
 * Created by WYK on 2017/7/29.
 */

public class BaseResult<T> {
    /**
     * 泛型
     */
        private int resultCode;
        private String msg;

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getVf_code() {
        return vf_code;
    }

    public void setVf_code(String vf_code) {
        this.vf_code = vf_code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    private String vf_code;
                T data;

}
