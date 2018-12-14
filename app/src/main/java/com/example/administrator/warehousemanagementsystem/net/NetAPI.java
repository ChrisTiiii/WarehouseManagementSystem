package com.example.administrator.warehousemanagementsystem.net;

import com.example.administrator.warehousemanagementsystem.bean.AddApplyBean;
import com.example.administrator.warehousemanagementsystem.bean.GoodsDetailBean;
import com.example.administrator.warehousemanagementsystem.bean.GoodsType;
import com.example.administrator.warehousemanagementsystem.bean.SPPersonBean;
import com.example.administrator.warehousemanagementsystem.bean.UserBean;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * author: ZhongMing
 * DATE: 2018/12/6 0006
 * Description:
 **/
public interface NetAPI {
    @GET("login.do")
    Observable<UserBean> getUser(@Query("userid") String userid, @Query("pass") String pass, @Query("from") String app);//用户登录，获取用户角色

    @GET("getGoodsListBy.do")
    Observable<GoodsDetailBean> getGoodsDetail(@Query("goodsTypeNo") int goodsTypeNo, @Query("page") int page, @Query("size") int size);//获得物品详情

    @GET("getAllGoodsType.do")
    Observable<GoodsType> getGoodsType();//获得商品分类

    @GET("getUserList.do")
    Observable<SPPersonBean> getSPPersonType();//获取审批人分类

    @GET("getUserListBy.do")
    Observable<SPPersonBean> getSPPerson(@Query("userRoleNo") int userRoleNo);//获取审批人

    @Headers({"Content-Type: application/json", "Accept: application/json"})//需要添加头
    @POST("addApply.do")
    Observable<AddApplyBean> postApply(@Body RequestBody route);//提交订单


}
