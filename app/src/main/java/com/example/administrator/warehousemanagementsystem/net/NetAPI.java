package com.example.administrator.warehousemanagementsystem.net;

import com.example.administrator.warehousemanagementsystem.bean.AddApplyBean;
import com.example.administrator.warehousemanagementsystem.bean.ApplyBean;
import com.example.administrator.warehousemanagementsystem.bean.BackData;
import com.example.administrator.warehousemanagementsystem.bean.GoodsDetailBean;
import com.example.administrator.warehousemanagementsystem.bean.GoodsType;
import com.example.administrator.warehousemanagementsystem.bean.ApplyList;
import com.example.administrator.warehousemanagementsystem.bean.PurchaseBean;
import com.example.administrator.warehousemanagementsystem.bean.PurchaseList;
import com.example.administrator.warehousemanagementsystem.bean.ReviewList;
import com.example.administrator.warehousemanagementsystem.bean.ReviewListHaveDone;
import com.example.administrator.warehousemanagementsystem.bean.SPPersonBean;
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

    @GET("getGoodsListBy.do")
    Observable<GoodsDetailBean> getGoodsDetail(@Query("goodsTypeNo") int goodsTypeNo);//获得物品详情

    @GET("getAllGoodsType.do")
    Observable<GoodsType> getGoodsType();//获得商品分类

    @GET("getUserList.do")
    Observable<SPPersonBean> getSPPersonType();//获取审批人分类

    @GET("getUserListBy.do")
    Observable<SPPersonBean> getSPPerson(@Query("userRoleNo") int userRoleNo);//获取审批人

    @FormUrlEncoded
    @POST("addApply.do")
    Observable<AddApplyBean> postApply(@Field("userNo") Integer userNo, @Field("note") String note, @Field("goodsMap") String goodsMap, @Field("userNoList") String userNoList);//提交申领订单

    @FormUrlEncoded
    @POST("addPurchase.do")
    Observable<PurchaseBean> postPurchase(@Field("userNo") Integer userNo, @Field("note") String note, @Field("goodsMap") String goodsMap, @Field("userNoList") String userNoList, @Field("supplier") String supplier);//提交采购单


    @GET("getReviewListBy.do")
    Observable<ReviewList> getReviewList(@Query("userNo") Integer userNo, @Query("page") int page, @Query("size") int size);//获取待审批订单

    @GET("getApplyById.do")
    Observable<ApplyBean> getApply(@Query("id") String id);//待审批申领单详情

    @GET("getPurchaseById.do")
    Observable<PurchaseBean> getPurchaseById(@Query("id") String id);//采购单详情

    @GET("agreeReview.do")
    Observable<BackData> agreeReview(@Query("userNo") Integer userNo, @Query("reviewNo") Integer reviewNo);//提交同意审批

    @GET("refuseReview.do")
    Observable<BackData> refuseReview(@Query("userNo") Integer userNo, @Query("reviewNo") Integer reviewNo);//提交拒绝审批

    @GET("getReviewListHaveDoneByMe.do")
    Observable<ReviewListHaveDone> getReviewListHaveDoneByMe(@Query("userNo") Integer userNo, @Query("page") int page, @Query("size") int size);//获取已完成订单


    @GET("getApplyListBy.do")
    Observable<ApplyList> getApplyList(@Query("userNo") Integer userNo, @Query("page") int page, @Query("size") int size);//获取我提交的申领单


    @GET("getPurchaseListBy.do")
    Observable<PurchaseList> getPurchaseList(@Query("userNo") Integer userNo, @Query("page") int page, @Query("size") int size);//获取我提交的采购单


}
