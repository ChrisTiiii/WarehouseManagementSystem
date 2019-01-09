package com.example.administrator.warehousemanagementsystem.bean;

import java.util.List;

/**
 * author: ZhongMing
 * DATE: 2018/12/24 0024
 * Description:
 **/
public class BudgetList {

    /**
     * result : ok
     * data : [{"budgDate":1545629051000,"budgId":"215456290509974","budgState":"进行中","budgUsage":"就这样","budgetContentList":null,"deptName":"","deptNo":2,"fromUserName":"","id":6,"reviewList":null,"userNo":4}]
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
         * budgDate : 1545629051000
         * budgId : 215456290509974
         * budgState : 进行中
         * budgUsage : 就这样
         * budgetContentList : null
         * deptName :
         * deptNo : 2
         * fromUserName :
         * id : 6
         * reviewList : null
         * userNo : 4
         */

        private long budgDate;
        private String budgId;
        private String budgState;
        private String budgUsage;
        private Object budgetContentList;
        private String deptName;
        private int deptNo;
        private String fromUserName;
        private int id;
        private Object reviewList;
        private int userNo;

        @Override
        public String toString() {
            return "DataBean{" +
                    "budgDate=" + budgDate +
                    ", budgId='" + budgId + '\'' +
                    ", budgState='" + budgState + '\'' +
                    ", budgUsage='" + budgUsage + '\'' +
                    ", budgetContentList=" + budgetContentList +
                    ", deptName='" + deptName + '\'' +
                    ", deptNo=" + deptNo +
                    ", fromUserName='" + fromUserName + '\'' +
                    ", id=" + id +
                    ", reviewList=" + reviewList +
                    ", userNo=" + userNo +
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

        public Object getBudgetContentList() {
            return budgetContentList;
        }

        public void setBudgetContentList(Object budgetContentList) {
            this.budgetContentList = budgetContentList;
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

        public Object getReviewList() {
            return reviewList;
        }

        public void setReviewList(Object reviewList) {
            this.reviewList = reviewList;
        }

        public int getUserNo() {
            return userNo;
        }

        public void setUserNo(int userNo) {
            this.userNo = userNo;
        }
    }
}
