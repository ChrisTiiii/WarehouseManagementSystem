package com.example.administrator.warehousemanagementsystem.fragment.mysp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.warehousemanagementsystem.MyApp;
import com.example.administrator.warehousemanagementsystem.R;
import com.example.administrator.warehousemanagementsystem.activity.SPDetailActivity;
import com.example.administrator.warehousemanagementsystem.adapter.mysp.SPAdapter;
import com.example.administrator.warehousemanagementsystem.bean.ApplyList;
import com.example.administrator.warehousemanagementsystem.bean.BudgetList;
import com.example.administrator.warehousemanagementsystem.bean.PurchaseList;
import com.example.administrator.warehousemanagementsystem.bean.ReviewList;
import com.example.administrator.warehousemanagementsystem.bean.ReviewListHaveDone;
import com.example.administrator.warehousemanagementsystem.net.NetServerImp;
import com.example.administrator.warehousemanagementsystem.util.MessageEvent;
import com.example.administrator.warehousemanagementsystem.util.MyDialog;
import com.example.administrator.warehousemanagementsystem.util.RecyclerViewForEmpty;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * author: ZhongMing
 * DATE: 2018/11/23 0023
 * Description:审批等待 list 页面
 **/
@SuppressLint("ValidFragment")
public class SPFragment extends Fragment {

    @BindView(R.id.recycler)
    RecyclerViewForEmpty recycler;
    Unbinder unbinder;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private List<ReviewList.DataBean> preWaitList;
    private List<ReviewList.DataBean> waitList;//待审批
    private List<ReviewListHaveDone.DataBean> doneList;//已审批
    private List<ReviewListHaveDone.DataBean> preDoneList;
    private List<ApplyList.DataBean> myApplyList;//我的申领单
    private List<ApplyList.DataBean> preMyApplyList;
    private List<PurchaseList.DataBean> purchaseList;//我的采购单
    private List<PurchaseList.DataBean> prePurchaseList;

    private List<BudgetList.DataBean> budgetList;//我的预算单
    private List<BudgetList.DataBean> preBudgetList;

    private SPAdapter spAdapter;
    private int type;//type==0待审批 type==1 已审批 2我的申领 3 我的采购 4我的预算
    private NetServerImp netServerImp;
    private MyApp myApp;
    private int page_wait = 1;//待审批请求页
    private int page_done = 1;//已完成请求页
    private int page_me = 1;//我的申领单
    private int page_purchase = 1;//我的采购单
    private int page_budget = 1;//我的预算单
    private MyDialog myDialog;


    @SuppressLint("ValidFragment")
    public SPFragment(int type, MyApp myApp) {
        this.type = type;
        this.myApp = myApp;
    }

    public static SPFragment newInstance(int type, MyApp myApp) {
        Bundle args = new Bundle();
        args.putInt("type", type);
        SPFragment fragment = new SPFragment(type, myApp);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
            type = getArguments().getInt("type");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sp_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
        netServerImp = new NetServerImp(myApp);
        myDialog = new MyDialog(getContext(), 1);
        initList();//初始化全部list
        initView();//初始化界面
        loadData();//刷新和加载
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        waitList.clear();
        doneList.clear();
        myApplyList.clear();
        purchaseList.clear();
        budgetList.clear();
        page_wait = 1;
        page_done = 1;
        page_me = 1;
        page_purchase = 1;
        page_budget = 1;
        switch (type) {
            case 0:
                myDialog.showDialog();
                netServerImp.getReviewList(page_wait, 5, refreshLayout, myDialog);
                break;
            case 1:
                myDialog.showDialog();
                netServerImp.getReviewListHaveDoneByMe(page_done, 5, refreshLayout, myDialog);
                break;
            case 2:
                myDialog.showDialog();
                netServerImp.getApplyList(page_me, 5, refreshLayout, myDialog);
                break;
            case 3:
                myDialog.showDialog();
                netServerImp.getPurchaseList(page_purchase, 5, refreshLayout, myDialog);
                break;
            case 4:
                myDialog.showDialog();
                netServerImp.getBudgetList(page_budget, 5, refreshLayout, myDialog);
                break;
        }
    }

