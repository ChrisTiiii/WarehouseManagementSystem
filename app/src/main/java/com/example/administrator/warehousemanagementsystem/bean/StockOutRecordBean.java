package com.example.administrator.warehousemanagementsystem.bean;

import java.util.List;

/**
 * author: ZhongMing
 * DATE: 2019/1/4 0004
 * Description:
 **/
public class StockOutRecordBean {

    /**
     * result : ok
     * data : [{"applyNo":59,"deptName":"泰兴收费站","deptNo":1,"goodsIdNo":"6921734944573","goodsNo":28,"goodsUnit":"支","id":21,"stockOutAnnotation":"","stockOutDate":1545979090000,"stockOutDateStr":"2018-12-28 14:38:10","stockOutGoodsName":"黑笔","stockOutGoodsNum":15,"storehouseName":"生产仓库01","storehouseNo":1},{"applyNo":58,"deptName":"泰兴收费站","deptNo":1,"goodsIdNo":"6906791521028","goodsNo":27,"goodsUnit":"壶","id":20,"stockOutAnnotation":"官方电话计费很久很久共和国的诞生给客户","stockOutDate":1545978869000,"stockOutDateStr":"2018-12-28 14:34:29","stockOutGoodsName":"阿华田","stockOutGoodsNum":72,"storehouseName":"生产仓库01","storehouseNo":1},{"applyNo":58,"deptName":"泰兴收费站","deptNo":1,"goodsIdNo":"6934024515186","goodsNo":26,"goodsUnit":"瓶","id":19,"stockOutAnnotation":"官方电话计费很久很久共和国的诞生给客户","stockOutDate":1545978869000,"stockOutDateStr":"2018-12-28 14:34:29","stockOutGoodsName":"果缤纷","stockOutGoodsNum":36,"storehouseName":"生产仓库01","storehouseNo":1},{"applyNo":58,"deptName":"泰兴收费站","deptNo":1,"goodsIdNo":"6920459902387","goodsNo":25,"goodsUnit":"瓶","id":18,"stockOutAnnotation":"官方电话计费很久很久共和国的诞生给客户","stockOutDate":1545978869000,"stockOutDateStr":"2018-12-28 14:34:29","stockOutGoodsName":"冰红茶","stockOutGoodsNum":2,"storehouseName":"综合仓库03","storehouseNo":2}]
     * count : 4
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
         * applyNo : 59
         * deptName : 泰兴收费站
         * deptNo : 1
         * goodsIdNo : 6921734944573
         * goodsNo : 28
         * goodsUnit : 支
         * id : 21
         * stockOutAnnotation :
         * stockOutDate : 1545979090000
         * stockOutDateStr : 2018-12-28 14:38:10
         * stockOutGoodsName : 黑笔l.klh
         * stockOutGoodsNum : 15
         * storehouseName : 生产仓库01
         * storehouseNo : 1
         */

        private int applyNo;
        private String deptName;
        private int deptNo;
        private String goodsIdNo;
        private int goodsNo;
        private String goodsUnit;
        private int id;
        private String stockOutAnnotation;
        private long stockOutDate;
        private String stockOutDateStr;
        private String stockOutGoodsName;
        private int stockOutGoodsNum;
        private String storehouseName;
        private int storehouseNo;

        public int getApplyNo() {
            return applyNo;
        }

        public void setApplyNo(int applyNo) {
            this.applyNo = applyNo;
        }

        public String getDeptName() {
            return deptName;
        }

        public void setDeptName(String deptName) {
            this.deptName = deptName;
        }

        public int getDeptNo() {
            return deptNo;
        }

        public void setDeptNo(int deptNo) {
            this.deptNo = deptNo;
        }

        public String getGoodsIdNo() {
            return goodsIdNo;
        }

        public void setGoodsIdNo(String goodsIdNo) {
            this.goodsIdNo = goodsIdNo;
        }

        public int getGoodsNo() {
            return goodsNo;
        }

        public void setGoodsNo(int goodsNo) {
            this.goodsNo = goodsNo;
        }

        public String getGoodsUnit() {
            return goodsUnit;
        }

        public void setGoodsUnit(String goodsUnit) {
            this.goodsUnit = goodsUnit;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getStockOutAnnotation() {
            return stockOutAnnotation;
        }

        public void setStockOutAnnotation(String stockOutAnnotation) {
            this.stockOutAnnotation = stockOutAnnotation;
        }

        public long getStockOutDate() {
            return stockOutDate;
        }

        public void setStockOutDate(long stockOutDate) {
            this.stockOutDate = stockOutDate;
        }

        public String getStockOutDateStr() {
            return stockOutDateStr;
        }

        public void setStockOutDateStr(String stockOutDateStr) {
            this.stockOutDateStr = stockOutDateStr;
        }

        public String getStockOutGoodsName() {
            return stockOutGoodsName;
        }

        public void setStockOutGoodsName(String stockOutGoodsName) {
            this.stockOutGoodsName = stockOutGoodsName;
        }

        public int getStockOutGoodsNum() {
            return stockOutGoodsNum;
        }

        public void setStockOutGoodsNum(int stockOutGoodsNum) {
            this.stockOutGoodsNum = stockOutGoodsNum;
        }

        public String getStorehouseName() {
            return storehouseName;
        }

        public void setStorehouseName(String storehouseName) {
            this.storehouseName = storehouseName;
        }

        public int getStorehouseNo() {
            return storehouseNo;
        }

        public void setStorehouseNo(int storehouseNo) {
            this.storehouseNo = storehouseNo;
        }
    }
}
