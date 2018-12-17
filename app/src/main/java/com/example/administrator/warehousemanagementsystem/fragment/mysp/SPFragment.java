package com.example.administrator.warehousemanagementsystem.fragment.mysp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.warehousemanagementsystem.MyApp;
import com.example.administrator.warehousemanagementsystem.R;
import com.example.administrator.warehousemanagementsystem.activity.SPDetailActivity;
import com.example.administrator.warehousemanagementsystem.adapter.mysp.SPAdapter;
import com.example.administrator.warehousemanagementsystem.bean.ReviewList;
import com.example.administrator.warehousemanagementsystem.bean.SPDetailBean;
import com.example.administrator.warehousemanagementsystem.net.NetServerImp;
import com.example.administrator.warehousemanagementsystem.util.MessageEvent;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
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
    RecyclerView recycler;
    Unbinder unbinder;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private List<ReviewList.DataBean> preWaitList;
    private List<ReviewList.DataBean> waitList;
    private List<SPDetailBean> doneList;
    private SPAdapter spAdapter;
    private int type;//type==0待审批 type==1 已审批
    private NetServerImp netServerImp;
    private MyApp myApp;
    private int page = 1;

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
        initData();
        initView();
        loadData();//刷新和加载
        return view;
    }

    private void loadData() {
        //刷新
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
                waitList.clear();
                doneList.clear();
                netServerImp.getReviewList(1, 5);
            }
        });
        //加载更多
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                netServerImp.getReviewList(page++, 5);
                doneList.add(SPDetailBean.doneSp());
            }
        });

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getReviewList(MessageEvent messageEvent) {
        switch (messageEvent.getTag()) {
            case MyApp.SP_LIST:
                preWaitList.clear();
                for (ReviewList.DataBean dataBean : messageEvent.getReviewLists())
                    preWaitList.add(dataBean);
                waitList.addAll(0, preWaitList);
                spAdapter.notifyDataSetChanged();
                refreshLayout.finishRefresh();
                refreshLayout.finishLoadmore();
                break;
        }
    }

    private void initData() {
        preWaitList = new ArrayList<>();
        waitList = new ArrayList<>();
        doneList = new ArrayList<>();
        doneList.add(SPDetailBean.doneSp());
        netServerImp.getReviewList(1, 5);
    }


    private void initView() {
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        whichAdapter();
    }

    //初始化适配器
    void whichAdapter() {
        spAdapter = new SPAdapter(getContext(), waitList, doneList, type);
        recycler.setAdapter(spAdapter);
        spAdapter.setOnItemClickListener(new SPAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getContext(), SPDetailActivity.class);
                intent.putExtra("type", type);
                if (type == 0)
                    intent.putExtra("bh", waitList.get(position).getObjNo());
                else if (type == 1)
                    intent.putExtra("bh", doneList.get(position));
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
