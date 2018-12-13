package com.example.administrator.warehousemanagementsystem.fragment;

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
import android.widget.Button;
import android.widget.Toast;

import com.example.administrator.warehousemanagementsystem.MyApp;
import com.example.administrator.warehousemanagementsystem.R;
import com.example.administrator.warehousemanagementsystem.activity.other.MenuActivity;
import com.example.administrator.warehousemanagementsystem.adapter.SLAdapter;
import com.example.administrator.warehousemanagementsystem.bean.MyGoods;
import com.example.administrator.warehousemanagementsystem.bean.ViewType;
import com.example.administrator.warehousemanagementsystem.util.MessageEvent;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * author: ZhongMing
 * DATE: 2018/11/21 0021
 * Description: 申领物品
 **/
public class SLFragment extends Fragment {


    private View rootView;

    @BindView(R.id.shenpi)
    RecyclerView recyclerView;
    Unbinder unbinder;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    private SLAdapter spAdapter;
    private List<ViewType> uiList;
    private List<MyGoods> goodsList;
    //    private List<String> nameList;//物品名称
//    private List<String> numList;//物品数量
    private String use;//物品用途
    private String explain;//物品详情
    private int product_code = 0x123;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (null != rootView) {
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (!EventBus.getDefault().isRegistered(this))
                EventBus.getDefault().register(this);
            unbinder = ButterKnife.bind(this, rootView);
            if (null != parent) {
                parent.removeView(rootView);
            }
        } else {
            rootView = inflater.inflate(R.layout.sl_list, null);
            if (!EventBus.getDefault().isRegistered(this))
                EventBus.getDefault().register(this);
            unbinder = ButterKnife.bind(this, rootView);

            initData();
            initView();
        }
        return rootView;
    }


    //初始化数据
    private void initData() {
        uiList = new ArrayList<>();
        initList();
    }

    //初始化UI
    private void initList() {
        uiList.add(new ViewType(ViewType.SL_TYPE_HEAD));
        uiList.add(new ViewType(ViewType.SL_TYPE_ADD));
        uiList.add(new ViewType(ViewType.SL_TYPE_EXPLAIN));
        uiList.add(new ViewType(ViewType.SL_TYPE_LEADER));
    }

    private void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        spAdapter = new SLAdapter(getContext(), uiList);
        spAdapter.setHasStableIds(true);
        recyclerView.setAdapter(spAdapter);
        spAdapter.setOnItemClickListener(new SLAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, final int position) {
                switch (view.getId()) {
                    case R.id.btn_del:
                        System.out.println("delete:" + position);
                        spAdapter.deleteData(position - 1);
                        Toast.makeText(getContext(), "删除" + (position - 1) + "成功", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        spAdapter.setOnAddClickListener(new SLAdapter.OnAddClickListener() {
            @Override
            public void onAddClick(View view, int position) {
                switch (view.getId()) {
                    case R.id.btn_add:
                        Intent intent = new Intent(getContext(), MenuActivity.class);
                        intent.putExtra("type", 1);
                        startActivityForResult(intent, product_code);
                        break;
                }

            }
        });

        spAdapter.setOnAddLeaderListener(new SLAdapter.OnAddLeaderListener() {
            @Override
            public void onAddLeader(View view, int position) {
                Intent intent = new Intent(getContext(), MenuActivity.class);
                intent.putExtra("type", 0);//0为选择审批人
                startActivity(intent);
            }
        });


    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateData(MessageEvent messageEvent) {
        switch (messageEvent.getTag()) {
            case MyApp.SL_CHOOSE_PRODUCT:
                if (messageEvent.getMapList() != null) {
                    MyGoods myGoods = new MyGoods((String) messageEvent.getMapList().get(0).get("code"), (String) messageEvent.getMapList().get(0).get("name"), null);
//                    spAdapter.addData(uiList.size() - 2, (String) messageEvent.getMapList().get(0).get("name"));
                    spAdapter.addData(uiList.size() - 2, myGoods);

                }
                break;
//            case MyApp.SL_SPPERSON:
//                if (messageEvent.getMapList() != null) {
//                    for (String name : messageEvent.getNameList()) {
//                        spAdapter.achieveLeader(name);
//                    }
//                }
//                break;
        }
    }


    @OnClick(R.id.btn_submit)
    public void onViewClicked() {
        goodsList = spAdapter.getGoodsList();
        Gson gson = new Gson();
        List<MyGoods> list = new ArrayList<>();
        for (MyGoods temp : goodsList) {
            list.add(new MyGoods(temp.getCode(), temp.getNum()));
        }
        System.out.println(gson.toJson(list));
//        achiveData();
    }


//    //提交审批
//    void achiveData() {
//        String temp = "";
//        String leaders = "";
//        if (nameList != null && numList != null && spAdapter.leaderList != null) {
//            for (int i = 0; i < spAdapter.leaderList.size(); i++) {
//                leaders += "步骤" + i + ":" + spAdapter.leaderList.get(i);
//            }
//            for (int i = 0; i < nameList.size(); i++) {
//                temp += "position:" + i + "\t name：" + nameList.get(i) + " \tnum：" + numList.get(i) + "\n";
//            }
//            Toast.makeText(getContext(), "title: " + use + "\n" + temp + "\n" + explain + "\n" + leaders, Toast.LENGTH_SHORT).show();
//        } else Toast.makeText(getContext(), "提交内容不能为空", Toast.LENGTH_SHORT).show();
//    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void acceptEditList(MessageEvent messageEvent) {
        switch (messageEvent.getTag()) {
            case MyApp.SL_DETAIL:
//                goodsList = messageEvent.getGoodsList();
//                nameList = messageEvent.getNameList();
//                numList = messageEvent.getNumlist();
                break;
            case MyApp.SL_USE:
                use = messageEvent.getMessage();
                System.out.println(messageEvent.getMessage());
                break;
            case MyApp.SL_EXPLAIN:
                explain = messageEvent.getMessage();
                break;
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().unregister(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}