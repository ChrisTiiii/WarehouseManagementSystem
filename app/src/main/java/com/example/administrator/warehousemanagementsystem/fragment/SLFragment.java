package com.example.administrator.warehousemanagementsystem.fragment;

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
import android.widget.Button;
import android.widget.Toast;

import com.example.administrator.warehousemanagementsystem.MyApp;
import com.example.administrator.warehousemanagementsystem.R;
import com.example.administrator.warehousemanagementsystem.activity.other.MenuActivity;
import com.example.administrator.warehousemanagementsystem.adapter.SLAdapter;
import com.example.administrator.warehousemanagementsystem.bean.MyGoods;
import com.example.administrator.warehousemanagementsystem.bean.MyLeader;
import com.example.administrator.warehousemanagementsystem.bean.ViewType;
import com.example.administrator.warehousemanagementsystem.net.NetServerImp;
import com.example.administrator.warehousemanagementsystem.util.MessageEvent;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * author: ZhongMing
 * DATE: 2018/11/21 0021
 * Description: 申领物品
 **/
@SuppressLint("ValidFragment")
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

    private int product_code = 0x123;
    private MyApp myApp;
    private NetServerImp netServerImp;

    @SuppressLint("ValidFragment")
    public SLFragment(MyApp myApp) {
        this.myApp = myApp;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        netServerImp = new NetServerImp(myApp);
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
                    String code = String.valueOf(messageEvent.getMapList().get(0).get("code"));
                    String name = String.valueOf(messageEvent.getMapList().get(0).get("name"));
                    MyGoods myGoods = new MyGoods(code, name, null);
                    spAdapter.addData(uiList.size() - 2, myGoods);

                }
                break;
            case MyApp.SL_SPPERSON:
                if (messageEvent.getMapList() != null) {
                    for (Map<String, Object> map : messageEvent.getMapList()) {
                        String code = String.valueOf(map.get("code"));
                        String name = String.valueOf(map.get("name"));
                        MyLeader leader = new MyLeader(code, name);
                        spAdapter.achieveLeader(leader);
                    }
                }
                break;
            case MyApp.POST_SUCCESS:
                spAdapter.initView();
                break;
        }
    }


    @OnClick(R.id.btn_submit)
    public void onViewClicked() {
        if (achieve() != null) {
//            netServerImp.postApply(achieve());
            netServerImp.postApply(myApp.getUser().getId(), spAdapter.getNote(), getGoodsList(), getLeader());

        }
    }

    //提交审批
    public String achieve() {
        if (!spAdapter.getNote().equals("") && getGoodsList() != null && getLeader() != null) {
            //获取userNo
            Map<String, Object> map = new HashMap<>();
            map.put("userNo", myApp.getUser().getId());
            map.put("note", spAdapter.getNote());
            map.put("goodsMap", getGoodsList());
            map.put("userNoList", getLeader());
            Gson gson = new Gson();
            Toast.makeText(myApp, gson.toJson(map), Toast.LENGTH_SHORT).show();
            return gson.toJson(map);
        } else {
            Toast.makeText(myApp, "您有未填写项", Toast.LENGTH_SHORT).show();
            return null;
        }
    }

    public String getGoodsList() {
        //获得goodsList:物品编号+数量
        goodsList = spAdapter.getGoodsList();
        if (goodsList != null) {
            JSONObject jsonObject = new JSONObject();
            for (MyGoods temp : goodsList) {
                if (temp.getNum() != null) {
                    try {
                        jsonObject.put(temp.getCode(), temp.getNum().equals("") ? 0 : Integer.parseInt(temp.getNum()));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
            return jsonObject.toString().equals("") ? null : jsonObject.toString();

        }
        return null;
    }

    public String getLeader() {
        if (spAdapter.getLeaderList() != null) {
            JSONArray jsonArray = new JSONArray();
            for (MyLeader temp : spAdapter.getLeaderList()) {
                if (temp.getCode() != null) {
                    jsonArray.put(Integer.parseInt(temp.getCode()));
                }
            }
            return jsonArray.toString();
        }
        return null;
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