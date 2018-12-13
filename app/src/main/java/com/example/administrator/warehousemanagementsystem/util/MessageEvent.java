package com.example.administrator.warehousemanagementsystem.util;

import com.example.administrator.warehousemanagementsystem.bean.MyGoods;

import java.util.List;
import java.util.Map;

/**
 * author: ZhongMing
 * DATE: 2018/11/21 0021
 * Description:
 **/
public class MessageEvent {
    private int tag;
    private String message;
    private List<Map<String, Object>> mapList;
    private List<MyGoods> goodsList;

    public MessageEvent(int tag, List<MyGoods> goodsList) {
        this.tag = tag;
        this.goodsList = goodsList;
    }

    public List<MyGoods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<MyGoods> goodsList) {
        this.goodsList = goodsList;
    }
    //    private List<String> nameList;
//    private List<String> numlist;

//    public MessageEvent(int tag, List<String> nameList, List<String> numlist) {
//        this.tag = tag;
//        this.nameList = nameList;
//        this.numlist = numlist;
//    }


    public MessageEvent(int tag, String message) {
        this.tag = tag;
        this.message = message;
    }

    public MessageEvent(int tag, List<Map<String, Object>> mapList, int t) {
        this.tag = tag;
        this.mapList = mapList;
    }

    public List<Map<String, Object>> getMapList() {
        return mapList;
    }

    public void setMapList(List<Map<String, Object>> mapList) {
        this.mapList = mapList;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

//    public List<String> getNameList() {
//        return nameList;
//    }
//
//    public void setNameList(List<String> nameList) {
//        this.nameList = nameList;
//    }
//
//    public List<String> getNumlist() {
//        return numlist;
//    }
//
//    public void setNumlist(List<String> numlist) {
//        this.numlist = numlist;
//    }


}