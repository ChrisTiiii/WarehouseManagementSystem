package com.example.administrator.warehousemanagementsystem.bean;

import java.io.Serializable;

/**
 * author: ZhongMing
 * DATE: 2018/12/13 0013
 * Description:
 **/
public class MyGoods implements Serializable {
    private String goodsTypeNo;
    private String code;
    private String name;
    private String num;

    @Override
    public String toString() {
        return "MyGoods{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", num='" + num + '\'' +
                '}';
    }

    public MyGoods(String name, String num) {
        this.name = name;
        this.num = num;
    }

    public MyGoods(String code, String name, String num) {
        this.code = code;
        this.name = name;
        this.num = num;
    }

    public MyGoods(String goodsTypeNo, String code, String name, String num) {
        this.goodsTypeNo = goodsTypeNo;
        this.code = code;
        this.name = name;
        this.num = num;
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

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getGoodsTypeNo() {
        return goodsTypeNo;
    }

    public void setGoodsTypeNo(String goodsTypeNo) {
        this.goodsTypeNo = goodsTypeNo;
    }
}
