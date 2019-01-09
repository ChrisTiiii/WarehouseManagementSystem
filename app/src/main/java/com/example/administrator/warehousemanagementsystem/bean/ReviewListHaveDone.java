package com.example.administrator.warehousemanagementsystem.bean;

import java.util.List;

/**
 * author: ZhongMing
 * DATE: 2018/12/17 0017
 * Description:
 **/
public class ReviewListHaveDone {

    /**
     * result : ok
     * data : [{"fromUserName":"虞勇-仓管","goodsCount":2,"id":18,"objNo":5,"reviewDate":1547026075000,"reviewIndex":0,"reviewReason":"","reviewState":"通过","reviewStateNo":601,"reviewType":"采购","reviewTypeNo":310,"reviewUserName":"","startDate":1547026062000,"userNo":5},{"fromUserName":"虞勇-仓管","goodsCount":2,"id":15,"objNo":4,"reviewDate":1547025974000,"reviewIndex":0,"reviewReason":"","reviewState":"通过","reviewStateNo":601,"reviewType":"采购","reviewTypeNo":310,"reviewUserName":"","startDate":1547025946000,"userNo":5},{"fromUserName":"浦头收费站仓库","goodsCount":1,"id":11,"objNo":1,"reviewDate":1547024689000,"reviewIndex":0,"reviewReason":"","reviewState":"通过","reviewStateNo":601,"reviewType":"预算","reviewTypeNo":320,"reviewUserName":"","startDate":1547024380000,"userNo":5},{"fromUserName":"浦头收费站仓库","goodsCount":1,"id":9,"objNo":6,"reviewDate":1547024774000,"reviewIndex":0,"reviewReason":"","reviewState":"通过","reviewStateNo":601,"reviewType":"申领","reviewTypeNo":300,"reviewUserName":"","startDate":1547024355000,"userNo":5}]
     * count : 6
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
         * fromUserName : 虞勇-仓管
         * goodsCount : 2
         * id : 18
         * objNo : 5
         * reviewDate : 1547026075000
         * reviewIndex : 0
         * reviewReason :
         * reviewState : 通过
         * reviewStateNo : 601
         * reviewType : 采购
         * reviewTypeNo : 310
         * reviewUserName :
         * startDate : 1547026062000
         * userNo : 5
         */

        private String fromUserName;
        private int goodsCount;
        private int id;
        private int objNo;
        private long reviewDate;
        private int reviewIndex;
        private String reviewReason;
        private String reviewState;
        private int reviewStateNo;
        private String reviewType;
        private int reviewTypeNo;
        private String reviewUserName;
        private long startDate;
        private int userNo;

        @Override
        public String toString() {
            return "DataBean{" +
                    "fromUserName='" + fromUserName + '\'' +
                    ", goodsCount=" + goodsCount +
                    ", id=" + id +
                    ", objNo=" + objNo +
                    ", reviewDate=" + reviewDate +
                    ", reviewIndex=" + reviewIndex +
                    ", reviewReason='" + reviewReason + '\'' +
                    ", reviewState='" + reviewState + '\'' +
                    ", reviewStateNo=" + reviewStateNo +
                    ", reviewType='" + reviewType + '\'' +
                    ", reviewTypeNo=" + reviewTypeNo +
                    ", reviewUserName='" + reviewUserName + '\'' +
                    ", startDate=" + startDate +
                    ", userNo=" + userNo +
                    '}';
        }

        public String getFromUserName() {
            return fromUserName;
        }

        public void setFromUserName(String fromUserName) {
            this.fromUserName = fromUserName;
        }

        public int getGoodsCount() {
            return goodsCount;
        }

        public void setGoodsCount(int goodsCount) {
            this.goodsCount = goodsCount;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getObjNo() {
            return objNo;
        }

        public void setObjNo(int objNo) {
            this.objNo = objNo;
        }

        public long getReviewDate() {
            return reviewDate;
        }

        public void setReviewDate(long reviewDate) {
            this.reviewDate = reviewDate;
        }

        public int getReviewIndex() {
            return reviewIndex;
        }

        public void setReviewIndex(int reviewIndex) {
            this.reviewIndex = reviewIndex;
        }

        public String getReviewReason() {
            return reviewReason;
        }

        public void setReviewReason(String reviewReason) {
            this.reviewReason = reviewReason;
        }

        public String getReviewState() {
            return reviewState;
        }

        public void setReviewState(String reviewState) {
            this.reviewState = reviewState;
        }

        public int getReviewStateNo() {
            return reviewStateNo;
        }

        public void setReviewStateNo(int reviewStateNo) {
            this.reviewStateNo = reviewStateNo;
        }

        public String getReviewType() {
            return reviewType;
        }

        public void setReviewType(String reviewType) {
            this.reviewType = reviewType;
        }

        public int getReviewTypeNo() {
            return reviewTypeNo;
        }

        public void setReviewTypeNo(int reviewTypeNo) {
            this.reviewTypeNo = reviewTypeNo;
        }

        public String getReviewUserName() {
            return reviewUserName;
        }

        public void setReviewUserName(String reviewUserName) {
            this.reviewUserName = reviewUserName;
        }

        public long getStartDate() {
            return startDate;
        }

        public void setStartDate(long startDate) {
            this.startDate = startDate;
        }

        public int getUserNo() {
            return userNo;
        }

        public void setUserNo(int userNo) {
            this.userNo = userNo;
        }
    }
}
