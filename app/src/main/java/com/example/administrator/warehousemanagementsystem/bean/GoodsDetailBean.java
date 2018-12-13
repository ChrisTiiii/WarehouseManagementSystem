package com.example.administrator.warehousemanagementsystem.bean;

import java.util.List;

/**
 * author: ZhongMing
 * DATE: 2018/12/7 0007
 * Description:
 **/
public class GoodsDetailBean {

    /**
     * result : ok
     * data : [{"goodsFromStorehouseNo":1,"goodsId":"6901668005731","goodsMaxApply":15,"goodsMaxApplyTime":1,"goodsMinStore":10,"goodsModel":"草莓味","goodsName":"奥利奥夹心饼干","goodsOrigin":"北京","goodsPrice":600,"goodsRawId":"6901668005731","goodsShortName":"oreo","goodsSpecification":"2包/盒装","goodsTypeNo":131,"goodsUnit":"盒","goodsWeight":116,"id":1}]
     * count : 1
     */

    private String result;
    private int count;
    private List<DataBean> data;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * goodsFromStorehouseNo : 1
         * goodsId : 6901668005731
         * goodsMaxApply : 15
         * goodsMaxApplyTime : 1
         * goodsMinStore : 10
         * goodsModel : 草莓味
         * goodsName : 奥利奥夹心饼干
         * goodsOrigin : 北京
         * goodsPrice : 600
         * goodsRawId : 6901668005731
         * goodsShortName : oreo
         * goodsSpecification : 2包/盒装
         * goodsTypeNo : 131
         * goodsUnit : 盒
         * goodsWeight : 116
         * id : 1
         */

        private int goodsFromStorehouseNo;
        private String goodsId;
        private int goodsMaxApply;
        private int goodsMaxApplyTime;
        private int goodsMinStore;
        private String goodsModel;
        private String goodsName;
        private String goodsOrigin;
        private int goodsPrice;
        private String goodsRawId;
        private String goodsShortName;
        private String goodsSpecification;
        private int goodsTypeNo;
        private String goodsUnit;
        private int goodsWeight;
        private int id;

        public int getGoodsFromStorehouseNo() {
            return goodsFromStorehouseNo;
        }

        public void setGoodsFromStorehouseNo(int goodsFromStorehouseNo) {
            this.goodsFromStorehouseNo = goodsFromStorehouseNo;
        }

        public String getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(String goodsId) {
            this.goodsId = goodsId;
        }

        public int getGoodsMaxApply() {
            return goodsMaxApply;
        }

        public void setGoodsMaxApply(int goodsMaxApply) {
            this.goodsMaxApply = goodsMaxApply;
        }

        public int getGoodsMaxApplyTime() {
            return goodsMaxApplyTime;
        }

        public void setGoodsMaxApplyTime(int goodsMaxApplyTime) {
            this.goodsMaxApplyTime = goodsMaxApplyTime;
        }

        public int getGoodsMinStore() {
            return goodsMinStore;
        }

        public void setGoodsMinStore(int goodsMinStore) {
            this.goodsMinStore = goodsMinStore;
        }

        public String getGoodsModel() {
            return goodsModel;
        }

        public void setGoodsModel(String goodsModel) {
            this.goodsModel = goodsModel;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public String getGoodsOrigin() {
            return goodsOrigin;
        }

        public void setGoodsOrigin(String goodsOrigin) {
            this.goodsOrigin = goodsOrigin;
        }

        public int getGoodsPrice() {
            return goodsPrice;
        }

        public void setGoodsPrice(int goodsPrice) {
            this.goodsPrice = goodsPrice;
        }

        public String getGoodsRawId() {
            return goodsRawId;
        }

        public void setGoodsRawId(String goodsRawId) {
            this.goodsRawId = goodsRawId;
        }

        public String getGoodsShortName() {
            return goodsShortName;
        }

        public void setGoodsShortName(String goodsShortName) {
            this.goodsShortName = goodsShortName;
        }

        public String getGoodsSpecification() {
            return goodsSpecification;
        }

        public void setGoodsSpecification(String goodsSpecification) {
            this.goodsSpecification = goodsSpecification;
        }

        public int getGoodsTypeNo() {
            return goodsTypeNo;
        }

        public void setGoodsTypeNo(int goodsTypeNo) {
            this.goodsTypeNo = goodsTypeNo;
        }

        public String getGoodsUnit() {
            return goodsUnit;
        }

        public void setGoodsUnit(String goodsUnit) {
            this.goodsUnit = goodsUnit;
        }

        public int getGoodsWeight() {
            return goodsWeight;
        }

        public void setGoodsWeight(int goodsWeight) {
            this.goodsWeight = goodsWeight;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "goodsFromStorehouseNo=" + goodsFromStorehouseNo +
                    ", goodsId='" + goodsId + '\'' +
                    ", goodsMaxApply=" + goodsMaxApply +
                    ", goodsMaxApplyTime=" + goodsMaxApplyTime +
                    ", goodsMinStore=" + goodsMinStore +
                    ", goodsModel='" + goodsModel + '\'' +
                    ", goodsName='" + goodsName + '\'' +
                    ", goodsOrigin='" + goodsOrigin + '\'' +
                    ", goodsPrice=" + goodsPrice +
                    ", goodsRawId='" + goodsRawId + '\'' +
                    ", goodsShortName='" + goodsShortName + '\'' +
                    ", goodsSpecification='" + goodsSpecification + '\'' +
                    ", goodsTypeNo=" + goodsTypeNo +
                    ", goodsUnit='" + goodsUnit + '\'' +
                    ", goodsWeight=" + goodsWeight +
                    ", id=" + id +
                    '}';
        }
    }
}
