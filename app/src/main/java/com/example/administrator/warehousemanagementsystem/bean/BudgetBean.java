package com.example.administrator.warehousemanagementsystem.bean;

import java.util.List;

/**
 * author: ZhongMing
 * DATE: 2018/12/24 0024
 * Description:
 **/
public class BudgetBean {

    /**
     * result : ok
     * data : {"budgDate":1545029544129,"budgId":"215450295441293","budgState":"进行中","budgUsage":"预算单！！！","budgetContentList":[{"annotation":"","budgNo":2,"goodsIdNo":"6901668005731","goodsName":"奥利奥夹心饼干","goodsNo":1,"goodsNum":300,"id":3},{"annotation":"","budgNo":2,"goodsIdNo":"6906791521028","goodsName":"阿华田","goodsNo":2,"goodsNum":500,"id":4}],"deptNo":1,"id":2,"reviewList":[{"id":36,"objNo":2,"reviewDate":null,"reviewIndex":0,"reviewState":"等待审批","reviewTypeNo":320,"userNo":3},{"id":37,"objNo":2,"reviewDate":null,"reviewIndex":1,"reviewState":"等待审批","reviewTypeNo":320,"userNo":5}],"userNo":2}
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
         * budgDate : 1545029544129
         * budgId : 215450295441293
         * budgState : 进行中
         * budgUsage : 预算单！！！
         * budgetContentList : [{"annotation":"","budgNo":2,"goodsIdNo":"6901668005731","goodsName":"奥利奥夹心饼干","goodsNo":1,"goodsNum":300,"id":3},{"annotation":"","budgNo":2,"goodsIdNo":"6906791521028","goodsName":"阿华田","goodsNo":2,"goodsNum":500,"id":4}]
         * deptNo : 1
         * id : 2
         * reviewList : [{"id":36,"objNo":2,"reviewDate":null,"reviewIndex":0,"reviewState":"等待审批","reviewTypeNo":320,"userNo":3},{"id":37,"objNo":2,"reviewDate":null,"reviewIndex":1,"reviewState":"等待审批","reviewTypeNo":320,"userNo":5}]
         * userNo : 2
         */

        private long budgDate;
        private String budgId;
        private String budgState;
        private String budgUsage;
        private int deptNo;
        private int id;
        private String fromUserName;
        private String deptName;
        private int userNo;

        private List<BudgetContentListBean> budgetContentList;
        private List<ReviewListBean> reviewList;


        @Override
        public String toString() {
            return "DataBean{" +
                    "budgDate=" + budgDate +
                    ", budgId='" + budgId + '\'' +
                    ", budgState='" + budgState + '\'' +
                    ", budgUsage='" + budgUsage + '\'' +
                    ", deptNo=" + deptNo +
                    ", id=" + id +
                    ", fromUserName='" + fromUserName + '\'' +
                    ", deptName='" + deptName + '\'' +
                    ", userNo=" + userNo +
                    ", budgetContentList=" + budgetContentList +
                    ", reviewList=" + reviewList +
                    '}';
        }

        public long getBudgDate() {
            return budgDate;
        }

        public void setBudgDate(long budgDate) {
            this.budgDate = budgDate;
        }

        public String getBudgId() {
            return budgId;
        }

        public void setBudgId(String budgId) {
            this.budgId = budgId;
        }

        public String getBudgState() {
            return budgState;
        }

        public void setBudgState(String budgState) {
            this.budgState = budgState;
        }

        public String getBudgUsage() {
            return budgUsage;
        }

        public void setBudgUsage(String budgUsage) {
            this.budgUsage = budgUsage;
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

        public String getDeptName() {
            return deptName;
        }

        public void setDeptName(String deptName) {
            this.deptName = deptName;
        }

        public String getFromUserName() {
            return fromUserName;
        }

        public void setFromUserName(String fromUserName) {
            this.fromUserName = fromUserName;
        }

        public int getUserNo() {
            return userNo;
        }

        public void setUserNo(int userNo) {
            this.userNo = userNo;
        }

        public List<BudgetContentListBean> getBudgetContentList() {
            return budgetContentList;
        }

        public void setBudgetContentList(List<BudgetContentListBean> budgetContentList) {
            this.budgetContentList = budgetContentList;
        }

        public List<ReviewListBean> getReviewList() {
            return reviewList;
        }

        public void setReviewList(List<ReviewListBean> reviewList) {
            this.reviewList = reviewList;
        }

        public static class BudgetContentListBean {
            /**
             * annotation :
             * budgNo : 2
             * goodsIdNo : 6901668005731
             * goodsName : 奥利奥夹心饼干
             * goodsNo : 1
             * goodsNum : 300
             * id : 3
             */

            private String annotation;
            private int budgNo;
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

            public int getBudgNo() {
                return budgNo;
            }

            public void setBudgNo(int budgNo) {
                this.budgNo = budgNo;
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
             * id : 36
             * objNo : 2
             * reviewDate : null
             * reviewIndex : 0
             * reviewState : 等待审批
             * reviewTypeNo : 320
             * userNo : 3
             */

            private int id;
            private int objNo;
            private Object reviewDate;
            private int reviewIndex;
            private String reviewState;
            private int reviewTypeNo;
            private String reviewUserName;
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

            public int getUserNo() {
                return userNo;
            }

            public void setUserNo(int userNo) {
                this.userNo = userNo;
            }
        }
    }
}
