package com.example.administrator.warehousemanagementsystem.bean;

import java.util.List;

/**
 * author: ZhongMing
 * DATE: 2018/12/19 0019
 * Description:获取我的申请列表
 **/
public class ApplyList {

    /**
     * result : ok
     * data : [{"applyContentList":null,"applyFinishdate":null,"applyId":"015451096553985","applyStartdate":1545109655000,"applyState":"进行中","applyUsage":"测试测试吃饭","applyWarning":"","deptNo":1,"id":23,"reviewList":null,"userNo":2},{"applyContentList":null,"applyFinishdate":null,"applyId":"015451127702200","applyStartdate":1545112770000,"applyState":"进行中","applyUsage":"吃吃","applyWarning":"","deptNo":1,"id":24,"reviewList":null,"userNo":2},{"applyContentList":null,"applyFinishdate":null,"applyId":"015451142808520","applyStartdate":1545114281000,"applyState":"进行中","applyUsage":"罢了看看","applyWarning":"","deptNo":1,"id":25,"reviewList":null,"userNo":2}]
     * count : 3
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
         * applyContentList : null
         * applyFinishdate : null
         * applyId : 015451096553985
         * applyStartdate : 1545109655000
         * applyState : 进行中
         * applyUsage : 测试测试吃饭
         * applyWarning :
         * deptNo : 1
         * id : 23
         * reviewList : null
         * userNo : 2
         */

        private Object applyContentList;
        private Object applyFinishdate;
        private String applyId;
        private long applyStartdate;
        private String applyState;
        private String applyUsage;
        private String applyWarning;
        private int deptNo;
        private int id;
        private Object reviewList;
        private int userNo;
        private String reviewType;
        private Integer reviewTypeNo;

        @Override
        public String toString() {
            return "DataBean{" +
                    "applyContentList=" + applyContentList +
                    ", applyFinishdate=" + applyFinishdate +
                    ", applyId='" + applyId + '\'' +
                    ", applyStartdate=" + applyStartdate +
                    ", applyState='" + applyState + '\'' +
                    ", applyUsage='" + applyUsage + '\'' +
                    ", applyWarning='" + applyWarning + '\'' +
                    ", deptNo=" + deptNo +
                    ", id=" + id +
                    ", reviewList=" + reviewList +
                    ", userNo=" + userNo +
                    '}';
        }

        public Object getApplyContentList() {
            return applyContentList;
        }

        public void setApplyContentList(Object applyContentList) {
            this.applyContentList = applyContentList;
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

        public Object getReviewList() {
            return reviewList;
        }

        public void setReviewList(Object reviewList) {
            this.reviewList = reviewList;
        }

        public int getUserNo() {
            return userNo;
        }

        public String getReviewType() {
            return reviewType;
        }

        public void setReviewType(String reviewType) {
            this.reviewType = reviewType;
        }

        public Integer getReviewTypeNo() {
            return reviewTypeNo;
        }

        public void setReviewTypeNo(Integer reviewTypeNo) {
            this.reviewTypeNo = reviewTypeNo;
        }

        public void setUserNo(int userNo) {
            this.userNo = userNo;
        }
    }
}
