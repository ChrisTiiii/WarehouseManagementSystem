package com.example.administrator.warehousemanagementsystem.bean;

import java.util.List;

/**
 * author: ZhongMing
 * DATE: 2018/12/24 0024
 * Description:预算单
 **/
public class BudgetBean {


    /**
     * result : ok
     * data : {"budgDate":1547024380000,"budgId":"215470243803239","budgState":"进行中","budgUsage":"暂无备注","budgetContentList":[{"annotation":"","budgNo":1,"goodsIdNo":"6906791521028","goodsName":"阿华田","goodsNo":13,"goodsNum":36,"goodsType":"调度中心","goodsTypeNo":131,"id":1}],"deptName":"浦头收费站","deptNo":12,"fromUserName":"浦头收费站仓库","id":1,"reviewList":[{"fromUserName":"","goodsCount":0,"id":11,"objNo":1,"reviewDate":1547024689000,"reviewIndex":0,"reviewReason":"","reviewState":"通过","reviewStateNo":601,"reviewType":"","reviewTypeNo":320,"reviewUserName":"杨军-部门负责人","startDate":null,"userNo":5},{"fromUserName":"","goodsCount":0,"id":12,"objNo":1,"reviewDate":null,"reviewIndex":1,"reviewReason":"","reviewState":"等待审批","reviewStateNo":600,"reviewType":"","reviewTypeNo":320,"reviewUserName":"王友中-分管领导","startDate":null,"userNo":32}],"userNo":31}
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
         * budgDate : 1547024380000
         * budgId : 215470243803239
         * budgState : 进行中
         * budgUsage : 暂无备注
         * budgetContentList : [{"annotation":"","budgNo":1,"goodsIdNo":"6906791521028","goodsName":"阿华田","goodsNo":13,"goodsNum":36,"goodsType":"调度中心","goodsTypeNo":131,"id":1}]
         * deptName : 浦头收费站
         * deptNo : 12
         * fromUserName : 浦头收费站仓库
         * id : 1
         * reviewList : [{"fromUserName":"","goodsCount":0,"id":11,"objNo":1,"reviewDate":1547024689000,"reviewIndex":0,"reviewReason":"","reviewState":"通过","reviewStateNo":601,"reviewType":"","reviewTypeNo":320,"reviewUserName":"杨军-部门负责人","startDate":null,"userNo":5},{"fromUserName":"","goodsCount":0,"id":12,"objNo":1,"reviewDate":null,"reviewIndex":1,"reviewReason":"","reviewState":"等待审批","reviewStateNo":600,"reviewType":"","reviewTypeNo":320,"reviewUserName":"王友中-分管领导","startDate":null,"userNo":32}]
         * userNo : 31
         */

        private long budgDate;
        private String budgId;
        private String budgState;
        private String budgUsage;
        private String deptName;
        private int deptNo;
        private String fromUserName;
        private int id;
        private int userNo;
        private List<BudgetContentListBean> budgetContentList;
        private List<ReviewListBean> reviewList;

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

        public String getFromUserName() {
            return fromUserName;
        }

        public void setFromUserName(String fromUserName) {
            this.fromUserName = fromUserName;
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
             * budgNo : 1
             * goodsIdNo : 6906791521028
             * goodsName : 阿华田
             * goodsNo : 13
             * goodsNum : 36
             * goodsType : 调度中心
             * goodsTypeNo : 131
             * id : 1
             */

            private String annotation;
            private int budgNo;
            private String goodsIdNo;
            private String goodsName;
            private int goodsNo;
            private int goodsNum;
            private String goodsType;
            private int goodsTypeNo;
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

            public String getGoodsType() {
                return goodsType;
            }

            public void setGoodsType(String goodsType) {
                this.goodsType = goodsType;
            }

            public int getGoodsTypeNo() {
                return goodsTypeNo;
            }

            public void setGoodsTypeNo(int goodsTypeNo) {
                this.goodsTypeNo = goodsTypeNo;
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
             * fromUserName :
             * goodsCount : 0
             * id : 11
             * objNo : 1
             * reviewDate : 1547024689000
             * reviewIndex : 0
             * reviewReason :
             * reviewState : 通过
             * reviewStateNo : 601
             * reviewType :
             * reviewTypeNo : 320
             * reviewUserName : 杨军-部门负责人
             * startDate : null
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
            private Object startDate;
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

            public Object getStartDate() {
                return startDate;
            }

            public void setStartDate(Object startDate) {
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
}
