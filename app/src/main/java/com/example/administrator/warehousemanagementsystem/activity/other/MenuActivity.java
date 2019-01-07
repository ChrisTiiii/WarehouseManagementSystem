package com.example.administrator.warehousemanagementsystem.activity.other;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.warehousemanagementsystem.MyApp;
import com.example.administrator.warehousemanagementsystem.R;
import com.example.administrator.warehousemanagementsystem.adapter.ProductListAdapter;
import com.example.administrator.warehousemanagementsystem.bean.MenuListBean;
import com.example.administrator.warehousemanagementsystem.net.NetServerImp;
import com.example.administrator.warehousemanagementsystem.util.MessageEvent;
import com.example.administrator.warehousemanagementsystem.util.MyDialog;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author: ZhongMing
 * DATE: 2018/11/23 0023
 * Description:该页面用来选择Menu二级菜单
 **/
public class MenuActivity extends AppCompatActivity {
    @BindView(R.id.main_recyclerviewAll)
    RecyclerView recyclerviewAll;
    @BindView(R.id.main_recyclerviewDetail)
    RecyclerView recyclerviewDetail;
    @BindView(R.id.main_tv)
    TextView tv;
//    @BindView(R.id.refreshLayout)
//    SmartRefreshLayout refreshLayout;

    private List<MenuListBean> allList;
    private int perPosition = 0;//AllList 位置
    private ProductListAdapter adapterAll;
    private List<MenuListBean> detailList;
    private List<MenuListBean> preDetailList;
    private List<Map<String, Object>> checkList;
    private ProductListAdapter adapterDetail;
    private static int menu_type = -1;//判断是 0添加审批人还是 1||3添加物资
    private NetServerImp netServerImp;
    MyApp myApp;
    private static int pageNo = 1;// 设置pageNo的初始化值为1，即默认获取的是第一页的数据。
    private static int pageCount;//总页数，从服务端获取过来
    private MyDialog myDialog;
    private Map<String, Object> map;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_list);
        ButterKnife.bind(this);
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
        myApp = (MyApp) getApplication();
        netServerImp = new NetServerImp(myApp);
        myDialog = new MyDialog(MenuActivity.this, 1);
        myDialog.showDialog();
        Intent intent = getIntent();
        menu_type = intent.getExtras().getInt("type");
        map = new HashMap<>();
        initView(); //初始化控件
    }

    private void initView() {
        LinearLayoutManager layout = new LinearLayoutManager(this);
        LinearLayoutManager layout1 = new LinearLayoutManager(this);
        recyclerviewAll.setLayoutManager(layout);
        recyclerviewDetail.setLayoutManager(layout1);
        initList();//初始化list
        initAllList();//加载左侧列表数据
        adapterAll = new ProductListAdapter(MenuActivity.this, allList);
        recyclerviewAll.setAdapter(adapterAll);
        adapterDetail = new ProductListAdapter(MenuActivity.this, detailList);
        recyclerviewDetail.setAdapter(adapterDetail);
        adapterAll.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (perPosition != position) {
                    allList.get(perPosition).setSelect(false);
                    allList.get(position).setSelect(true);
                    adapterAll.notifyDataSetChanged();
                    myDialog.showDialog();
                    //设置右列表数据
                    loadDetailList(position);
                    checkList.clear();
                    if (menu_type == 3)
                        tv.setText("确定");
                    else
                        tv.setText("确定(0/1)");
                    adapterDetail.notifyDataSetChanged();
                    perPosition = position;
                }
            }
        });

        adapterDetail.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                checkType(position);
            }
        });
