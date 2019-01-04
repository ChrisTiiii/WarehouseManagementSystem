package com.example.administrator.warehousemanagementsystem.bean;

import java.util.List;

/**
 * author: ZhongMing
 * DATE: 2019/1/4 0004
 * Description:
 **/
public class CountStockOutRecordBean {

    /**
     * result : ok
     * data : [{"key":"黑笔","value":15},{"key":"冰红茶","value":2},{"key":"果缤纷","value":36},{"key":"阿华田","value":72}]
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
         * key : 黑笔
         * value : 15
         */

        private String key;
        private int value;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }
}
