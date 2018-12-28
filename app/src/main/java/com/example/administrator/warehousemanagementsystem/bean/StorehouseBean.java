package com.example.administrator.warehousemanagementsystem.bean;

import java.util.List;

/**
 * author: ZhongMing
 * DATE: 2018/12/28 0028
 * Description:
 **/
public class StorehouseBean {

    /**
     * result : ok
     * data : [{"goodsIdNo":"6901668005731","goodsName":"奥利奥夹心饼干","goodsNo":1,"goodsUnit":"盒","id":1,"stockAnnotation":"","stockNum":50,"storehouseName":"生产仓库01","storehouseNo":1},{"goodsIdNo":"6906791521028","goodsName":"阿华田","goodsNo":2,"goodsUnit":"罐","id":2,"stockAnnotation":"","stockNum":33,"storehouseName":"生产仓库01","storehouseNo":1}]
     * count : 2
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
         * goodsIdNo : 6901668005731
         * goodsName : 奥利奥夹心饼干
         * goodsNo : 1
         * goodsUnit : 盒
         * id : 1
         * stockAnnotation :
         * stockNum : 50
         * storehouseName : 生产仓库01
         * storehouseNo : 1
         */

        private String goodsIdNo;
        private String goodsName;
        private int goodsNo;
        private String goodsUnit;
        private int id;
        private String stockAnnotation;
        private int stockNum;
        private String storehouseName;
        private int storehouseNo;

        public String getGoodsIdNo() {
            return goodsIdNo;
        }

        public void setGoodsIdNo(String goodsIdNo) {
            this.goodsIdNo = goodsIdNo;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
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

        public String getStockAnnotation() {
            return stockAnnotation;
        }

        public void setStockAnnotation(String stockAnnotation) {
            this.stockAnnotation = stockAnnotation;
        }

        public int getStockNum() {
            return stockNum;
        }

        public void setStockNum(int stockNum) {
            this.stockNum = stockNum;
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