    private void loadData() {
        //刷新
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                switch (type) {
                    case 0:
                        page_wait = 1;
                        waitList.clear();
                        netServerImp.getReviewList(page_wait, 5, refreshLayout, myDialog);
                        break;
                    case 1:
                        page_done = 1;
                        doneList.clear();
                        netServerImp.getReviewListHaveDoneByMe(page_done, 5, refreshLayout, myDialog);
                        break;
                    case 2:
                        page_me = 1;
                        myApplyList.clear();
                        netServerImp.getApplyList(page_me, 5, refreshLayout, myDialog);
                        break;
                    case 3:
                        page_purchase = 1;
                        purchaseList.clear();
                        netServerImp.getPurchaseList(page_purchase, 5, refreshLayout, myDialog);
                        break;
                    case 4:
                        page_budget = 1;
                        budgetList.clear();
                        netServerImp.getBudgetList(page_budget, 5, refreshLayout, myDialog);
                        break;
                }
            }
        });
        //加载更多
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                switch (type) {
                    case 0:
                        netServerImp.getReviewList(++page_wait, 5, refreshLayout, myDialog);
                        break;
                    case 1:
                        netServerImp.getReviewListHaveDoneByMe(++page_done, 5, refreshLayout, myDialog);
                        break;
                    case 2:
                        netServerImp.getApplyList(++page_me, 5, refreshLayout, myDialog);
                        break;
                    case 3:
                        netServerImp.getPurchaseList(++page_purchase, 5, refreshLayout, myDialog);
                        break;
                    case 4:
                        netServerImp.getBudgetList(++page_budget, 5, refreshLayout, myDialog);
                        break;

                }
            }
        });

    }

    //回调更新数据
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getReviewList(MessageEvent messageEvent) {
        switch (messageEvent.getTag()) {
            case MyApp.SP_LIST://待审批
                preWaitList.clear();
                for (ReviewList.DataBean dataBean : messageEvent.getReviewLists())
                    preWaitList.add(dataBean);
                waitList.addAll(preWaitList);
                spAdapter.notifyDataSetChanged();
                break;
            case MyApp.HAVE_DONE://已审批
                preDoneList.clear();
                for (ReviewListHaveDone.DataBean dataBean : messageEvent.getHaveDoneList())
                    preDoneList.add(dataBean);
                doneList.addAll(preDoneList);
                spAdapter.notifyDataSetChanged();
                break;
            case MyApp.MY_APPLY_LIST://我的申领
                preMyApplyList.clear();
                for (ApplyList.DataBean dataBean : messageEvent.getMyApplyList())
                    preMyApplyList.add(dataBean);
                myApplyList.addAll(preMyApplyList);
                spAdapter.notifyDataSetChanged();
                break;
            case MyApp.MY_PURCHASE_LIST://我的采购
                prePurchaseList.clear();
                for (PurchaseList.DataBean dataBean : messageEvent.getPurchaseList())
                    prePurchaseList.add(dataBean);
                purchaseList.addAll(prePurchaseList);
                spAdapter.notifyDataSetChanged();
                break;
            case MyApp.MY_BUDGET_LIST://我的预算
                preBudgetList.clear();
                for (BudgetList.DataBean dataBean : messageEvent.getBudgetList())
                    preBudgetList.add(dataBean);
                budgetList.addAll(preBudgetList);
                spAdapter.notifyDataSetChanged();
                break;
        }
    }

    private void initList() {
        preWaitList = new ArrayList<>();
        waitList = new ArrayList<>();
        doneList = new ArrayList<>();
        preDoneList = new ArrayList<>();
        myApplyList = new ArrayList<>();
        preMyApplyList = new ArrayList<>();
        prePurchaseList = new ArrayList<>();
        purchaseList = new ArrayList<>();
        budgetList = new ArrayList<>();
        preBudgetList = new ArrayList<>();
    }


    private void initView() {
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        View view = LayoutInflater.from(getContext()).inflate(R.layout.no_data, null);
        recycler.setEmptyView(view);
        whichAdapter();
    }

    //初始化适配器
    void whichAdapter() {
        spAdapter = new SPAdapter(getContext(), myApp, waitList, doneList, myApplyList, purchaseList, budgetList, type);
        recycler.setAdapter(spAdapter);
        //跳转详情订单
        spAdapter.setOnItemClickListener(new SPAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getContext(), SPDetailActivity.class);
                intent.putExtra("type", type);
                switch (type) {
                    case 0:
                        intent.putExtra("bh", waitList.get(position).getObjNo());
                        intent.putExtra("detail_type", waitList.get(position).getReviewTypeNo());
                        break;
                    case 1:
                        intent.putExtra("bh", doneList.get(position).getObjNo());
                        intent.putExtra("detail_type", doneList.get(position).getReviewTypeNo());
                        break;
                    case 2:
                        intent.putExtra("bh", myApplyList.get(position).getId());
                        intent.putExtra("detail_type", 2);//我的申领单
                        break;
                    case 3:
                        intent.putExtra("bh", purchaseList.get(position).getId());
                        intent.putExtra("detail_type", 3);//我的采购单
                        break;
                    case 4:
                        intent.putExtra("bh", budgetList.get(position).getId());
                        intent.putExtra("detail_type", 4);//我的预算单
                }
                startActivity(intent);
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().unregister(this);
    }
}
