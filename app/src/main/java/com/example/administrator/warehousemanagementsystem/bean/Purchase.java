package com.example.administrator.warehousemanagementsystem.bean;

import java.util.List;

/**
 * author: ZhongMing
 * DATE: 2018/12/18 0018
 * Description:
 **/
public class Purchase {

    /**
     * result : ok
     * data : {"id":3,"purcFinishdate":null,"purcId":"115450239615014","purcStartdate":1545023961501,"purcState":"进行中","purcSupplier":"供应商xxxxx","purcUsage":"采购单测试用途","purchaseContentList":[{"annotation":"","goodsIdNo":"6901668005731","goodsName":"奥利奥夹心饼干","goodsNo":1,"goodsNum":30,"id":5,"purcNo":3},{"annotation":"","goodsIdNo":"6906791521028","goodsName":"阿华田","goodsNo":2,"goodsNum":50,"id":6,"purcNo":3}],"reviewList":[{"id":34,"objNo":3,"reviewDate":null,"reviewIndex":0,"reviewState":"等待审批","reviewTypeNo":300,"userNo":5},{"id":35,"objNo":3,"reviewDate":null,"reviewIndex":1,"reviewState":"等待审批","reviewTypeNo":300,"userNo":6}],"userNo":4}
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
         * id : 3
         * purcFinishdate : null
         * purcId : 115450239615014
         * purcStartdate : 1545023961501
         * purcState : 进行中
         * purcSupplier : 供应商xxxxx
         * purcUsage : 采购单测试用途
         * purchaseContentList : [{"annotation":"","goodsIdNo":"6901668005731","goodsName":"奥利奥夹心饼干","goodsNo":1,"goodsNum":30,"id":5,"purcNo":3},{"annotation":"","goodsIdNo":"6906791521028","goodsName":"阿华田","goodsNo":2,"goodsNum":50,"id":6,"purcNo":3}]
         * reviewList : [{"id":34,"objNo":3,"reviewDate":null,"reviewIndex":0,"reviewState":"等待审批","reviewTypeNo":300,"userNo":5},{"id":35,"objNo":3,"reviewDate":null,"reviewIndex":1,"reviewState":"等待审批","reviewTypeNo":300,"userNo":6}]
         * userNo : 4
         */

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
             * goodsIdNo : 6901668005731
             * goodsName : 奥利奥夹心饼干
             * goodsNo : 1
             * goodsNum : 30
             * id : 5
             * purcNo : 3
             */

            private String annotation;
            private String goodsIdNo;
            private String goodsName;
            private int goodsNo;
            private int goodsNum;
            private int id;
            private int purcNo;

            @Override
            public String toString() {
                return "PurchaseContentListBean{" +
                        "annotation='" + annotation + '\'' +
                        ", goodsIdNo='" + goodsIdNo + '\'' +
                        ", goodsName='" + goodsName + '\'' +
                        ", goodsNo=" + goodsNo +
                        ", goodsNum=" + goodsNum +
                        ", id=" + id +
                        ", purcNo=" + purcNo +
                        '}';
            }

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
             * id : 34
             * objNo : 3
             * reviewDate : null
             * reviewIndex : 0
             * reviewState : 等待审批
             * reviewTypeNo : 300
             * userNo : 5
             */

            private int id;
            private int objNo;
            private Object reviewDate;
            private int reviewIndex;
            private String reviewState;
            private int reviewTypeNo;
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

            public int getUserNo() {
                return userNo;
            }

            public void setUserNo(int userNo) {
                this.userNo = userNo;
            }
        }
    }
}
