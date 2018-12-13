package com.example.administrator.warehousemanagementsystem.net;

import android.widget.Toast;

import com.example.administrator.warehousemanagementsystem.MyApp;
import com.example.administrator.warehousemanagementsystem.bean.GoodsDetailBean;
import com.example.administrator.warehousemanagementsystem.bean.GoodsType;
import com.example.administrator.warehousemanagementsystem.bean.SPPersonBean;
import com.example.administrator.warehousemanagementsystem.bean.UserBean;
import com.example.administrator.warehousemanagementsystem.util.MessageEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * author: ZhongMing
 * DATE: 2018/12/6 0006
 * Description:实现网络接口访问服务器数据
 **/
public class NetServerImp {
    private Retrofit retrofit;
    private static final String BASE_URL = "http://10.101.208.119:8080/";
    MyApp myApp;
    private static String APP = "app";
    String msg = "";

    public NetServerImp(MyApp myApp) {
        this.myApp = myApp;
    }

    /**
     * 获取物品类型
     */
    public void getGoodsType() {
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
        NetAPI netAPI = retrofit.create(NetAPI.class);
        netAPI.getGoodsType().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<GoodsType>() {
            @Override
            public void onCompleted() {
                System.out.println("getGoodsType加载完成");
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(myApp, "请检查你的网络是否连接正常", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNext(GoodsType goodsType) {
                if (goodsType.getResult().equals("ok"))
                    if (goodsType != null) {
                        List<Map<String, Object>> tempList = new ArrayList<>();
                        for (GoodsType.DataBean goodType : goodsType.getData()) {
                            Map<String, Object> map = new HashMap<>();
                            map.put("type", goodType.getCodeCode());
                            map.put("name", goodType.getCodeDetail());
                            tempList.add(map);
                        }
                        System.out.println("goodsType size:" + goodsType.getData().size());
                        EventBus.getDefault().post(new MessageEvent(myApp.MENU_TYPE, tempList, 1));
                    }
            }
        });
    }

    /**
     * 获取数据详情列表
     *
     * @param goodsTypeNo
     * @param page
     * @param size
     */
    public void getGoodsDetail(int goodsTypeNo, int page, int size) {
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
        NetAPI netAPI = retrofit.create(NetAPI.class);
        netAPI.getGoodsDetail(goodsTypeNo, page, size).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<GoodsDetailBean>() {
            @Override
            public void onCompleted() {
                System.out.println("getGoodsDetail加载完成");
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(myApp, "请检查你的网络是否连接正常", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNext(GoodsDetailBean goodsDetailBean) {
                if (goodsDetailBean.getResult().equals("ok"))
                    if (goodsDetailBean.getData() != null) {
                        List<Map<String, Object>> tempList = new ArrayList<>();
                        for (GoodsDetailBean.DataBean dataBean : goodsDetailBean.getData()) {
                            Map<String, Object> map = new HashMap<>();
                            map.put("name", dataBean.getGoodsName());
                            map.put("code",dataBean.getGoodsId());
                            map.put("count", goodsDetailBean.getCount());
                            tempList.add(map);
                        }
                        EventBus.getDefault().post(new MessageEvent(myApp.MENU_DETAIL, tempList, 1));
                    }
            }
        });

    }


    /**
     * @param userid 用户名
     * @param pass   密码
     *               获取用户信息 登录使用 并设置全局变量
     */
    public void getUser(final String userid, String pass) {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)//基础URL 建议以 / 结尾
                .addConverterFactory(GsonConverterFactory.create())//设置 Json 转换器
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//RxJava 适配器
                .build();
        NetAPI netAPI = retrofit.create(NetAPI.class);
        netAPI.getUser(userid, pass, APP).subscribeOn(Schedulers.io())//IO线程加载数据
                .observeOn(AndroidSchedulers.mainThread())//主线程显示数据
                .subscribe(new Subscriber<UserBean>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("getUser加载完成");
                        EventBus.getDefault().post(new MessageEvent(myApp.LOGIN_SUCCESS, msg));
                    }

                    @Override
                    public void onError(Throwable e) {
                        EventBus.getDefault().post(new MessageEvent(myApp.LOGIN_FAIL, "error"));
                        System.out.println("getUser加载失败：" + e.getMessage());
                    }

                    @Override
                    public void onNext(UserBean userBean) {
                        System.out.println("backData:" + userBean.getResult());
                        if (userBean.getResult().equals("ok")) {
                            if (userBean.getData() != null) {
                                System.out.println(userBean.getData().toString());
                                myApp.setUser(userBean.getData());
                            }
                        } else if (userBean.getResult().equals("notReg") || userBean.getResult().equals("useridError") || userBean.getResult().equals("passError")) {
                            msg = "请检查用户名或密码是否正确";
                        }
                    }
                });
    }


    /**
     * 获取审批人分类
     */
    public void getSPPersonType() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)//基础URL 建议以 / 结尾
                .addConverterFactory(GsonConverterFactory.create())//设置 Json 转换器
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//RxJava 适配器
                .build();
        NetAPI netAPI = retrofit.create(NetAPI.class);
        netAPI.getSPPersonType().subscribeOn(Schedulers.io())//IO线程加载数据
                .observeOn(AndroidSchedulers.mainThread())//主线程显示数据
                .subscribe(new Subscriber<SPPersonBean>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("getSPPersonType加载完成");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(myApp, "请检查你的网络是否连接正常", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(SPPersonBean spPersonBean) {
                        if (spPersonBean.getResult().equals("ok")) {
                            if (spPersonBean.getData() != null) {
                                List<Map<String, Object>> tempList = new ArrayList<>();
                                for (SPPersonBean.DataBean spp : spPersonBean.getData()) {
                                    Map<String, Object> map = new HashMap<>();
                                    map.put("type", spp.getUserRoleNo());
                                    map.put("name", spp.getUserRole());
                                    tempList.add(map);
                                }
                                EventBus.getDefault().post(new MessageEvent(myApp.MENU_TYPE, tempList, 1));
                            }
                        }
                    }
                });
    }


    /**
     * 获取审批人姓名 type
     *
     * @param userRoleNo 当前menuType
     */
    public void getSPPerson(int userRoleNo) {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)//基础URL 建议以 / 结尾
                .addConverterFactory(GsonConverterFactory.create())//设置 Json 转换器
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//RxJava 适配器
                .build();
        NetAPI netAPI = retrofit.create(NetAPI.class);
        netAPI.getSPPerson(userRoleNo).subscribeOn(Schedulers.io())//IO线程加载数据
                .observeOn(AndroidSchedulers.mainThread())//主线程显示数据
                .subscribe(new Subscriber<SPPersonBean>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("getSPPerson加载完成");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(myApp, "请检查你的网络是否连接正常", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(SPPersonBean spPersonBean) {
                        if (spPersonBean.getResult().equals("ok")) {
                            if (spPersonBean.getData() != null) {
                                List<Map<String, Object>> tempList = new ArrayList<>();
                                for (SPPersonBean.DataBean spp : spPersonBean.getData()) {
                                    Map<String, Object> map = new HashMap<>();
                                    map.put("type", spp.getUserRoleNo());
                                    map.put("name", spp.getUserName());
                                    map.put("count", spPersonBean.getCount());
                                    tempList.add(map);
                                    System.out.println(spp.toString());
                                }
                                EventBus.getDefault().post(new MessageEvent(myApp.MENU_DETAIL, tempList, 1));
                            }
                        }
                    }
                });

    }

}
