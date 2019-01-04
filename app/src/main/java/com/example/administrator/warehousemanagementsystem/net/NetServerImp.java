package com.example.administrator.warehousemanagementsystem.net;

import android.widget.Toast;

import com.example.administrator.warehousemanagementsystem.MyApp;
import com.example.administrator.warehousemanagementsystem.bean.AddApplyBean;
import com.example.administrator.warehousemanagementsystem.bean.ApplyBean;
import com.example.administrator.warehousemanagementsystem.bean.BackData;
import com.example.administrator.warehousemanagementsystem.bean.BudgetBean;
import com.example.administrator.warehousemanagementsystem.bean.BudgetList;
import com.example.administrator.warehousemanagementsystem.bean.CountStockOutRecordBean;
import com.example.administrator.warehousemanagementsystem.bean.GoodsDetailBean;
import com.example.administrator.warehousemanagementsystem.bean.GoodsType;
import com.example.administrator.warehousemanagementsystem.bean.ApplyList;
import com.example.administrator.warehousemanagementsystem.bean.MyGoods;
import com.example.administrator.warehousemanagementsystem.bean.PurchaseBean;
import com.example.administrator.warehousemanagementsystem.bean.PurchaseList;
import com.example.administrator.warehousemanagementsystem.bean.StoreHouseReport;
import com.example.administrator.warehousemanagementsystem.bean.ReviewList;
import com.example.administrator.warehousemanagementsystem.bean.ReviewListHaveDone;
import com.example.administrator.warehousemanagementsystem.bean.SPPersonBean;
import com.example.administrator.warehousemanagementsystem.bean.StockOutRecordBean;
import com.example.administrator.warehousemanagementsystem.bean.StorehouseBean;
import com.example.administrator.warehousemanagementsystem.bean.StorehouseList;
import com.example.administrator.warehousemanagementsystem.bean.UserBean;
import com.example.administrator.warehousemanagementsystem.util.MessageEvent;
import com.example.administrator.warehousemanagementsystem.util.MyDialog;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
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
    private NetAPI netAPI;
    private static final String BASE_URL = "http://192.168.0.88:8080/";  //10.101.80.119  10.101.208.119 120
    MyApp myApp;
    private static String APP = "app";
    String msg = "";
    OkHttpClient client;

    /**
     * 初始化retrofit netApi
     *
     * @param myApp
     */
    public NetServerImp(MyApp myApp) {
        this.myApp = myApp;
        OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder();
        client = httpBuilder.readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS).writeTimeout(30, TimeUnit.SECONDS) //设置超时
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)//基础URL 建议以 / 结尾
                .addConverterFactory(GsonConverterFactory.create())//设置 Json 转换器
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//RxJava 适配器
                .client(client)
                .build();
        netAPI = retrofit.create(NetAPI.class);

    }

    /**
     * 获取物品类型
     */
    public void getGoodsType() {
        netAPI.getGoodsType(APP, myApp.getToken()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<GoodsType>() {
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
                        EventBus.getDefault().post(new MessageEvent(myApp.MENU_TYPE, tempList));
                    }
            }
        });
    }

    /**
     * 获取数据详情列表
     *
     * @param menuType
     */
    public void getGoodsDetail(int menuType, MyDialog myDialog) {
        System.out.println(menuType);
        netAPI.getGoodsDetail(menuType, APP, myApp.getToken()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<GoodsDetailBean>() {
            @Override
            public void onCompleted() {
                myDialog.dissDilalog();
                System.out.println("getGoodsDetail加载完成");
            }

            @Override
            public void onError(Throwable e) {
                myDialog.dissDilalog();
                Toast.makeText(myApp, "请检查你的网络是否连接正常", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNext(GoodsDetailBean goodsDetailBean) {
                if (goodsDetailBean.getResult().equals("ok"))
                    if (goodsDetailBean.getData() != null) {
                        List<Map<String, Object>> tempList = new ArrayList<>();
                        for (GoodsDetailBean.DataBean dataBean : goodsDetailBean.getData()) {
                            Map<String, Object> map = new HashMap<>();
                            map.put("goodsType", dataBean.getGoodsTypeNo());
                            map.put("name", dataBean.getGoodsName());
                            map.put("code", dataBean.getId());
                            map.put("count", goodsDetailBean.getCount());
                            tempList.add(map);
                        }
                        EventBus.getDefault().post(new MessageEvent(myApp.MENU_DETAIL, tempList));
                    }
            }
        });

    }


    /**
     * @param userid 用户名
     * @param pass   密码
     *               获取用户信息 登录使用 并设置全局变量
     */
    public void getUser(final String userid, String pass, MyDialog myDialog) {
        netAPI.getUser(userid, pass, APP).subscribeOn(Schedulers.io())//IO线程加载数据
                .observeOn(AndroidSchedulers.mainThread())//主线程显示数据
                .subscribe(new Subscriber<UserBean>() {
                    @Override
                    public void onCompleted() {
                        myDialog.dissDilalog();
                        System.out.println("getUser加载完成");
                        EventBus.getDefault().post(new MessageEvent(myApp.LOGIN_SUCCESS, msg));
                    }

                    @Override
                    public void onError(Throwable e) {
                        myDialog.dissDilalog();
                        EventBus.getDefault().post(new MessageEvent(myApp.LOGIN_FAIL, "error"));
                        System.out.println("getUser加载失败：" + e.getMessage());
                    }

                    @Override
                    public void onNext(UserBean userBean) {
                        if (userBean.getResult().equals("ok")) {
                            if (userBean.getData() != null) {
                                System.out.println(userBean.getData().toString() + "token:" + userBean.getToken());
                                myApp.setUser(userBean.getData());
                                myApp.setToken(userBean.getToken());
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
        netAPI.getSPPersonType(APP, myApp.getToken()).subscribeOn(Schedulers.io())//IO线程加载数据
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
                                Set set = new HashSet();//去重操作
                                List newList = new ArrayList();
                                for (Iterator iter = tempList.iterator(); iter.hasNext(); ) {
                                    Object element = iter.next();
                                    if (set.add(element))
                                        newList.add(element);
                                }
                                tempList.clear();
                                tempList.addAll(newList);
                                EventBus.getDefault().post(new MessageEvent(myApp.MENU_TYPE, tempList));
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
    public void getSPPerson(int userRoleNo, MyDialog myDialog) {
        netAPI.getSPPerson(myApp.getUser().getId(), userRoleNo, APP, myApp.getToken()).subscribeOn(Schedulers.io())//IO线程加载数据
                .observeOn(AndroidSchedulers.mainThread())//主线程显示数据
                .subscribe(new Subscriber<SPPersonBean>() {
                    @Override
                    public void onCompleted() {
                        myDialog.dissDilalog();
                        System.out.println("getSPPerson加载完成");
                    }

                    @Override
                    public void onError(Throwable e) {
                        myDialog.dissDilalog();
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
                                    map.put("code", spp.getId());
                                    map.put("name", spp.getUserName());
                                    map.put("count", spPersonBean.getCount());
                                    tempList.add(map);
                                    System.out.println(spp.toString());
                                }
                                EventBus.getDefault().post(new MessageEvent(myApp.MENU_DETAIL, tempList));
                            }
                        }
                    }
                });

    }

    public void postPurchase(Integer userNo, String note, String goodsMap, String userNoList, String supplier, MyDialog myDialog) {
        netAPI.postPurchase(userNo, note, goodsMap, userNoList, supplier, APP, myApp.getToken()).subscribeOn(Schedulers.io())//IO线程加载数据
                .observeOn(AndroidSchedulers.mainThread())//主线程显示数据
                .subscribe(new Subscriber<PurchaseBean>() {
                    @Override
                    public void onCompleted() {
                        myDialog.dissDilalog();
                        EventBus.getDefault().post(new MessageEvent(myApp.POST_SUCCESS, ""));
                        Toast.makeText(myApp, "提交成功", Toast.LENGTH_SHORT).show();
                        System.out.println("postApply success");
                    }

                    @Override
                    public void onError(Throwable e) {
                        myDialog.dissDilalog();
                        Toast.makeText(myApp, "请检查你的网络是否连接正常", Toast.LENGTH_SHORT).show();
                        System.out.println("错误原因：" + e.getMessage());
                    }

                    @Override
                    public void onNext(PurchaseBean purchase) {
                        System.out.println("result:" + purchase.getResult());
                        if (purchase.getResult().equals("ok")) {
                            System.out.println("data:" + purchase.getData().getPurchaseContentList().toString());
                        }
                    }
                });
    }


    /**
     * 提交申领数据订单
     */
    public void postApply(Integer userNo, String note, String goodsMap, String userNoList, MyDialog myDialog) {
        System.out.println(userNo + note + goodsMap + userNoList);
        netAPI.postApply(userNo, note, goodsMap, userNoList, APP, myApp.getToken()).subscribeOn(Schedulers.io())//IO线程加载数据
                .observeOn(AndroidSchedulers.mainThread())//主线程显示数据
                .subscribe(new Subscriber<AddApplyBean>() {
                    @Override
                    public void onCompleted() {
                        myDialog.dissDilalog();
                        EventBus.getDefault().post(new MessageEvent(myApp.POST_SUCCESS, ""));
                        Toast.makeText(myApp, "提交成功", Toast.LENGTH_SHORT).show();
                        System.out.println("postApply success");
                    }

                    @Override
                    public void onError(Throwable e) {
                        myDialog.dissDilalog();
                        System.out.println("错误提示：" + e.getMessage());
                        Toast.makeText(myApp, "请检查你的网络是否连接正常", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(AddApplyBean addApplyBean) {
                        System.out.println("result:" + addApplyBean.getResult());
                        if (addApplyBean.getResult().equals("ok"))
                            System.out.println("data:" + addApplyBean.getData().getApplyContentList().toString());
                    }
                });
    }


    /**
     * 提交预算单
     */

    public void postBudget(Integer userNo, String note, String goodsMap, String userNoList, MyDialog myDialog) {
        System.out.println(userNo + note + goodsMap + userNoList);
        netAPI.postBudget(userNo, note, goodsMap, userNoList, APP, myApp.getToken()).subscribeOn(Schedulers.io())//IO线程加载数据
                .observeOn(AndroidSchedulers.mainThread())//主线程显示数据
                .subscribe(new Subscriber<BudgetBean>() {
                    @Override
                    public void onCompleted() {
                        myDialog.dissDilalog();
                        EventBus.getDefault().post(new MessageEvent(myApp.POST_SUCCESS, ""));
                        Toast.makeText(myApp, "提交成功", Toast.LENGTH_SHORT).show();
                        System.out.println("postBudget success");
                    }

                    @Override
                    public void onError(Throwable e) {
                        myDialog.dissDilalog();
                        System.out.println("错误提示：" + e.getMessage());
                        Toast.makeText(myApp, "请检查你的网络是否连接正常", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(BudgetBean budget) {
                        System.out.println("result:" + budget.getResult());
                        if (budget.getResult().equals("ok"))
                            System.out.println("data:" + budget.getData().getBudgetContentList().toString());
                    }
                });
    }

    /**
     * 获取待审批订单List
     *
     * @param page
     * @param size
     */
    public void getReviewList(int page, int size, SmartRefreshLayout refreshLayout, MyDialog myDialog) {
        netAPI.getReviewList(myApp.user.getId(), page, size, APP, myApp.getToken()).subscribeOn(Schedulers.io())//IO线程加载数据
                .observeOn(AndroidSchedulers.mainThread())//主线程显示数据
                .subscribe(new Subscriber<ReviewList>() {
                    @Override
                    public void onCompleted() {
                        myDialog.dissDilalog();
                        System.out.println("getReviewList已完成");
                        refreshLayout.finishRefresh();
                        refreshLayout.finishLoadmore();
                    }

                    @Override
                    public void onError(Throwable e) {
                        myDialog.dissDilalog();
                        Toast.makeText(myApp, "请检查你的网络是否连接正常", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(ReviewList reviewList) {
                        if (reviewList.getResult().equals("ok")) {
                            MessageEvent messageEvent = new MessageEvent(myApp.SP_LIST);
                            messageEvent.setReviewLists(reviewList.getData());
                            EventBus.getDefault().post(messageEvent);
                        }
                    }
                });
    }

    /**
     * 获取申请单详情
     */
    public void getApplyById(String applyNo, MyDialog myDialog) {
        System.out.println("applyNo:++++++++++" + applyNo);
        netAPI.getApplyById(applyNo, APP, myApp.getToken()).subscribeOn(Schedulers.io())//IO线程加载数据
                .observeOn(AndroidSchedulers.mainThread())//主线程显示数据
                .subscribe(new Subscriber<ApplyBean>() {
                    @Override
                    public void onCompleted() {
                        myDialog.dissDilalog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        myDialog.dissDilalog();
                        Toast.makeText(myApp, "请检查你的网络是否连接正常", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(ApplyBean applyBean) {
                        if (applyBean.getResult().equals("ok")) {
                            if (applyBean.getData() != null) {
                                MessageEvent messageEvent = new MessageEvent(myApp.APPLY_DETAIL);
                                messageEvent.setApplyList(applyBean.getData());
                                EventBus.getDefault().post(messageEvent);
                            }
                        }
                    }
                });
    }


    /**
     * 获取具体的采购单
     *
     * @param applyNo
     * @param myDialog
     */
    public void getPurchaseById(String applyNo, MyDialog myDialog) {
        System.out.println("applyNo" + applyNo);
        netAPI.getPurchaseById(applyNo, APP, myApp.getToken()).subscribeOn(Schedulers.io())//IO线程加载数据
                .observeOn(AndroidSchedulers.mainThread())//主线程显示数据
                .subscribe(new Subscriber<PurchaseBean>() {
                    @Override
                    public void onCompleted() {
                        myDialog.dissDilalog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        myDialog.dissDilalog();
                        Toast.makeText(myApp, "请检查你的网络是否连接正常", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(PurchaseBean purchase) {
                        if (purchase.getResult().equals("ok")) {
                            if (purchase.getData() != null) {
                                MessageEvent messageEvent = new MessageEvent(myApp.PURCHASE_DETAIL);
                                messageEvent.setPurchaseBean(purchase.getData());
                                EventBus.getDefault().post(messageEvent);
                            }
                        }
                    }
                });
    }


    /**
     * 获取预算单详情
     */
    public void getBudgetById(String applyNo, MyDialog myDialog) {
        System.out.println("applyNo:++++++++++" + applyNo);
        netAPI.getBudgetById(applyNo, APP, myApp.getToken()).subscribeOn(Schedulers.io())//IO线程加载数据
                .observeOn(AndroidSchedulers.mainThread())//主线程显示数据
                .subscribe(new Subscriber<BudgetBean>() {
                    @Override
                    public void onCompleted() {
                        myDialog.dissDilalog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        myDialog.dissDilalog();
                        Toast.makeText(myApp, "请检查你的网络是否连接正常", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(BudgetBean budgetBean) {
                        if (budgetBean.getResult().equals("ok")) {
                            if (budgetBean.getData() != null) {
                                MessageEvent messageEvent = new MessageEvent(myApp.BUDGET_DETAIL);
                                messageEvent.setBudgetBean(budgetBean.getData());
                                EventBus.getDefault().post(messageEvent);
                            }
                        }
                    }
                });
    }

    /**
     * 同意审批
     */
    public void agreeReview(Integer reviewNo) {
        netAPI.agreeReview(myApp.user.getId(), reviewNo, APP, myApp.getToken()).subscribeOn(Schedulers.io())//IO线程加载数据
                .observeOn(AndroidSchedulers.mainThread())//主线程显示数据
                .subscribe(new Subscriber<BackData>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("agreeReview完成");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("error:" + e.getMessage());
                        Toast.makeText(myApp, "请检查你的网络是否连接正常", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(BackData s) {
                        System.out.println("agree:" + s.getResult());
                        if (s.getResult().equals("ok")) {
                            EventBus.getDefault().post(new MessageEvent(myApp.COMMIT_APPLY, "success"));
                        }
                    }
                });

    }


    /**
     * 拒绝请求
     */
    public void refuseReview(Integer reviewNo, String reason) {
        netAPI.refuseReview(myApp.user.getId(), reviewNo, reason, APP, myApp.getToken()).subscribeOn(Schedulers.io())//IO线程加载数据
                .observeOn(AndroidSchedulers.mainThread())//主线程显示数据
                .subscribe(new Subscriber<BackData>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("refuseReview完成");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("refuseReview" + e.getMessage());
                        Toast.makeText(myApp, "请检查你的网络是否连接正常", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(BackData s) {
                        if (s.getResult().equals("ok"))
                            EventBus.getDefault().post(new MessageEvent(myApp.COMMIT_APPLY, "fail"));
                    }
                });

    }

    /**
     * 获取已完成订单
     */
    public void getReviewListHaveDoneByMe(int page, int size, SmartRefreshLayout refreshLayout, MyDialog myDialog) {
        netAPI.getReviewListHaveDoneByMe(myApp.user.getId(), page, size, APP, myApp.getToken()).subscribeOn(Schedulers.io())//IO线程加载数据
                .observeOn(AndroidSchedulers.mainThread())//主线程显示数据
                .subscribe(new Subscriber<ReviewListHaveDone>() {
                    @Override
                    public void onCompleted() {
                        myDialog.dissDilalog();
                        System.out.println("getReviewList已完成");
                        refreshLayout.finishRefresh();
                        refreshLayout.finishLoadmore();
                    }

                    @Override
                    public void onError(Throwable e) {
                        myDialog.dissDilalog();
                        Toast.makeText(myApp, "请检查你的网络是否连接正常", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(ReviewListHaveDone reviewListHaveDone) {
                        if (reviewListHaveDone.getResult().equals("ok")) {
                            MessageEvent messageEvent = new MessageEvent(myApp.HAVE_DONE);
                            messageEvent.setHaveDoneList(reviewListHaveDone.getData());
                            EventBus.getDefault().post(messageEvent);
                        }
                    }
                });
    }

    /**
     * 获取我提交的申请单
     */
    public void getApplyList(int page, int size, SmartRefreshLayout refreshLayout, MyDialog myDialog) {
        netAPI.getApplyList(myApp.user.getId(), page, size, APP, myApp.getToken()).subscribeOn(Schedulers.io())//IO线程加载数据
                .observeOn(AndroidSchedulers.mainThread())//主线程显示数据
                .subscribe(new Subscriber<ApplyList>() {
                    @Override
                    public void onCompleted() {
                        myDialog.dissDilalog();
                        System.out.println("getApplyList已完成");
                        refreshLayout.finishRefresh();
                        refreshLayout.finishLoadmore();
                    }

                    @Override
                    public void onError(Throwable e) {
                        myDialog.dissDilalog();
                        Toast.makeText(myApp, "请检查你的网络是否连接正常", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(ApplyList myApplyList) {
                        if (myApplyList.getResult().equals("ok")) {
                            MessageEvent messageEvent = new MessageEvent(myApp.MY_APPLY_LIST);
                            messageEvent.setMyApplyList(myApplyList.getData());
                            EventBus.getDefault().post(messageEvent);

                        }
                    }
                });
    }


    /**
     * 获取我的采购单列表
     */
    public void getPurchaseList(int page, int size, SmartRefreshLayout refreshLayout, MyDialog myDialog) {
        netAPI.getPurchaseList(myApp.user.getId(), page, size, APP, myApp.getToken()).subscribeOn(Schedulers.io())//IO线程加载数据
                .observeOn(AndroidSchedulers.mainThread())//主线程显示数据
                .subscribe(new Subscriber<PurchaseList>() {
                    @Override
                    public void onCompleted() {
                        myDialog.dissDilalog();
                        System.out.println("getPurchaseList已完成");
                        refreshLayout.finishRefresh();
                        refreshLayout.finishLoadmore();
                    }

                    @Override
                    public void onError(Throwable e) {
                        myDialog.dissDilalog();
                        Toast.makeText(myApp, "请检查你的网络是否连接正常", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(PurchaseList purchaseList) {
                        if (purchaseList.getResult().equals("ok")) {
                            MessageEvent messageEvent = new MessageEvent(myApp.MY_PURCHASE_LIST);
                            messageEvent.setPurchaseList(purchaseList.getData());
                            EventBus.getDefault().post(messageEvent);
                        }
                    }
                });


    }

    /**
     * 获取我提交的申请单
     */
    public void getBudgetList(int page, int size, SmartRefreshLayout refreshLayout, MyDialog myDialog) {
        netAPI.getBudgetList(myApp.user.getId(), page, size, APP, myApp.getToken()).subscribeOn(Schedulers.io())//IO线程加载数据
                .observeOn(AndroidSchedulers.mainThread())//主线程显示数据
                .subscribe(new Subscriber<BudgetList>() {
                    @Override
                    public void onCompleted() {
                        myDialog.dissDilalog();
                        System.out.println("getBudgetList已完成");
                        refreshLayout.finishRefresh();
                        refreshLayout.finishLoadmore();
                    }

                    @Override
                    public void onError(Throwable e) {
                        myDialog.dissDilalog();
                        Toast.makeText(myApp, "请检查你的网络是否连接正常", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(BudgetList budgetList) {
                        if (budgetList.getResult().equals("ok")) {
                            MessageEvent messageEvent = new MessageEvent(myApp.MY_BUDGET_LIST);
                            messageEvent.setBudgetList(budgetList.getData());
                            EventBus.getDefault().post(messageEvent);
                        }
                    }
                });
    }


    /**
     * 获取全部仓库名
     */

    public void getStorehouseList(MyDialog myDialog) {
        netAPI.getStorehouseList(APP, myApp.getToken()).subscribeOn(Schedulers.io())//IO线程加载数据
                .observeOn(AndroidSchedulers.mainThread())//主线程显示数据
                .subscribe(new Subscriber<StorehouseList>() {
                    @Override
                    public void onCompleted() {
                        myDialog.dissDilalog();
                        System.out.println("getStorehouseList已完成");
                    }

                    @Override
                    public void onError(Throwable e) {
                        myDialog.dissDilalog();
                        Toast.makeText(myApp, "请检查你的网络是否连接正常", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(StorehouseList storehouseList) {
                        if (storehouseList.getResult().equals("ok")) {
                            MessageEvent messageEvent = new MessageEvent(myApp.STOREHOUSE_LIST);
                            messageEvent.setStorehouseList(storehouseList.getData());
                            EventBus.getDefault().post(messageEvent);
                        }
                    }
                });
    }


    /**
     * 根据仓库管理员id获取对应仓库
     */
    public void getStorehouseBy(int id, MyDialog myDialog) {
        netAPI.getStorehouseBy(id, APP, myApp.getToken()).subscribeOn(Schedulers.io())//IO线程加载数据
                .observeOn(AndroidSchedulers.mainThread())//主线程显示数据
                .subscribe(new Subscriber<StorehouseList>() {
                    @Override
                    public void onCompleted() {
                        myDialog.dissDilalog();
                        System.out.println("getStorehouseBy已完成");
                    }

                    @Override
                    public void onError(Throwable e) {
                        myDialog.dissDilalog();
                        Toast.makeText(myApp, "请检查你的网络是否连接正常", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(StorehouseList storehouseList) {
                        if (storehouseList.getResult().equals("ok")) {
                            MessageEvent messageEvent = new MessageEvent(myApp.STOREHOUSE_LIST);
                            messageEvent.setStorehouseList(storehouseList.getData());
                            EventBus.getDefault().post(messageEvent);
                        }
                    }
                });
    }

    /**
     * 获取仓库库存
     *
     * @param storehouseNoStr
     * @param myDialog
     */
    public void getStockRecord(String storehouseNoStr, MyDialog myDialog) {
        netAPI.getStockRecord(storehouseNoStr, 1, 1000, APP, myApp.getToken()).subscribeOn(Schedulers.io())//IO线程加载数据
                .observeOn(AndroidSchedulers.mainThread())//主线程显示数据
                .subscribe(new Subscriber<StorehouseBean>() {
                    @Override
                    public void onCompleted() {
                        myDialog.dissDilalog();
                        System.out.println("getStockRecord已完成");
                    }

                    @Override
                    public void onError(Throwable e) {
                        myDialog.dissDilalog();
                        Toast.makeText(myApp, "请检查你的网络是否连接正常", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(StorehouseBean storehouseBean) {
                        if (storehouseBean.getResult().equals("ok")) {
                            MessageEvent messageEvent = new MessageEvent(myApp.CKKC);
                            List<StoreHouseReport> list = new ArrayList<>();
                            for (StorehouseBean.DataBean bean : storehouseBean.getData()) {
                                StoreHouseReport report = new StoreHouseReport(bean.getStorehouseName(), bean.getGoodsName(), bean.getStockNum());
                                list.add(report);
                                System.out.println("仓库数据:" + list.get(0));
                            }
                            messageEvent.setStorehouseBean(list);
                            EventBus.getDefault().post(messageEvent);
                        }
                    }
                });
    }

    public void changePass(String oldPass, String newPass, String newPass2, MyDialog myDialog) {
        System.out.println("Id:" + myApp.getUser().getId() + "\toldPWD:" + oldPass + "\tnewPwd:" + newPass + "\tnewpwd2:" + newPass2 + "\ttoken:" + myApp.getToken());
        netAPI.changePass(myApp.getUser().getId(), oldPass, newPass, newPass2, APP, myApp.getToken()).subscribeOn(Schedulers.io())//IO线程加载数据
                .observeOn(AndroidSchedulers.mainThread())//主线程显示数据
                .subscribe(new Subscriber<BackData>() {
                    @Override
                    public void onCompleted() {
                        myDialog.dissDilalog();
                        System.out.println("changePass已完成");
                    }

                    @Override
                    public void onError(Throwable e) {
                        myDialog.dissDilalog();
                        Toast.makeText(myApp, "请检查你的网络是否连接正常", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(BackData backData) {
                        System.out.println("更改密码：" + backData.getResult());
                        if (backData.getResult().equals("ok")) {
                            EventBus.getDefault().post(new MessageEvent(MyApp.CHANGE_SUCCESS, "success"));
                        } else Toast.makeText(myApp, "原始密码错误", Toast.LENGTH_SHORT).show();
                    }
                });
    }


    /**
     * 收费站查看自己的领用情况生成饼状图
     */
    public void getStockOutRecord(String beginDate, String endDate, MyDialog myDialog) {
        netAPI.getStockOutRecord(beginDate, endDate, myApp.getUser().getDeptNo()).subscribeOn(Schedulers.io())//IO线程加载数据
                .observeOn(AndroidSchedulers.mainThread())//主线程显示数据
                .subscribe(new Subscriber<StockOutRecordBean>() {
                    @Override
                    public void onCompleted() {
                        myDialog.dissDilalog();
                        System.out.println("getStockOutRecord已完成");
                    }

                    @Override
                    public void onError(Throwable e) {
                        myDialog.dissDilalog();
                        Toast.makeText(myApp, "请检查你的网络是否连接正常", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(StockOutRecordBean stockOutRecordBean) {
                        System.out.println("请求报表：" + stockOutRecordBean.getResult());
                        if (stockOutRecordBean.getResult().equals("ok")) {
                            if (stockOutRecordBean.getData() != null) {
                                List<MyGoods> list = new ArrayList<>();
                                for (StockOutRecordBean.DataBean dataBean : stockOutRecordBean.getData()) {
                                    System.out.println("商品：" + dataBean.getStockOutGoodsName());
                                    list.add(new MyGoods(dataBean.getStockOutGoodsName(), String.valueOf(dataBean.getStockOutGoodsNum())));
                                }
                                MessageEvent messageEvent = new MessageEvent(MyApp.REPORT_BT);
                                messageEvent.setGoodsList(list);
                                EventBus.getDefault().post(messageEvent);
                            }

                        }
                    }
                });

    }

    /**
     * 收费站查看领用记录生成柱状图
     */
    public void getCountStockOutRecord(String beginDate, String endDate, MyDialog myDialog) {
        System.out.println("beginDate:" + beginDate);
        netAPI.getCountStockOutRecord(beginDate, endDate, myApp.getUser().getDeptNo()).subscribeOn(Schedulers.io())//IO线程加载数据
                .observeOn(AndroidSchedulers.mainThread())//主线程显示数据
                .subscribe(new Subscriber<CountStockOutRecordBean>() {
                    @Override
                    public void onCompleted() {
                        myDialog.dissDilalog();
                        System.out.println("getStockOutRecord已完成");
                    }

                    @Override
                    public void onError(Throwable e) {
                        myDialog.dissDilalog();
                        Toast.makeText(myApp, "请检查你的网络是否连接正常", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(CountStockOutRecordBean countStockOutRecordBean) {
                        System.out.println("请求报表：" + countStockOutRecordBean.getResult());
                        if (countStockOutRecordBean.getResult().equals("ok")) {
                            if (countStockOutRecordBean.getData() != null) {
                                List<MyGoods> list = new ArrayList<>();
                                for (CountStockOutRecordBean.DataBean dataBean : countStockOutRecordBean.getData()) {
                                    System.out.println("商品：" + dataBean.getKey());
                                    list.add(new MyGoods(dataBean.getKey(), String.valueOf(dataBean.getValue())));
                                }
                                MessageEvent messageEvent = new MessageEvent(MyApp.COUNT_STOCK);
                                messageEvent.setGoodsList(list);
                                EventBus.getDefault().post(messageEvent);
                            }

                        }
                    }
                });
    }

}
