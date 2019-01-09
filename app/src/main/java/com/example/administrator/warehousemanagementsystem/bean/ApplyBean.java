package com.example.administrator.warehousemanagementsystem.bean;

import java.util.List;

/**
 * author: ZhongMing
 * DATE: 2018/12/17 0017
 * Description:
 **/
public class ApplyBean {

    /**
     * result : ok
     * data : {"applyContentList":[{"annotation":"暂无","applyNo":5,"goodsIdNo":"6906791521028","goodsName":"阿华田","goodsNo":13,"goodsNum":35,"goodsType":"调度中心","goodsTypeNo":131,"id":7}],"applyFinishdate":null,"applyId":"015470236164038","applyStartdate":1547023616000,"applyState":"进行中","applyUsage":"yhhb","applyWarning":"本单位暂不存在领用超限情况","deptName":"浦头收费站","deptNo":12,"fromUserName":"浦头收费站仓库","id":5,"reviewList":[{"fromUserName":"","goodsCount":0,"id":7,"objNo":5,"reviewDate":null,"reviewIndex":0,"reviewReason":"","reviewState":"等待审批","reviewStateNo":600,"reviewType":"","reviewTypeNo":300,"reviewUserName":"浦头收费站","startDate":null,"userNo":19},{"fromUserName":"","goodsCount":0,"id":8,"objNo":5,"reviewDate":null,"reviewIndex":1,"reviewReason":"","reviewState":"等待审批","reviewStateNo":600,"reviewType":"","reviewTypeNo":300,"reviewUserName":"杨军-部门负责人","startDate":null,"userNo":5}],"userNo":31}
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
         * applyContentList : [{"annotation":"暂无","applyNo":5,"goodsIdNo":"6906791521028","goodsName":"阿华田","goodsNo":13,"goodsNum":35,"goodsType":"调度中心","goodsTypeNo":131,"id":7}]
         * applyFinishdate : null
         * applyId : 015470236164038
         * applyStartdate : 1547023616000
         * applyState : 进行中
         * applyUsage : yhhb
         * applyWarning : 本单位暂不存在领用超限情况
         * deptName : 浦头收费站
         * deptNo : 12
         * fromUserName : 浦头收费站仓库
         * id : 5
         * reviewList : [{"fromUserName":"","goodsCount":0,"id":7,"objNo":5,"reviewDate":null,"reviewIndex":0,"reviewReason":"","reviewState":"等待审批","reviewStateNo":600,"reviewType":"","reviewTypeNo":300,"reviewUserName":"浦头收费站","startDate":null,"userNo":19},{"fromUserName":"","goodsCount":0,"id":8,"objNo":5,"reviewDate":null,"reviewIndex":1,"reviewReason":"","reviewState":"等待审批","reviewStateNo":600,"reviewType":"","reviewTypeNo":300,"reviewUserName":"杨军-部门负责人","startDate":null,"userNo":5}]
         * userNo : 31
         */

        private Object applyFinishdate;
        private String applyId;
        private long applyStartdate;
        private String applyState;
        private String applyUsage;
        private String applyWarning;
        private String deptName;
        private int deptNo;
        private String fromUserName;
        private int id;
        private int userNo;
        private List<ApplyContentListBean> applyContentList;
        private List<ReviewListBean> reviewList;

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
             * annotation : 暂无
             * applyNo : 5
             * goodsIdNo : 6906791521028
             * goodsName : 阿华田
             * goodsNo : 13
             * goodsNum : 35
             * goodsType : 调度中心
             * goodsTypeNo : 131
             * id : 7
             */

            private String annotation;
            private int applyNo;
            private String goodsIdNo;
            private String goodsName;
            private int goodsNo;
            private int goodsNum;
            private String goodsType;
            private int goodsTypeNo;
            private int id;

            @Override
            public String toString() {
                return "ApplyContentListBean{" +
                        "annotation='" + annotation + '\'' +
                        ", applyNo=" + applyNo +
                        ", goodsIdNo='" + goodsIdNo + '\'' +
                        ", goodsName='" + goodsName + '\'' +
                        ", goodsNo=" + goodsNo +
                        ", goodsNum=" + goodsNum +
                        ", goodsType='" + goodsType + '\'' +
                        ", goodsTypeNo=" + goodsTypeNo +
                        ", id=" + id +
                        '}';
            }

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
             * id : 7
             * objNo : 5
             * reviewDate : null
             * reviewIndex : 0
             * reviewReason :
             * reviewState : 等待审批
             * reviewStateNo : 600
             * reviewType :
             * reviewTypeNo : 300
             * reviewUserName : 浦头收费站
             * startDate : null
             * userNo : 19
             */

            private String fromUserName;
            private int goodsCount;
            private int id;
            private int objNo;
            private Object reviewDate;
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
