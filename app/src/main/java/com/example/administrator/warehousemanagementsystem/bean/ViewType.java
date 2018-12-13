package com.example.administrator.warehousemanagementsystem.bean;


/**
 * author: ZhongMing
 * DATE: 2018/11/21 0021
 * Description: 申领和审批viewType
 **/
public class ViewType {
    //申领
    public final static Integer SL_TYPE_HEAD = 0;//用途
    public final static Integer SL_TYPE_DETAIL = 1;//明细
    public final static Integer SL_TYPE_ADD = 2;//添加
    public final static Integer SL_TYPE_EXPLAIN = 3;//详情
    public final static Integer SL_TYPE_LEADER = 4;//审批人
    //审批
    public final static Integer SP_HEAD = 5;//编号头像 人名所在部门 物品用途等
    public final static Integer SP_DETAIL = 6;//物品明细
    public final static Integer SP_EXPLAIN = 7;//领用详情
    public final static Integer SP_PROGRESS = 8;//过程
    private int Type;//Item类型类别

    public ViewType(Integer type) {
        Type = type;
    }

    public int getType() {
        return Type;
    }

    public void setType(Integer type) {
        Type = type;
    }
}
