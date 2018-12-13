package com.example.administrator.warehousemanagementsystem.bean;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * author: ZhongMing
 * DATE: 2018/11/26 0026
 * Description:该类存放待审批的详细页面实体类s
 **/
public class SPDetailBean implements Serializable {
    private String nowTime;//当前时间
    private String slTime;//申领时间
    private Integer state;//状态图片
    private String spName;//申领人姓名
    private String spBH;//申领人编号
    private String spBM;//申领人部门
    private String spUse;//用途
    private List<SPMX> spmx;//审批明细
    private String explain;//详情
    private static List<String> progressList;//物品详情
    private static int nowPoint;//当前的点


    @Override
    public String toString() {
        return "SPDetailBean{" +
                "nowTime='" + nowTime + '\'' +
                ", slTime='" + slTime + '\'' +
                ", state=" + state +
                ", spName='" + spName + '\'' +
                ", spBH='" + spBH + '\'' +
                ", spBM='" + spBM + '\'' +
                ", spUse='" + spUse + '\'' +
                ", spmx=" + spmx +
                ", explain='" + explain + '\'' +
                '}';
    }

    public static SPDetailBean waitSp() {
        List<SPMX> spmxList = new ArrayList<>();
        progressList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            progressList.add("审批人" + i);
        }
        for (int i = 0; i < 5; i++) {
            spmxList.add(new SPDetailBean.SPMX("物品" + i, "" + i * 20));
        }
        return new SPDetailBean("2018年12月10日17:11:19", "2018年12月10日17:11:30", 0, "钟泽西", "20180530123332", "号仓库", "用于写材料", spmxList, "需要尽快", progressList, nowPoint);
    }


    public static SPDetailBean doneSp() {
        List<SPMX> spmxList = new ArrayList<>();
        progressList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            progressList.add("审批人" + i);
        }
        for (int i = 0; i < 5; i++) {
            spmxList.add(new SPDetailBean.SPMX("物品" + i, "" + i * 20));
        }
        return new SPDetailBean("2018年12月10日17:11:19", "2018年12月10日17:11:30", 1, "钟泽西", "20180530123332", "号仓库", "用于写材料", spmxList, "需要尽快", progressList, nowPoint);
    }

    public static SPDetailBean removeSp() {
        List<SPMX> spmxList = new ArrayList<>();
        progressList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            progressList.add("审批人" + i);
        }
        for (int i = 0; i < 5; i++) {
            spmxList.add(new SPDetailBean.SPMX("物品" + i, "" + i * 20));
        }
        return new SPDetailBean("2018年12月10日17:11:19", "2018年12月10日17:11:30", 2, "钟泽西", "20180530123332", "号仓库", "用于写材料", spmxList, "需要尽快", progressList, nowPoint);
    }


    public SPDetailBean initData() {
        List<SPMX> spmxList = new ArrayList<>();
        progressList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            progressList.add("审批人" + i);
        }
        for (int i = 0; i < 5; i++) {
            spmxList.add(new SPDetailBean.SPMX("物品" + i, "" + i * 20));
        }
        return new SPDetailBean(1, "钟泽西", "20180530123332", "号仓库", "用于写材料", spmxList, "需要尽快", progressList, nowPoint);
    }


    public SPDetailBean(String nowTime, String slTime, Integer state, String spName, String spBH, String spBM, String spUse, List<SPMX> spmx, String explain, List<String> progressList, int nowPoint) {
        this.nowTime = nowTime;
        this.slTime = slTime;
        this.state = state;
        this.spName = spName;
        this.spBH = spBH;
        this.spBM = spBM;
        this.spUse = spUse;
        this.spmx = spmx;
        this.explain = explain;
        this.progressList = progressList;
        this.nowPoint = nowPoint;
    }

    public SPDetailBean() {
    }

    public SPDetailBean(Integer state, String spName, String spBH, String spBM, String spUse, List<SPMX> spmx, String explain, List<String> progressList, int nowPoint) {
        this.state = state;
        this.spName = spName;
        this.spBH = spBH;
        this.spBM = spBM;
        this.spUse = spUse;
        this.spmx = spmx;
        this.explain = explain;
        this.progressList = progressList;
        this.nowPoint = nowPoint;
    }

    public String getNowTime() {
        return nowTime;
    }

    public void setNowTime(String nowTime) {
        this.nowTime = nowTime;
    }

    public String getSlTime() {
        return slTime;
    }

    public void setSlTime(String slTime) {
        this.slTime = slTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getSpName() {
        return spName;
    }

    public void setSpName(String spName) {
        this.spName = spName;
    }

    public String getSpBH() {
        return spBH;
    }

    public void setSpBH(String spBH) {
        this.spBH = spBH;
    }

    public String getSpBM() {
        return spBM;
    }

    public void setSpBM(String spBM) {
        this.spBM = spBM;
    }

    public String getSpUse() {
        return spUse;
    }

    public void setSpUse(String spUse) {
        this.spUse = spUse;
    }

    public List<SPMX> getSpmx() {
        return spmx;
    }

    public void setSpmx(List<SPMX> spmx) {
        this.spmx = spmx;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public List<String> getProgressList() {
        return progressList;
    }

    public void setProgressList(List<String> progressList) {
        this.progressList = progressList;
    }

    public int getNowPoint() {
        return nowPoint;
    }

    public void setNowPoint(int nowPoint) {
        this.nowPoint = nowPoint;
    }

    /**
     * 审批明细
     */
    public static class SPMX implements Serializable {
        private String name;
        private String num;

        public SPMX(String name, String num) {
            this.name = name;
            this.num = num;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }
    }

}


