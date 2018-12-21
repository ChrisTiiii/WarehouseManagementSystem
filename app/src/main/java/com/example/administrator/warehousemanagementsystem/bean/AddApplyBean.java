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
     * data : {"applyContentList":[{"annotation":"暂无","applyNo":45,"goodsIdNo":"6901668005731","goodsName":"奥利奥夹心饼干","goodsNo":1,"goodsNum":20,"id":73},{"annotation":"暂无","applyNo":45,"goodsIdNo":"6906791521028","goodsName":"阿华田","goodsNo":2,"goodsNum":5,"id":74},{"annotation":"暂无","applyNo":45,"goodsIdNo":"6920459902387","goodsName":"康师傅冰红茶","goodsNo":3,"goodsNum":8,"id":75}],"applyFinishdate":null,"applyId":"015453711055705","applyStartdate":1545371105570,"applyState":"进行中","applyUsage":"测试csasas撒大大用途","applyWarning":"本单位暂不存在领用超限情况","deptName":"","deptNo":1,"fromUserName":"","id":45,"reviewList":[{"fromUserName":"","goodsCount":0,"id":97,"objNo":45,"reviewDate":null,"reviewIndex":0,"reviewState":"等待审批","reviewType":"","reviewTypeNo":300,"reviewUserName":"收费站站长1","startDate":null,"userNo":3},{"fromUserName":"","goodsCount":0,"id":98,"objNo":45,"reviewDate":null,"reviewIndex":1,"reviewState":"等待审批","reviewType":"","reviewTypeNo":300,"reviewUserName":"仓库管理员1","startDate":null,"userNo":4},{"fromUserName":"","goodsCount":0,"id":99,"objNo":45,"reviewDate":null,"reviewIndex":2,"reviewState":"等待审批","reviewType":"","reviewTypeNo":300,"reviewUserName":"主任1","startDate":null,"userNo":5}],"userNo":2}
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
         * applyContentList : [{"annotation":"暂无","applyNo":45,"goodsIdNo":"6901668005731","goodsName":"奥利奥夹心饼干","goodsNo":1,"goodsNum":20,"id":73},{"annotation":"暂无","applyNo":45,"goodsIdNo":"6906791521028","goodsName":"阿华田","goodsNo":2,"goodsNum":5,"id":74},{"annotation":"暂无","applyNo":45,"goodsIdNo":"6920459902387","goodsName":"康师傅冰红茶","goodsNo":3,"goodsNum":8,"id":75}]
         * applyFinishdate : null
         * applyId : 015453711055705
         * applyStartdate : 1545371105570
         * applyState : 进行中
         * applyUsage : 测试csasas撒大大用途
         * applyWarning : 本单位暂不存在领用超限情况
         * deptName :
         * deptNo : 1
         * fromUserName :
         * id : 45
         * reviewList : [{"fromUserName":"","goodsCount":0,"id":97,"objNo":45,"reviewDate":null,"reviewIndex":0,"reviewState":"等待审批","reviewType":"","reviewTypeNo":300,"reviewUserName":"收费站站长1","startDate":null,"userNo":3},{"fromUserName":"","goodsCount":0,"id":98,"objNo":45,"reviewDate":null,"reviewIndex":1,"reviewState":"等待审批","reviewType":"","reviewTypeNo":300,"reviewUserName":"仓库管理员1","startDate":null,"userNo":4},{"fromUserName":"","goodsCount":0,"id":99,"objNo":45,"reviewDate":null,"reviewIndex":2,"reviewState":"等待审批","reviewType":"","reviewTypeNo":300,"reviewUserName":"主任1","startDate":null,"userNo":5}]
         * userNo : 2
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
             * applyNo : 45
             * goodsIdNo : 6901668005731
             * goodsName : 奥利奥夹心饼干
             * goodsNo : 1
             * goodsNum : 20
             * id : 73
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
             * fromUserName :
             * goodsCount : 0
             * id : 97
             * objNo : 45
             * reviewDate : null
             * reviewIndex : 0
             * reviewState : 等待审批
             * reviewType :
             * reviewTypeNo : 300
             * reviewUserName : 收费站站长1
             * startDate : null
             * userNo : 3
             */

            private String fromUserName;
            private int goodsCount;
            private int id;
            private int objNo;
            private Object reviewDate;
            private int reviewIndex;
            private String reviewState;
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
