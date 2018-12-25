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
     * data : {"applyContentList":[{"annotation":"","applyNo":2,"goodsIdNo":"6901668005731","goodsName":"奥利奥夹心饼干","goodsNo":1,"goodsNum":12,"id":1},{"annotation":"","applyNo":2,"goodsIdNo":"6906791521028","goodsName":"阿华田","goodsNo":2,"goodsNum":5,"id":2}],"applyFinishdate":null,"applyId":"015446816982659","applyStartdate":1544681698000,"applyState":"进行中","applyUsage":"测试用途","applyWarning":"","deptNo":1,"id":2,"reviewList":[{"id":1,"objNo":2,"reviewDate":null,"reviewIndex":0,"reviewState":"等待审批","reviewTypeNo":300,"userNo":3},{"id":2,"objNo":2,"reviewDate":null,"reviewIndex":1,"reviewState":"等待审批","reviewTypeNo":300,"userNo":4},{"id":13,"objNo":2,"reviewDate":null,"reviewIndex":0,"reviewState":"等待审批","reviewTypeNo":300,"userNo":5},{"id":14,"objNo":2,"reviewDate":null,"reviewIndex":1,"reviewState":"等待审批","reviewTypeNo":300,"userNo":6}],"userNo":2}
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

        /**
         * applyContentList : [{"annotation":"","applyNo":2,"goodsIdNo":"6901668005731","goodsName":"奥利奥夹心饼干","goodsNo":1,"goodsNum":12,"id":1},{"annotation":"","applyNo":2,"goodsIdNo":"6906791521028","goodsName":"阿华田","goodsNo":2,"goodsNum":5,"id":2}]
         * applyFinishdate : null
         * applyId : 015446816982659
         * applyStartdate : 1544681698000
         * applyState : 进行中
         * applyUsage : 测试用途
         * applyWarning :
         * deptNo : 1
         * id : 2
         * reviewList : [{"id":1,"objNo":2,"reviewDate":null,"reviewIndex":0,"reviewState":"等待审批","reviewTypeNo":300,"userNo":3},{"id":2,"objNo":2,"reviewDate":null,"reviewIndex":1,"reviewState":"等待审批","reviewTypeNo":300,"userNo":4},{"id":13,"objNo":2,"reviewDate":null,"reviewIndex":0,"reviewState":"等待审批","reviewTypeNo":300,"userNo":5},{"id":14,"objNo":2,"reviewDate":null,"reviewIndex":1,"reviewState":"等待审批","reviewTypeNo":300,"userNo":6}]
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
        private String fromUserName;
        private String deptName;
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

        public String getFromUserName() {
            return fromUserName;
        }

        public void setFromUserName(String fromUserName) {
            this.fromUserName = fromUserName;
        }

        public String getDeptName() {
            return deptName;
        }

        public void setDeptName(String deptName) {
            this.deptName = deptName;
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
             * applyNo : 2
             * goodsIdNo : 6901668005731
             * goodsName : 奥利奥夹心饼干
             * goodsNo : 1
             * goodsNum : 12
             * id : 1
             */

            private String annotation;
            private int applyNo;
            private String goodsIdNo;
            private String goodsName;
            private int goodsNo;
            private int goodsNum;
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

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }
        }

        public static class ReviewListBean {
            /**
             * id : 1
             * objNo : 2
             * reviewDate : null
             * reviewIndex : 0
             * reviewState : 等待审批
             * reviewTypeNo : 300
             * userNo : 3
             */

            private int id;
            private int objNo;
            private Object reviewDate;
            private int reviewIndex;
            private String reviewState;
            private int reviewTypeNo;
            private int userNo;
            private String reviewUserName;

            @Override
            public String toString() {
                return "ReviewListBean{" +
                        "id=" + id +
                        ", objNo=" + objNo +
                        ", reviewDate=" + reviewDate +
                        ", reviewIndex=" + reviewIndex +
                        ", reviewState='" + reviewState + '\'' +
                        ", reviewTypeNo=" + reviewTypeNo +
                        ", userNo=" + userNo +
                        ", reviewUserName='" + reviewUserName + '\'' +
                        '}';
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

            public int getUserNo() {
                return userNo;
            }

            public String getReviewUserName() {
                return reviewUserName;
            }

            public void setReviewUserName(String reviewUserName) {
                this.reviewUserName = reviewUserName;
            }

            public void setUserNo(int userNo) {
                this.userNo = userNo;
            }
        }
    }
}
