package com.example.administrator.warehousemanagementsystem.bean;

/**
 * author: ZhongMing
 * DATE: 2018/12/14 0014
 * Description:选择审批人
 **/
public class MyLeader {
    private String code;
    private String name;

    public MyLeader(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
