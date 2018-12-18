package com.example.administrator.warehousemanagementsystem.bean;

/**
 * author: ZhongMing
 * DATE: 2018/12/17 0017
 * Description:
 **/
public class BackData {
    private String msg;
    /**
     * result : ok
     */

    private String result;

    public BackData(String msg) {
        this.msg = msg;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
