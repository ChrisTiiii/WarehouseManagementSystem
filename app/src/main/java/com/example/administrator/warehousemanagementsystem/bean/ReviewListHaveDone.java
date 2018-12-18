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
     * data : [{"fromUserName":"收费站管理员1","goodsCount":2,"id":2,"objNo":2,"reviewDate":1545038861000,"reviewIndex":1,"reviewState":"未通过","reviewType":"申领","reviewTypeNo":300,"startDate":1544681698000,"userNo":5}]
     * count : 17
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
         * fromUserName : 收费站管理员1
         * goodsCount : 2
         * id : 2
         * objNo : 2
         * reviewDate : 1545038861000
         * reviewIndex : 1
         * reviewState : 未通过
         * reviewType : 申领
         * reviewTypeNo : 300
         * startDate : 1544681698000
         * userNo : 5
         */

        private String fromUserName;
        private int goodsCount;
        private int id;
        private int objNo;
        private long reviewDate;
        private int reviewIndex;
        private String reviewState;
        private String reviewType;
        private int reviewTypeNo;
        private long startDate;
        private int userNo;

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

        public String getReviewState() {
            return reviewState;
        }

        public void setReviewState(String reviewState) {
            this.reviewState = reviewState;
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
