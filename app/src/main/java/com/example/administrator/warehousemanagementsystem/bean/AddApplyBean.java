package com.example.administrator.warehousemanagementsystem.bean;

import java.util.List;

/**
 * author: ZhongMing
 * DATE: 2018/12/14 0014
 * Description:
 **/
public class AddApplyBean {

    /**
     * result : ok
     * data : {"applyContentList":[{"annotation":"","applyNo":3,"goodsIdNo":"6901668005731","goodsName":"奥利奥夹心饼干","goodsNo":1,"goodsNum":12,"id":3},{"annotation":"","applyNo":3,"goodsIdNo":"6906791521028","goodsName":"阿华田","goodsNo":2,"goodsNum":5,"id":4}],"applyFinishdate":null,"applyId":"015446824190142","applyStartdate":1544682419013,"applyState":"进行中","applyUsage":"测试用途","applyWarning":"","deptNo":1,"id":3,"reviewList":[{"id":3,"objNo":3,"reviewDate":null,"reviewState":"等待审批","reviewType":300,"userNo":3},{"id":4,"objNo":3,"reviewDate":null,"reviewState":"等待审批","reviewType":300,"userNo":4}],"userNo":2}
     */

    private String result;
    private DataBean data;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * applyContentList : [{"annotation":"","applyNo":3,"goodsIdNo":"6901668005731","goodsName":"奥利奥夹心饼干","goodsNo":1,"goodsNum":12,"id":3},{"annotation":"","applyNo":3,"goodsIdNo":"6906791521028","goodsName":"阿华田","goodsNo":2,"goodsNum":5,"id":4}]
         * applyFinishdate : null
         * applyId : 015446824190142
         * applyStartdate : 1544682419013
         * applyState : 进行中
         * applyUsage : 测试用途
         * applyWarning :
         * deptNo : 1
         * id : 3
         * reviewList : [{"id":3,"objNo":3,"reviewDate":null,"reviewState":"等待审批","reviewType":300,"userNo":3},{"id":4,"objNo":3,"reviewDate":null,"reviewState":"等待审批","reviewType":300,"userNo":4}]
         * userNo : 2
         */

        private Object applyFinishdate;
        private String applyId;
        private long applyStartdate;
        private String applyState;
        private String applyUsage;
        private String applyWarning;
        private int deptNo;
        private int id;
        private int userNo;
        private List<ApplyContentListBean> applyContentList;
        private List<ReviewListBean> reviewList;

        @Override
        public String toString() {
            return "DataBean{" +
                    "applyFinishdate=" + applyFinishdate +
                    ", applyId='" + applyId + '\'' +
                    ", applyStartdate=" + applyStartdate +
                    ", applyState='" + applyState + '\'' +
                    ", applyUsage='" + applyUsage + '\'' +
                    ", applyWarning='" + applyWarning + '\'' +
                    ", deptNo=" + deptNo +
                    ", id=" + id +
                    ", userNo=" + userNo +
                    ", applyContentList=" + applyContentList +
                    ", reviewList=" + reviewList +
                    '}';
        }

        public Object getApplyFinishdate() {
            return applyFinishdate;
        }

        public void setApplyFinishdate(Object applyFinishdate) {
            this.applyFinishdate = applyFinishdate;
        }

        public String getApplyId() {
            return applyId;
        }

        public void setApplyId(String applyId) {
            this.applyId = applyId;
        }

        public long getApplyStartdate() {
            return applyStartdate;
        }

        public void setApplyStartdate(long applyStartdate) {
            this.applyStartdate = applyStartdate;
        }

        public String getApplyState() {
            return applyState;
        }

        public void setApplyState(String applyState) {
            this.applyState = applyState;
        }

        public String getApplyUsage() {
            return applyUsage;
        }

        public void setApplyUsage(String applyUsage) {
            this.applyUsage = applyUsage;
        }

        public String getApplyWarning() {
            return applyWarning;
        }

        public void setApplyWarning(String applyWarning) {
            this.applyWarning = applyWarning;
        }

        public int getDeptNo() {
            return deptNo;
        }

        public void setDeptNo(int deptNo) {
            this.deptNo = deptNo;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUserNo() {
            return userNo;
        }

        public void setUserNo(int userNo) {
            this.userNo = userNo;
        }

        public List<ApplyContentListBean> getApplyContentList() {
            return applyContentList;
        }

        public void setApplyContentList(List<ApplyContentListBean> applyContentList) {
            this.applyContentList = applyContentList;
        }

        public List<ReviewListBean> getReviewList() {
            return reviewList;
        }

        public void setReviewList(List<ReviewListBean> reviewList) {
            this.reviewList = reviewList;
        }

        public static class ApplyContentListBean {
            /**
             * annotation :
             * applyNo : 3
             * goodsIdNo : 6901668005731
             * goodsName : 奥利奥夹心饼干
             * goodsNo : 1
             * goodsNum : 12
             * id : 3
             */

            private String annotation;
            private int applyNo;
            private String goodsIdNo;
            private String goodsName;
            private int goodsNo;
            private int goodsNum;
            private int id;

            public String getAnnotation() {
                return annotation;
            }

            public void setAnnotation(String annotation) {
                this.annotation = annotation;
            }

            public int getApplyNo() {
                return applyNo;
            }

            public void setApplyNo(int applyNo) {
                this.applyNo = applyNo;
            }

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

            public int getGoodsNum() {
                return goodsNum;
            }

            public void setGoodsNum(int goodsNum) {
                this.goodsNum = goodsNum;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }
        }

        public static class ReviewListBean {
            /**
             * id : 3
             * objNo : 3
             * reviewDate : null
             * reviewState : 等待审批
             * reviewType : 300
             * userNo : 3
             */

            private int id;
            private int objNo;
            private Object reviewDate;
            private String reviewState;
            private int reviewType;
            private int userNo;

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

            public Object getReviewDate() {
                return reviewDate;
            }

            public void setReviewDate(Object reviewDate) {
                this.reviewDate = reviewDate;
            }

            public String getReviewState() {
                return reviewState;
            }

            public void setReviewState(String reviewState) {
                this.reviewState = reviewState;
            }

            public int getReviewType() {
                return reviewType;
            }

            public void setReviewType(int reviewType) {
                this.reviewType = reviewType;
            }

            public int getUserNo() {
                return userNo;
            }

            public void setUserNo(int userNo) {
                this.userNo = userNo;
            }
        }
    }
}
