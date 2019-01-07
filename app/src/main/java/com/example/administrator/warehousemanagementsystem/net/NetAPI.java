package com.example.administrator.warehousemanagementsystem.net;

import android.content.Intent;

import com.example.administrator.warehousemanagementsystem.bean.AddApplyBean;
import com.example.administrator.warehousemanagementsystem.bean.ApplyBean;
import com.example.administrator.warehousemanagementsystem.bean.BackData;
import com.example.administrator.warehousemanagementsystem.bean.BudgetBean;
import com.example.administrator.warehousemanagementsystem.bean.BudgetList;
import com.example.administrator.warehousemanagementsystem.bean.CountStockOutRecordBean;
import com.example.administrator.warehousemanagementsystem.bean.DeptListBean;
import com.example.administrator.warehousemanagementsystem.bean.GoodsDetailBean;
import com.example.administrator.warehousemanagementsystem.bean.GoodsType;
import com.example.administrator.warehousemanagementsystem.bean.ApplyList;
import com.example.administrator.warehousemanagementsystem.bean.PurchaseBean;
import com.example.administrator.warehousemanagementsystem.bean.PurchaseList;
import com.example.administrator.warehousemanagementsystem.bean.ReviewList;
import com.example.administrator.warehousemanagementsystem.bean.ReviewListHaveDone;
import com.example.administrator.warehousemanagementsystem.bean.SPPersonBean;
import com.example.administrator.warehousemanagementsystem.bean.StockOutRecordBean;
import com.example.administrator.warehousemanagementsystem.bean.StorehouseBean;
import com.example.administrator.warehousemanagementsystem.bean.StorehouseList;
import com.example.administrator.warehousemanagementsystem.bean.UserBean;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * author: ZhongMing
 * DATE: 2018/12/6 0006
 * Description:
 **/
public interface NetAPI {
    @POST("login.do")
    Observable<UserBean> getUser(@Query("userid") String userid, @Query("pass") String pass, @Query("from") String app);//用户登录，获取用户角色

    @GET("getAllGoodsType.do")
    Observable<GoodsType> getGoodsType(@Query("from") String app, @Query("token") String token);//获得商品分类

    @GET("getGoodsListBy.do")
    Observable<GoodsDetailBean> getGoodsDetail(@Query("goodsTypeNo") int goodsTypeNo, @Query("from") String app, @Query("token") String token);//获得物品详情

    @GET("getUserList.do")
    Observable<SPPersonBean> getSPPersonType(@Query("from") String app, @Query("token") String token);//获取审批人分类

    @GET("getUserListForChoose.do")
    Observable<SPPersonBean> getSPPerson(@Query("id") int id, @Query("userRoleNo") int userRoleNo, @Query("from") String app, @Query("token") String token);//获取审批人

    @FormUrlEncoded
    @POST("addApply.do")
    Observable<AddApplyBean> postApply(@Field("userNo") Integer userNo, @Field("note") String note, @Field("goodsMap") String goodsMap, @Field("userNoList") String userNoList, @Query("from") String app, @Query("token") String token);//提交申领订单

    @FormUrlEncoded
    @POST("addPurchase.do")
    Observable<PurchaseBean> postPurchase(@Field("userNo") Integer userNo, @Field("note") String note, @Field("goodsMap") String goodsMap, @Field("userNoList") String userNoList, @Field("supplier") String supplier, @Query("from") String app, @Query("token") String token);//提交采购单

    @FormUrlEncoded
    @POST("addBudget.do")
    Observable<BudgetBean> postBudget(@Field("userNo") Integer userNo, @Field("note") String note, @Field("goodsMap") String goodsMap, @Field("userNoList") String userNoList, @Query("from") String app, @Query("token") String token);//提交预算单

    //同意/拒绝
    @GET("agreeReview.do")
    Observable<BackData> agreeReview(@Query("userNo") Integer userNo, @Query("reviewNo") Integer reviewNo, @Query("from") String app, @Query("token") String token);//提交同意审批

    @GET("refuseReview.do")
    Observable<BackData> refuseReview(@Query("userNo") Integer userNo, @Query("reviewNo") Integer reviewNo, @Query("reason") String reason, @Query("from") String app, @Query("token") String token);//提交拒绝审批

    @GET("getReviewListWaitForMe.do")
    Observable<ReviewList> getReviewList(@Query("userNo") Integer userNo, @Query("page") int page, @Query("size") int size, @Query("from") String app, @Query("token") String token);//获取待审批订单

    @GET("getReviewListHaveDoneByMe.do")
    Observable<ReviewListHaveDone> getReviewListHaveDoneByMe(@Query("userNo") Integer userNo, @Query("page") int page, @Query("size") int size, @Query("from") String app, @Query("token") String token);//获取已完成订单

    @GET("getApplyById.do")
    Observable<ApplyBean> getApplyById(@Query("id") String id, @Query("from") String app, @Query("token") String token);//审批详情

    @GET("getPurchaseById.do")
    Observable<PurchaseBean> getPurchaseById(@Query("id") String id, @Query("from") String app, @Query("token") String token);//采购单详情

    @GET("getBudgetById.do")
    Observable<BudgetBean> getBudgetById(@Query("id") String id, @Query("from") String app, @Query("token") String token);//预算详情

    @GET("getApplyListBy.do")
    Observable<ApplyList> getApplyList(@Query("userNo") Integer userNo, @Query("page") int page, @Query("size") int size, @Query("from") String app, @Query("token") String token);//获取我提交的申领单

    @GET("getPurchaseListBy.do")
    Observable<PurchaseList> getPurchaseList(@Query("userNo") Integer userNo, @Query("page") int page, @Query("size") int size, @Query("from") String app, @Query("token") String token);//获取我提交的采购单

    @GET("getBudgetListBy.do")
    Observable<BudgetList> getBudgetList(@Query("userNo") Integer userNo, @Query("page") int page, @Query("size") int size, @Query("from") String app, @Query("token") String token);//获取我提交的预算单

    @GET("getStorehouseList.do")
    Observable<StorehouseList> getStorehouseList(@Query("from") String app, @Query("token") String token);//获取全部仓库名

    @GET("getStorehouseList.do")
    Observable<StorehouseList> getStorehouseBy(@Query("storehouseAdmUserNo") Integer storehouseAdmUserNo, @Query("from") String app, @Query("token") String token);//获取根据仓库管理员id获取仓库

    @GET("getStockRecord.do")
    Observable<StorehouseBean> getStockRecord(@Query("storehouseNoStr") String storehouseNoStr, @Query("page") int page, @Query("size") int size, @Query("from") String app, @Query("token") String token);//获取仓库名

    @POST("changePass.do")
    Observable<BackData> changePass(@Query("id") int id, @Query("oldPass") String oldPass, @Query("newPass") String newPass, @Query("newPass2") String newPass2, @Query("from") String app, @Query("token") String token);//更改密码

    @GET("getStockOutRecord.do")
    Observable<StockOutRecordBean> getStockOutRecord(@Query("beginDate") String beginDate, @Query("endDate") String endDate, @Query("deptNo") int deptNo);//根据deptNo获取历史申领记录单饼状图

    @GET("countStockOutRecord.do")
    Observable<CountStockOutRecordBean> getCountStockOutRecord(@Query("beginDate") String beginDate, @Query("endDate") String endDate, @Query("deptNo") int deptNo);//根据deptNo获取柱状图


    @GET("getDeptListBy.do")
    Observable<DeptListBean> getDeptListBy(@Query("deptType") String deptType, @Query("page") String page, @Query("size") String size);//获取全部收费站
}
