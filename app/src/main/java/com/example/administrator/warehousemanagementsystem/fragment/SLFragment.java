package com.example.administrator.warehousemanagementsystem.fragment;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.administrator.warehousemanagementsystem.MyApp;
import com.example.administrator.warehousemanagementsystem.R;
import com.example.administrator.warehousemanagementsystem.activity.other.MenuActivity;
import com.example.administrator.warehousemanagementsystem.adapter.SLAdapter;
import com.example.administrator.warehousemanagementsystem.bean.MyGoods;
import com.example.administrator.warehousemanagementsystem.bean.MyLeader;
import com.example.administrator.warehousemanagementsystem.bean.ViewType;
import com.example.administrator.warehousemanagementsystem.net.NetServerImp;
import com.example.administrator.warehousemanagementsystem.util.MessageEvent;
import com.example.administrator.warehousemanagementsystem.util.MyDialog;
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
    private AlertDialog.Builder builder;
    private MyDialog myDialog;

    @SuppressLint("ValidFragment")
    public SLFragment(MyApp myApp) {
        this.myApp = myApp;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        netServerImp = new NetServerImp(myApp);
        myDialog = new MyDialog(getContext(), 2);
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
                        spAdapter.deleteData(position);
                        Toast.makeText(getContext(), "删除第" + (position) + "项成功", Toast.LENGTH_SHORT).show();
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
                    String goodsType = String.valueOf(messageEvent.getMapList().get(0).get("goodsType"));
                    String code = String.valueOf(messageEvent.getMapList().get(0).get("code"));
                    String name = String.valueOf(messageEvent.getMapList().get(0).get("name"));
                    String num = String.valueOf(messageEvent.getMapList().get(0).get("num"));
                    MyGoods myGoods = new MyGoods(goodsType, code, name, num);
                    spAdapter.addData(uiList.size() - 2, myGoods);
                    if (spAdapter.isReGoods()) {
                        new MaterialDialog.Builder(getContext())
                                .content("您添加了重复商品，请马上删除")
                                .positiveText("我马上删")
                                .show().setCanceledOnTouchOutside(false);
                    }
                    if (spAdapter.isReGoodsType())
                        new MaterialDialog.Builder(getContext())
                                .content("由于您选择了不同种类的物资，需要添加这些物资对应的不同审批人")
                                .positiveText("我已了解")
                                .show().setCanceledOnTouchOutside(false);
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
            builder = new AlertDialog.Builder(getContext());
            builder.setTitle("用户须知");
            builder.setMessage("若您有重复申请物品则按照最新物品明细进行提交\n");
            if (!dialogString().equals("未给您开通此项")) {
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        builder = new AlertDialog.Builder(getContext());
                        builder.setTitle("确认提交吗?");
                        builder.setMessage(dialogString());
                        builder.setPositiveButton("确定提交", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                myDialog.showDialog();
                                switch (spAdapter.getType()) {
                                    case "申领单":
                                        netServerImp.postApply(myApp.getUser().getId(), spAdapter.getExplain(), getGoodsList(0), getLeader(0), myDialog);
                                        break;
                                    case "采购单":
                                        netServerImp.postPurchase(myApp.getUser().getId(), spAdapter.getExplain(), getGoodsList(0), getLeader(0), spAdapter.getCaigou(), myDialog);
                                        break;
                                    case "预算单":
                                        netServerImp.postBudget(myApp.getUser().getId(), spAdapter.getExplain(), getGoodsList(0), getLeader(0), myDialog);
                                        break;
                                }
                            }
                        });
                        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();
                    }
                });
                builder.show();
            } else builder.setNegativeButton("未给您开通此项", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            }).show();
        }
    }

    //提交审批
    public String achieve() {
        if ((!spAdapter.getExplain().equals("")) && getGoodsList(0) != null && getLeader(0) != null) {
            //获取userNo
            Map<String, Object> map = new HashMap<>();
            map.put("userNo", myApp.getUser().getId());
            map.put("note", spAdapter.getExplain());
            map.put("goodsMap", getGoodsList(0));
            map.put("userNoList", getLeader(0));
            Gson gson = new Gson();
            return gson.toJson(map);
        } else {
            Toast.makeText(myApp, "您有未填写项", Toast.LENGTH_SHORT).show();
            return null;
        }
    }

    //给用户提供提示
    public String dialogString() {
        switch (spAdapter.getType()) {
            case "申领单"://全部可以
//                if (myApp.getRoot() == 100 || myApp.getRoot() == 120 || myApp.getRoot() == 130 || myApp.getRoot() == 140)
                return "确定要提交如下申领的物品吗?\n" + "申请类型：" + spAdapter.getType() + "\n商品：" + getGoodsList(1) + "\n审批人：" + getLeader(1) + "\n备注：" + spAdapter.getExplain();
//                else return "未给您开通此项";
            case "采购单"://只有120仓库管理员可以
                if (myApp.getRoot() == 120 || myApp.getRoot() == 130 || myApp.getRoot() == 140)
                    return "确定要提交如下采购的物品吗?\n" + "申请类型：" + spAdapter.getType() + "\n商品：" + getGoodsList(1) + "\n审批人：" + getLeader(1) + "\n备注：" + spAdapter.getExplain() + "\n供应商：" + spAdapter.getCaigou();
                else return "未给您开通此项";
            case "预算单"://只有100收费站管理员可以申请
                if (myApp.getRoot() == 100 || myApp.getRoot() == 130 || myApp.getRoot() == 140)
                    return "确定要提交如下预算的物品吗?\n" + "申请类型：" + spAdapter.getType() + "\n商品：" + getGoodsList(1) + "\n审批人：" + getLeader(1) + "\n备注：" + spAdapter.getExplain();
                else return "未给您开通此项";
        }
        return "出错";
    }

    /**
     * 获取物品List
     *
     * @param type 判断是显示name 还是 id
     * @return
     */
    public String getGoodsList(int type) {
        //获得goodsList:物品编号/名称+数量
        goodsList = spAdapter.getGoodsList();
        if (goodsList != null) {
            JSONObject jsonObject = new JSONObject();
            for (MyGoods temp : goodsList) {
                if (temp.getNum() != null) {
                    try {
                        jsonObject.put(type != 0 ? temp.getName() : temp.getCode(), temp.getNum().equals("") ? 0 : Integer.parseInt(temp.getNum()));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
            return jsonObject.toString().equals("") ? null : jsonObject.toString();
        }
        return "{}";
    }

    /**
     * 获取领导
     *
     * @param type
     * @return
     */
    public String getLeader(int type) {
        if (spAdapter.getLeaderList() != null) {
            JSONArray jsonArray = new JSONArray();
            for (MyLeader temp : spAdapter.getLeaderList()) {
                if (temp.getCode() != null) {
                    if (type != 0)
                        jsonArray.put(temp.getName());
                    else
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