package com.example.administrator.warehousemanagementsystem.bean;

import java.util.List;

/**
 * author: ZhongMing
 * DATE: 2018/12/7 0007
 * Description:
 **/
public class GoodsType {

    /**
     * result : ok
     * data : [{"codeCode":131,"codeDetail":"生产类","id":9},{"codeCode":132,"codeDetail":"综合类","id":10}]
     */

    private String result;
    private List<DataBean> data;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * codeCode : 131
         * codeDetail : 生产类
         * id : 9
         */

        private int codeCode;
        private String codeDetail;
        private int id;

        public int getCodeCode() {
            return codeCode;
        }

        public void setCodeCode(int codeCode) {
            this.codeCode = codeCode;
        }

        public String getCodeDetail() {
            return codeDetail;
        }

        public void setCodeDetail(String codeDetail) {
            this.codeDetail = codeDetail;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
