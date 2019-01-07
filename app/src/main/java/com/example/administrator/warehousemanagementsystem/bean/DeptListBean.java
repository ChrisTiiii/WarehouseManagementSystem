package com.example.administrator.warehousemanagementsystem.bean;

import java.util.List;

/**
 * author: ZhongMing
 * DATE: 2019/1/7 0007
 * Description:
 **/
public class DeptListBean {

    /**
     * result : ok
     * data : [{"deptAddress":"","deptName":"平潮收费站","deptType":"收费站","deptTypeNo":200,"id":1},{"deptAddress":"","deptName":"九华收费站","deptType":"收费站","deptTypeNo":200,"id":2},{"deptAddress":"","deptName":"季市收费站","deptType":"收费站","deptTypeNo":200,"id":3},{"deptAddress":"","deptName":"泰兴东收费站","deptType":"收费站","deptTypeNo":200,"id":4},{"deptAddress":"","deptName":"泰兴北收费站","deptType":"收费站","deptTypeNo":200,"id":5},{"deptAddress":"","deptName":"泰州收费站","deptType":"收费站","deptTypeNo":200,"id":6},{"deptAddress":"","deptName":"泰州医药城收费站","deptType":"收费站","deptTypeNo":200,"id":7},{"deptAddress":"","deptName":"葛市收费站","deptType":"收费站","deptTypeNo":200,"id":8},{"deptAddress":"","deptName":"广陵收费站","deptType":"收费站","deptTypeNo":200,"id":9},{"deptAddress":"","deptName":"大桥收费站","deptType":"收费站","deptTypeNo":200,"id":10},{"deptAddress":"","deptName":"如皋港收费站","deptType":"收费站","deptTypeNo":200,"id":11},{"deptAddress":"","deptName":"浦头收费站","deptType":"收费站","deptTypeNo":200,"id":12}]
     * count : 12
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
         * deptAddress :
         * deptName : 平潮收费站
         * deptType : 收费站
         * deptTypeNo : 200
         * id : 1
         */

        private String deptAddress;
        private String deptName;
        private String deptType;
        private int deptTypeNo;
        private int id;

        public String getDeptAddress() {
            return deptAddress;
        }

        public void setDeptAddress(String deptAddress) {
            this.deptAddress = deptAddress;
        }

        public String getDeptName() {
            return deptName;
        }

        public void setDeptName(String deptName) {
            this.deptName = deptName;
        }

        public String getDeptType() {
            return deptType;
        }

        public void setDeptType(String deptType) {
            this.deptType = deptType;
        }

        public int getDeptTypeNo() {
            return deptTypeNo;
        }

        public void setDeptTypeNo(int deptTypeNo) {
            this.deptTypeNo = deptTypeNo;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
