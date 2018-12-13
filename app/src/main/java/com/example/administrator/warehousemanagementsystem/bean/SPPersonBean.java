package com.example.administrator.warehousemanagementsystem.bean;

import java.util.List;

/**
 * author: ZhongMing
 * DATE: 2018/12/11 0011
 * Description:
 **/
public class SPPersonBean {
    /**
     * result : ok
     * data : [{"deptNo":2,"id":1,"userId":"admin@admin.com","userIdCard":"320000200001011111","userName":"系统管理员1","userRole":"root","userRoleNo":150,"userState":"开","userStateNo":1,"userType":"","userTypeNo":0},{"deptNo":1,"id":2,"userId":"100@1.com","userIdCard":"320000200001011111","userName":"收费站管理员1","userRole":"收费站管理员","userRoleNo":100,"userState":"开","userStateNo":1,"userType":"","userTypeNo":0},{"deptNo":1,"id":3,"userId":"110@1.com","userIdCard":"320000200001011111","userName":"收费站站长1","userRole":"收费站站长","userRoleNo":110,"userState":"开","userStateNo":1,"userType":"","userTypeNo":0},{"deptNo":2,"id":4,"userId":"120@1.com","userIdCard":"320000200001011111","userName":"仓库管理员1","userRole":"仓库管理员","userRoleNo":120,"userState":"开","userStateNo":1,"userType":"","userTypeNo":0},{"deptNo":1,"id":5,"userId":"130@1.com","userIdCard":"320000200001011111","userName":"主任1","userRole":"主任","userRoleNo":130,"userState":"开","userStateNo":1,"userType":"生产类","userTypeNo":131}]
     * count : 6
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
         * deptNo : 2
         * id : 1
         * userId : admin@admin.com
         * userIdCard : 320000200001011111
         * userName : 系统管理员1
         * userRole : root
         * userRoleNo : 150
         * userState : 开
         * userStateNo : 1
         * userType :
         * userTypeNo : 0
         */

        private int deptNo;
        private int id;
        private String userId;
        private String userIdCard;
        private String userName;
        private String userRole;
        private int userRoleNo;
        private String userState;
        private int userStateNo;
        private String userType;
        private int userTypeNo;

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

        public String getUserRole() {
            return userRole;
        }

        public void setUserRole(String userRole) {
            this.userRole = userRole;
        }

        public int getUserRoleNo() {
            return userRoleNo;
        }

        public void setUserRoleNo(int userRoleNo) {
            this.userRoleNo = userRoleNo;
        }

        public String getUserState() {
            return userState;
        }

        public void setUserState(String userState) {
            this.userState = userState;
        }

        public int getUserStateNo() {
            return userStateNo;
        }

        public void setUserStateNo(int userStateNo) {
            this.userStateNo = userStateNo;
        }

        public String getUserType() {
            return userType;
        }

        public void setUserType(String userType) {
            this.userType = userType;
        }

        public int getUserTypeNo() {
            return userTypeNo;
        }

        public void setUserTypeNo(int userTypeNo) {
            this.userTypeNo = userTypeNo;
        }
    }
}
