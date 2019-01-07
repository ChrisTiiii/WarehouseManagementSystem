package com.example.administrator.warehousemanagementsystem.util;

import com.example.administrator.warehousemanagementsystem.bean.ApplyBean;
import com.example.administrator.warehousemanagementsystem.bean.ApplyList;
import com.example.administrator.warehousemanagementsystem.bean.BudgetBean;
import com.example.administrator.warehousemanagementsystem.bean.BudgetList;
import com.example.administrator.warehousemanagementsystem.bean.DeptListBean;
import com.example.administrator.warehousemanagementsystem.bean.MyGoods;
import com.example.administrator.warehousemanagementsystem.bean.PurchaseBean;
import com.example.administrator.warehousemanagementsystem.bean.PurchaseList;
import com.example.administrator.warehousemanagementsystem.bean.StoreHouseReport;
import com.example.administrator.warehousemanagementsystem.bean.ReviewList;
import com.example.administrator.warehousemanagementsystem.bean.ReviewListHaveDone;
import com.example.administrator.warehousemanagementsystem.bean.StorehouseList;

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
    private List<StorehouseList.DataBean> storehouseList;//仓库名
    private List<ReviewList.DataBean> reviewLists;//待审批
    private List<ReviewListHaveDone.DataBean> haveDoneList;//已审批
    private List<ApplyList.DataBean> myApplyList;//我的申请List
    private List<PurchaseList.DataBean> purchaseList;//我的采购
    private List<BudgetList.DataBean> budgetList;//我的预算
    private ApplyBean.DataBean applyList;//我的申请单
    private PurchaseBean.DataBean purchaseBean;//我的采购单
    private BudgetBean.DataBean budgetBean;//我的预算单
    private List<StoreHouseReport> storehouseBean;//仓库库存
    private List<DeptListBean.DataBean> deptListBeanList;//收费站list

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

    public BudgetBean.DataBean getBudgetBean() {
        return budgetBean;
    }

    public void setBudgetBean(BudgetBean.DataBean budgetBean) {
        this.budgetBean = budgetBean;
    }

    public List<BudgetList.DataBean> getBudgetList() {
        return budgetList;
    }

    public void setBudgetList(List<BudgetList.DataBean> budgetList) {
        this.budgetList = budgetList;
    }

    public List<StorehouseList.DataBean> getStorehouseList() {
        return storehouseList;
    }

    public void setStorehouseList(List<StorehouseList.DataBean> storehouseList) {
        this.storehouseList = storehouseList;
    }

    public List<StoreHouseReport> getStorehouseBean() {
        return storehouseBean;
    }

    public void setStorehouseBean(List<StoreHouseReport> storehouseBean) {
        this.storehouseBean = storehouseBean;
    }

    public List<DeptListBean.DataBean> getDeptListBeanList() {
        return deptListBeanList;
    }

    public void setDeptListBeanList(List<DeptListBean.DataBean> deptListBeanList) {
        this.deptListBeanList = deptListBeanList;
    }
}