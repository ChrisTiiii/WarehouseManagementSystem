package com.example.administrator.warehousemanagementsystem.bean;

/**
 * author: ZhongMing
 * DATE: 2018/12/5 0005
 * Description:
 **/
public class UserBean {
    /**
     * result : ok
     * token : 4B7DE20A4C7AA02B6BA3C20CF3ECCC7F
     * data : {"deptNo":201,"id":1,"userId":"admin@admin.com","userIdCard":"320000200001012222","userName":"系统管理员1","userRoleNo":150,"userStateNo":1,"userTypeNo":0}
     */

    private String result;
    private String token;
    private DataBean data;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * deptNo : 201
         * id : 1
         * userId : admin@admin.com
         * userIdCard : 320000200001012222
         * userName : 系统管理员1
         * userRoleNo : 150
         * userStateNo : 1
         * userTypeNo : 0
         */

        private int deptNo;
        private int id;
        private String userId;
        private String userIdCard;
        private String userName;
        private int userRoleNo;
        private int userStateNo;
        private int userTypeNo;


        @Override
        public String toString() {
            return "DataBean{" +
                    "deptNo=" + deptNo +
                    ", id=" + id +
                    ", userId='" + userId + '\'' +
                    ", userIdCard='" + userIdCard + '\'' +
                    ", userName='" + userName + '\'' +
                    ", userRoleNo=" + userRoleNo +
                    ", userStateNo=" + userStateNo +
                    ", userTypeNo=" + userTypeNo +
                    '}';
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

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUserIdCard() {
            return userIdCard;
        }

        public void setUserIdCard(String userIdCard) {
            this.userIdCard = userIdCard;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public int getUserRoleNo() {
            return userRoleNo;
        }

        public void setUserRoleNo(int userRoleNo) {
            this.userRoleNo = userRoleNo;
        }

        public int getUserStateNo() {
            return userStateNo;
        }

        public void setUserStateNo(int userStateNo) {
            this.userStateNo = userStateNo;
        }

        public int getUserTypeNo() {
            return userTypeNo;
        }

        public void setUserTypeNo(int userTypeNo) {
            this.userTypeNo = userTypeNo;
        }
    }


}