package com.example.administrator.warehousemanagementsystem.bean;

import java.util.List;

/**
 * author: ZhongMing
 * DATE: 2018/12/21 0021
 * Description:
 * 查看我的采购单list
 **/
public class PurchaseList {

    /**
     * result : ok
     * data : [{"id":6,"purcFinishdate":null,"purcId":"115452948553404","purcStartdate":1545294855000,"purcState":"进行中","purcSupplier":"南京","purcUsage":"我要买东西","purchaseContentList":null,"reviewList":null,"userNo":4},{"id":7,"purcFinishdate":null,"purcId":"115452950372641","purcStartdate":1545295037000,"purcState":"进行中","purcSupplier":"南京","purcUsage":"买东西","purchaseContentList":null,"reviewList":null,"userNo":4}]
     * count : 2
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
         * id : 6
         * purcFinishdate : null
         * purcId : 115452948553404
         * purcStartdate : 1545294855000
         * purcState : 进行中
         * purcSupplier : 南京
         * purcUsage : 我要买东西
         * purchaseContentList : null
         * reviewList : null
         * userNo : 4
         */

        private int id;
        private Object purcFinishdate;
        private String purcId;
        private long purcStartdate;
        private String purcState;
        private String purcSupplier;
        private String purcUsage;
        private Object purchaseContentList;
        private Object reviewList;
        private int userNo;

        @Override
        public String toString() {
            return "DataBean{" +
                    "id=" + id +
                    ", purcFinishdate=" + purcFinishdate +
                    ", purcId='" + purcId + '\'' +
                    ", purcStartdate=" + purcStartdate +
                    ", purcState='" + purcState + '\'' +
                    ", purcSupplier='" + purcSupplier + '\'' +
                    ", purcUsage='" + purcUsage + '\'' +
                    ", purchaseContentList=" + purchaseContentList +
                    ", reviewList=" + reviewList +
                    ", userNo=" + userNo +
                    '}';
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

        public Object getPurchaseContentList() {
            return purchaseContentList;
        }

        public void setPurchaseContentList(Object purchaseContentList) {
            this.purchaseContentList = purchaseContentList;
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
