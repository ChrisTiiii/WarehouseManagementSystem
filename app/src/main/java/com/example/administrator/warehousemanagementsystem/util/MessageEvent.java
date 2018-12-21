package com.example.administrator.warehousemanagementsystem.util;

import com.example.administrator.warehousemanagementsystem.bean.ApplyBean;
import com.example.administrator.warehousemanagementsystem.bean.ApplyList;
import com.example.administrator.warehousemanagementsystem.bean.MyGoods;
import com.example.administrator.warehousemanagementsystem.bean.PurchaseBean;
import com.example.administrator.warehousemanagementsystem.bean.PurchaseList;
import com.example.administrator.warehousemanagementsystem.bean.ReviewList;
import com.example.administrator.warehousemanagementsystem.bean.ReviewListHaveDone;

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
    private List<ReviewList.DataBean> reviewLists;
    private List<ReviewListHaveDone.DataBean> haveDoneList;
    private List<ApplyList.DataBean> myApplyList;
    private List<PurchaseList.DataBean> purchaseList;
    private ApplyBean.DataBean applyList;//我的申请单
    private PurchaseBean.DataBean purchaseBean;//我的采购单


    public MessageEvent(int tag) {
        this.tag = tag;
    }


    public MessageEvent(int tag, String message) {
        this.tag = tag;
        this.message = message;
    }

    public MessageEvent(int tag, List<Map<String, Object>> mapList) {
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

    public List<MyGoods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<MyGoods> goodsList) {
        this.goodsList = goodsList;
    }

    public List<ReviewList.DataBean> getReviewLists() {
        return reviewLists;
    }

    public void setReviewLists(List<ReviewList.DataBean> reviewLists) {
        this.reviewLists = reviewLists;
    }

    public ApplyBean.DataBean getApplyList() {
        return applyList;
    }

    public void setApplyList(ApplyBean.DataBean applyList) {
        this.applyList = applyList;
    }

    public List<ReviewListHaveDone.DataBean> getHaveDoneList() {
        return haveDoneList;
    }

    public void setHaveDoneList(List<ReviewListHaveDone.DataBean> haveDoneList) {
        this.haveDoneList = haveDoneList;
    }

    public List<ApplyList.DataBean> getMyApplyList() {
        return myApplyList;
    }

    public void setMyApplyList(List<ApplyList.DataBean> myApplyList) {
        this.myApplyList = myApplyList;
    }

    public List<PurchaseList.DataBean> getPurchaseList() {
        return purchaseList;
    }

    public void setPurchaseList(List<PurchaseList.DataBean> purchaseList) {
        this.purchaseList = purchaseList;
    }

    public PurchaseBean.DataBean getPurchaseBean() {
        return purchaseBean;
    }

    public void setPurchaseBean(PurchaseBean.DataBean purchaseBean) {
        this.purchaseBean = purchaseBean;
    }
}