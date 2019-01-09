package com.example.administrator.warehousemanagementsystem.bean;

import java.util.List;

/**
 * author: ZhongMing
 * DATE: 2018/12/18 0018
 * Description:采购单详情
 **/
public class PurchaseBean {

    /**
     * result : ok
     * data : {"fromUserName":"虞勇-仓管","id":3,"purcFinishdate":null,"purcId":"115470248704599","purcStartdate":1547024870000,"purcState":"进行中","purcSupplier":"南京","purcUsage":"测试","purchaseContentList":[{"annotation":"","goodsIdNo":"6923644282462","goodsName":"蒙牛纯牛奶","goodsNo":12,"goodsNum":36,"goodsType":"综合科","goodsTypeNo":132,"id":2,"purcNo":3},{"annotation":"","goodsIdNo":"6906791521028","goodsName":"阿华田","goodsNo":13,"goodsNum":35,"goodsType":"调度中心","goodsTypeNo":131,"id":3,"purcNo":3}],"reviewList":[{"fromUserName":"","goodsCount":0,"id":13,"objNo":3,"reviewDate":null,"reviewIndex":0,"reviewReason":"","reviewState":"等待审批","reviewStateNo":600,"reviewType":"","reviewTypeNo":310,"reviewUserName":"杨军-部门负责人","startDate":null,"userNo":5},{"fromUserName":"","goodsCount":0,"id":14,"objNo":3,"reviewDate":null,"reviewIndex":1,"reviewReason":"","reviewState":"等待审批","reviewStateNo":600,"reviewType":"","reviewTypeNo":310,"reviewUserName":"严峰-部门负责人","startDate":null,"userNo":4}],"userNo":2}
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
         * fromUserName : 虞勇-仓管
         * id : 3
         * purcFinishdate : null
         * purcId : 115470248704599
         * purcStartdate : 1547024870000
         * purcState : 进行中
         * purcSupplier : 南京
         * purcUsage : 测试
         * purchaseContentList : [{"annotation":"","goodsIdNo":"6923644282462","goodsName":"蒙牛纯牛奶","goodsNo":12,"goodsNum":36,"goodsType":"综合科","goodsTypeNo":132,"id":2,"purcNo":3},{"annotation":"","goodsIdNo":"6906791521028","goodsName":"阿华田","goodsNo":13,"goodsNum":35,"goodsType":"调度中心","goodsTypeNo":131,"id":3,"purcNo":3}]
         * reviewList : [{"fromUserName":"","goodsCount":0,"id":13,"objNo":3,"reviewDate":null,"reviewIndex":0,"reviewReason":"","reviewState":"等待审批","reviewStateNo":600,"reviewType":"","reviewTypeNo":310,"reviewUserName":"杨军-部门负责人","startDate":null,"userNo":5},{"fromUserName":"","goodsCount":0,"id":14,"objNo":3,"reviewDate":null,"reviewIndex":1,"reviewReason":"","reviewState":"等待审批","reviewStateNo":600,"reviewType":"","reviewTypeNo":310,"reviewUserName":"严峰-部门负责人","startDate":null,"userNo":4}]
         * userNo : 2
         */

        private String fromUserName;
        private int id;
        private Object purcFinishdate;
        private String purcId;
        private long purcStartdate;
        private String purcState;
        private String purcSupplier;
        private String purcUsage;
        private int userNo;
        private List<PurchaseContentListBean> purchaseContentList;
        private List<ReviewListBean> reviewList;

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

        public Object getPurcFinishdate() {
            return purcFinishdate;
        }

        public void setPurcFinishdate(Object purcFinishdate) {
            this.purcFinishdate = purcFinishdate;
        }

        public String getPurcId() {
            return purcId;
        }

        public void setPurcId(String purcId) {
            this.purcId = purcId;
        }

        public long getPurcStartdate() {
            return purcStartdate;
        }

        public void setPurcStartdate(long purcStartdate) {
            this.purcStartdate = purcStartdate;
        }

        public String getPurcState() {
            return purcState;
        }

        public void setPurcState(String purcState) {
            this.purcState = purcState;
        }

        public String getPurcSupplier() {
            return purcSupplier;
        }

        public void setPurcSupplier(String purcSupplier) {
            this.purcSupplier = purcSupplier;
        }

        public String getPurcUsage() {
            return purcUsage;
        }

        public void setPurcUsage(String purcUsage) {
            this.purcUsage = purcUsage;
        }

        public int getUserNo() {
            return userNo;
        }

        public void setUserNo(int userNo) {
            this.userNo = userNo;
        }

        public List<PurchaseContentListBean> getPurchaseContentList() {
            return purchaseContentList;
        }

        public void setPurchaseContentList(List<PurchaseContentListBean> purchaseContentList) {
            this.purchaseContentList = purchaseContentList;
        }

        public List<ReviewListBean> getReviewList() {
            return reviewList;
        }

        public void setReviewList(List<ReviewListBean> reviewList) {
            this.reviewList = reviewList;
        }

        public static class PurchaseContentListBean {
            /**
             * annotation :
             * goodsIdNo : 6923644282462
             * goodsName : 蒙牛纯牛奶
             * goodsNo : 12
             * goodsNum : 36
             * goodsType : 综合科
             * goodsTypeNo : 132
             * id : 2
             * purcNo : 3
             */

            private String annotation;
            private String goodsIdNo;
            private String goodsName;
            private int goodsNo;
            private int goodsNum;
            private String goodsType;
            private int goodsTypeNo;
            private int id;
            private int purcNo;

            public String getAnnotation() {
                return annotation;
            }

            public void setAnnotation(String annotation) {
                this.annotation = annotation;
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

            public int getPurcNo() {
                return purcNo;
            }

            public void setPurcNo(int purcNo) {
                this.purcNo = purcNo;
            }
        }

        public static class ReviewListBean {
            /**
             * fromUserName :
             * goodsCount : 0
             * id : 13
             * objNo : 3
             * reviewDate : null
             * reviewIndex : 0
             * reviewReason :
             * reviewState : 等待审批
             * reviewStateNo : 600
             * reviewType :
             * reviewTypeNo : 310
             * reviewUserName : 杨军-部门负责人
             * startDate : null
             * userNo : 5
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
