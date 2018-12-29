package com.example.administrator.warehousemanagementsystem.bean;

import java.util.List;

/**
 * author: ZhongMing
 * DATE: 2018/12/25 0025
 * Description:
 **/
public class StorehouseList {

    /**
     * result : ok
     * data : [{"id":1,"storehouseAddress":"管理处3号楼101","storehouseAdmUserNo":0,"storehouseName":"生产仓库01","storehouseType":"生产类","storehouseTypeNo":131},{"id":2,"storehouseAddress":"管理处4号楼102","storehouseAdmUserNo":0,"storehouseName":"综合仓库03","storehouseType":"综合类","storehouseTypeNo":132}]
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
         * id : 1
         * storehouseAddress : 管理处3号楼101
         * storehouseAdmUserNo : 0
         * storehouseName : 生产仓库01
         * storehouseType : 生产类
         * storehouseTypeNo : 131
         */

        private int id;
        private String storehouseAddress;
        private int storehouseAdmUserNo;
        private String storehouseName;
        private String storehouseType;
        private int storehouseTypeNo;

        @Override
        public String toString() {
            return "DataBean{" +
                    "id=" + id +
                    ", storehouseAddress='" + storehouseAddress + '\'' +
                    ", storehouseAdmUserNo=" + storehouseAdmUserNo +
                    ", storehouseName='" + storehouseName + '\'' +
                    ", storehouseType='" + storehouseType + '\'' +
                    ", storehouseTypeNo=" + storehouseTypeNo +
                    '}';
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getStorehouseAddress() {
            return storehouseAddress;
        }

        public void setStorehouseAddress(String storehouseAddress) {
            this.storehouseAddress = storehouseAddress;
        }

        public int getStorehouseAdmUserNo() {
            return storehouseAdmUserNo;
        }

        public void setStorehouseAdmUserNo(int storehouseAdmUserNo) {
            this.storehouseAdmUserNo = storehouseAdmUserNo;
        }

        public String getStorehouseName() {
            return storehouseName;
        }

        public void setStorehouseName(String storehouseName) {
            this.storehouseName = storehouseName;
        }

        public String getStorehouseType() {
            return storehouseType;
        }

        public void setStorehouseType(String storehouseType) {
            this.storehouseType = storehouseType;
        }

        public int getStorehouseTypeNo() {
            return storehouseTypeNo;
        }

        public void setStorehouseTypeNo(int storehouseTypeNo) {
            this.storehouseTypeNo = storehouseTypeNo;
        }
    }
}
