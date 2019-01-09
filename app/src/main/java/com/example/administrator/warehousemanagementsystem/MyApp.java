package com.example.administrator.warehousemanagementsystem;

import android.app.Application;

import com.example.administrator.warehousemanagementsystem.bean.UserBean;

import java.io.Serializable;

import cn.jpush.android.api.JPushInterface;

/**
 * author: ZhongMing
 * DATE: 2018/11/21 0021
 * Description:
 * *
 **/
public class MyApp extends Application {
    public static final int SL_CHOOSE_PRODUCT = 0x239; //选择商品
    public static final int SL_SPPERSON = 0x483;//选择审批人
    public static final int REPORT_BT = 0x882;//选择物品后 报告数据
    public static final int STOREHOUSE_LIST = 0x7473;//仓库列表
    public static final int LOGIN_SUCCESS = 0x998;//登陆成功
    public static final int LOGIN_FAIL = 0x978;//登陆失败
    public static final int MENU_TYPE = 0X321;//获取物品类型
    public static final int MENU_DETAIL = 0x322;//获取商品
    public static final int POST_SUCCESS = 0x232;//提交成功
    public static final int SP_LIST = 0x858;//获取角色的待审批
    public static final int APPLY_DETAIL = 0x343;//获取申领订单详情
    public static final int PURCHASE_DETAIL = 0x1236;//获取采购单详情
    public static final int BUDGET_DETAIL = 0x1247;//获取预算单详情
    public static final int COMMIT_APPLY = 0x2345;//同意/拒绝订单
    public static final int HAVE_DONE = 0x3432;//已完成订单
    public static final int MY_APPLY_LIST = 0x2321;//我的申领单list
    public static final int MY_PURCHASE_LIST = 0x3244;//我的采购单list
    public static final int MY_BUDGET_LIST = 0x6543;//我的预算单list
    public static final int CKKC = 0x8573;//查询仓库库存
    public static final int CHANGE_SUCCESS = 0x28743;//更改密码成功
    public static final int COUNT_STOCK = 0x2340;//获取收费站领用情况的柱状图数据
    public static final int DEPT_LIST = 0x23435;//查看收费站列表
    public UserBean.DataBean user;
    public String token;

    public UserBean.DataBean getUser() {
        return user != null ? user : null;
    }

    public void setUser(UserBean.DataBean user) {
        this.user = user;
    }

    public String getToken() {
        return token != null ? token : null;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
    }


    public int getRoot() {
        return user != null ? user.getUserRoleNo() : -1;
    }

    public void initUser() {
        setUser(null);
    }
}