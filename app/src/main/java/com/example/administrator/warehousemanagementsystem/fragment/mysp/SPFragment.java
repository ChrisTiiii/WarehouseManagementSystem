package com.example.administrator.warehousemanagementsystem.fragment.mysp;

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

import com.example.administrator.warehousemanagementsystem.R;
import com.example.administrator.warehousemanagementsystem.activity.SPDetailActivity;
import com.example.administrator.warehousemanagementsystem.adapter.mysp.SPAdapter;
import com.example.administrator.warehousemanagementsystem.bean.SPDetailBean;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.io.Serializable;
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
public class SPFragment extends Fragment {

    @BindView(R.id.recycler)
    RecyclerView recycler;
    Unbinder unbinder;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private List<SPDetailBean> waitList;
    private List<SPDetailBean> doneList;
    private SPAdapter spAdapter;
    private int type;//type==0待审批 type==1 已审批

    public static SPFragment newInstance(int type) {
        Bundle args = new Bundle();
        args.putInt("type", type);
        SPFragment fragment = new SPFragment();
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
        initData();
        initView();
        loadData();
        return view;
    }

    private void loadData() {
        //刷新
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                waitList.clear();
                doneList.clear();
                spAdapter.notifyDataSetChanged();
                refreshlayout.finishRefresh();
            }
        });
        //加载更多
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                for (int i = 0; i < 3; i++) {
                    waitList.add(SPDetailBean.waitSp());
                    doneList.add(SPDetailBean.doneSp());
                }
                spAdapter.notifyDataSetChanged();
                refreshlayout.finishLoadmore();
            }
        });

    }

    private void initData() {
        waitList = new ArrayList<>();
        doneList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            waitList.add(SPDetailBean.waitSp());
            doneList.add(SPDetailBean.doneSp());
        }
        doneList.add(SPDetailBean.removeSp());
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
                    intent.putExtra("list", waitList.get(position));
                else if (type == 1)
                    intent.putExtra("list", doneList.get(position));
                startActivity(intent);
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