//        loadMore();
        sureResult();
    }

    private void initList() {
        allList = new ArrayList<>();
        detailList = new ArrayList<>();
        preDetailList = new ArrayList<>();
        checkList = new ArrayList<>();
    }


    /**
     * 数据回传处理
     *
     * @param messageEvent
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getGoodsData(MessageEvent messageEvent) {
        switch (messageEvent.getTag()) {
            case MyApp.MENU_TYPE:
                int i = 0;
                allList.clear();
                for (Map<String, Object> map : messageEvent.getMapList()) {
                    if (i == 0) {
                        allList.add(new MenuListBean(map, true));
                    } else
                        allList.add(new MenuListBean(map, false));
                    i++;
                }
                adapterAll.notifyDataSetChanged();
                loadDetailList(0);
                break;
            case MyApp.MENU_DETAIL:
                preDetailList.clear();
                for (Map<String, Object> map : messageEvent.getMapList())
                    preDetailList.add(new MenuListBean(map, false));
                detailList.addAll(preDetailList);
                if (messageEvent.getMapList().get(0).get("count") != null) {
                    pageCount = (int) messageEvent.getMapList().get(0).get("count");
                }
                adapterDetail.notifyDataSetChanged();
//                refreshLayout.finishRefresh();
//                refreshLayout.finishLoadmore();
                break;
        }
    }

    /**
     * 加载更多数据
     */
    private void loadMore() {
//        //刷新
//        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
//            @Override
//            public void onRefresh(RefreshLayout refreshlayout) {
//                detailList.clear();
//                if (menu_type != 0)
//                    loadDetailList(perPosition);
//                else loadDetailList(perPosition);
//            }
//        });
//
//        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
//            @Override
//            public void onLoadmore(RefreshLayout refreshlayout) {
//                if (menu_type != 0)
//                    loadDetailList(perPosition);
//                else loadDetailList(perPosition);
//            }
//        });
    }

    /**
     * 设置右列表数据
     */
    private void loadDetailList(int position) {
        rightData((Integer) allList.get(position).getMap().get("type"));
    }

    /**
     * 设置左列表数据
     */
    private void initAllList() {
        if (menu_type != 0)
            netServerImp.getGoodsType();
        else netServerImp.getSPPersonType();
    }

    //加载右侧数据
    void rightData(int menuTypeNo) {
        detailList.clear();
        if (menu_type != 0)
            netServerImp.getGoodsDetail(menuTypeNo, myDialog);
        else netServerImp.getSPPerson(menuTypeNo, myDialog);
    }

    //确定类型
    public void checkType(int position) {
        if (menu_type == 3) {
            if (detailList.get(position).isSelect()) {
                checkList.remove(detailList.get(position).getMap().get("name"));
            } else {
                map.put("goodsType", detailList.get(position).getMap().get("goodsType"));
                map.put("name", detailList.get(position).getMap().get("name"));
                map.put("code", detailList.get(position).getMap().get("code"));
                checkList.add(map);
            }
            tv.setText("确定" + "(" + checkList.size() + ")");
            detailList.get(position).setSelect(!detailList.get(position).isSelect());
            adapterDetail.notifyDataSetChanged();
        } else {
            if (detailList.get(position).isSelect()) {
                checkList.clear();
            } else {
                if (checkList.size() == 1) {
                    Toast.makeText(MenuActivity.this, "最多只能选择一个", Toast.LENGTH_SHORT).show();
                    return;
                }
                map.put("goodsType", detailList.get(position).getMap().get("goodsType"));
                map.put("name", detailList.get(position).getMap().get("name"));
                map.put("code", detailList.get(position).getMap().get("code"));
                checkList.add(map);
            }
            tv.setText("确定" + "(" + checkList.size() + "/1)");
            detailList.get(position).setSelect(!detailList.get(position).isSelect());
            adapterDetail.notifyDataSetChanged();
        }


    }

    //返回结果数据
    private void sureResult() {
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkList.size() > 0) {
                    switch (menu_type) {
                        case 0:
                            EventBus.getDefault().post(new MessageEvent(MyApp.SL_SPPERSON, checkList));
                            finish();
                            break;
                        case 1:
                            MaterialDialog.Builder builder = new MaterialDialog.Builder(MenuActivity.this);
                            builder.title("请输入数量")
                                    .inputType(InputType.TYPE_CLASS_NUMBER)
                                    .input(checkList.get(0).get("name") + "的数量", null, new MaterialDialog.InputCallback() {
                                        @Override
                                        public void onInput(MaterialDialog dialog, CharSequence input) {
                                            map.put("num", input);
                                            checkList.add(map);
                                            EventBus.getDefault().post(new MessageEvent(MyApp.SL_CHOOSE_PRODUCT, checkList));
                                            System.out.println(input.equals(""));
                                            if (!String.valueOf(input).equals(""))
                                                finish();
                                            else {
                                                checkList.clear();
                                                Toast.makeText(myApp, "数量不能为空", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    })
                                    .positiveText("确定")
                                    .negativeText("取消")
                                    .show();
                            break;
                        case 3:
                            finish();
                            break;
                        default:
                            finish();
                    }
                } else
                    finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        pageNo = 1;
        if (EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().unregister(this);
    }
}
