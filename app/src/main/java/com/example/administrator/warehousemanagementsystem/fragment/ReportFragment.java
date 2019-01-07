package com.example.administrator.warehousemanagementsystem.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.bigkoo.pickerview.TimePickerView;
import com.example.administrator.warehousemanagementsystem.MyApp;
import com.example.administrator.warehousemanagementsystem.R;
import com.example.administrator.warehousemanagementsystem.activity.ApplyReport;
import com.example.administrator.warehousemanagementsystem.activity.ReportActivity;
import com.example.administrator.warehousemanagementsystem.activity.other.MenuActivity;
import com.example.administrator.warehousemanagementsystem.bean.DeptListBean;
import com.example.administrator.warehousemanagementsystem.bean.MyGoods;
import com.example.administrator.warehousemanagementsystem.bean.StorehouseList;
import com.example.administrator.warehousemanagementsystem.net.NetServerImp;
import com.example.administrator.warehousemanagementsystem.util.MessageEvent;
import com.example.administrator.warehousemanagementsystem.util.MyDialog;
import com.example.administrator.warehousemanagementsystem.util.TimeUtil;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * author: ZhongMing
 * DATE: 2018/11/20 0020
 * Description:报表fragmnet
 **/
@SuppressLint("ValidFragment")
public class ReportFragment extends Fragment {

    Unbinder unbinder;

    @BindView(R.id.pic_chart)
    PieChart picChart;
    @BindView(R.id.clear)
    Button clear;
    @BindView(R.id.btn_bb)
    Button btnBb;
    @BindView(R.id.btn_begin_time)
    Button btnBeginTime;
    @BindView(R.id.tv_begin)
    TextView tvBegin;
    @BindView(R.id.btn_end_time)
    Button btnEndTime;
    @BindView(R.id.tv_end)
    TextView tvEnd;
    @BindView(R.id.btn_ly)
    Button btnLy;
    @BindView(R.id.btn_apply)
    Button btnApply;


    private List<PieEntry> things;//饼状图个体
    private ArrayList<Integer> colors;//饼状图色体
    private List<Map<String, Object>> listCK;
    private List<String> namelist;
    NetServerImp netServerImp;
    MyApp myApp;
    MyDialog myDialog;
    private String begin;
    private String end;

    @SuppressLint("ValidFragment")
    public ReportFragment(MyApp myApp) {
        this.myApp = myApp;
    }

    public static ReportFragment newInstance(MyApp myApp) {
        Bundle args = new Bundle();
        ReportFragment fragment = new ReportFragment(myApp);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.report_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
        myDialog = new MyDialog(getContext(), 1);
        netServerImp = new NetServerImp(myApp);
        root();
        initList();
        return view;
    }

    //根据权限展现库存/领用
    private void root() {
        if (myApp.getRoot() == 100 || myApp.getRoot() == 110) {
            btnApply.setVisibility(View.VISIBLE);
//            btnLy.setVisibility(View.VISIBLE);
            btnBb.setVisibility(View.GONE);
        } else {
//            btnLy.setVisibility(View.VISIBLE);
            btnBb.setVisibility(View.VISIBLE);
            btnApply.setVisibility(View.VISIBLE);
        }
    }

    private void initList() {
        things = new ArrayList<>();
        listCK = new ArrayList<>();
        namelist = new ArrayList<>();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        if (EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().unregister(this);
    }

    public void initColor() {
        colors = new ArrayList();
        for (int i = 0; i < things.size(); i++) {
            colors.add(randomColor());
        }
    }

    //返回随机颜色
    public int randomColor() {
        Random random = new Random();
        int ranColor = 0xff000000 | random.nextInt(0x00ffffff);
        return ranColor;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateData(MessageEvent messageEvent) {
        switch (messageEvent.getTag()) {
            case MyApp.REPORT_BT:
                if (messageEvent.getGoodsList() != null) {
                    Description description = new Description();
                    description.setText("近期领用的情况");
                    picChart.setDescription(description);
                    things.clear();
                    for (MyGoods myGoods : messageEvent.getGoodsList()) {
                        System.out.println("商品：" + myGoods.getName() + "数量：" + myGoods.getNum());
                        things.add(new PieEntry(Integer.parseInt(myGoods.getNum()), myGoods.getName()));
                    }
                    PieDataSet dataSet = new PieDataSet(things, "Label");
                    initColor();
                    dataSet.setColors(colors);
                    PieData pieData = new PieData(dataSet);
                    pieData.setDrawValues(true);
                    picChart.setData(pieData);
                    picChart.invalidate();
                }
                break;
            case MyApp.STOREHOUSE_LIST:
                if (messageEvent.getStorehouseList() != null) {
                    listCK.clear();
                    namelist.clear();
                    for (StorehouseList.DataBean bean : messageEvent.getStorehouseList()) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("code", bean.getId());
                        map.put("name", bean.getStorehouseName());
                        listCK.add(map);
                        namelist.add(String.valueOf(map.get("name")));
                    }
                    new MaterialDialog.Builder(getContext())
                            .title("宁通仓库列表")
                            .positiveText("确认")
                            .items(namelist)
                            .itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice() {
                                @Override
                                public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                    if (which != -1) {
                                        Intent intent = new Intent(getContext(), ReportActivity.class);
                                        intent.putExtra("which", String.valueOf(listCK.get(which).get("code")));
                                        startActivity(intent);
                                        return true;
                                    }
                                    return false;
                                }
                            })
                            .show();
                }
                break;
            case MyApp.DEPT_LIST:
                if (messageEvent.getDeptListBeanList() != null) {
                    listCK.clear();
                    namelist.clear();
                    for (DeptListBean.DataBean bean : messageEvent.getDeptListBeanList()) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("code", bean.getId());
                        map.put("name", bean.getDeptName());
                        listCK.add(map);
                        namelist.add(String.valueOf(map.get("name")));
                    }
                }
                new MaterialDialog.Builder(getContext())
                        .title("宁通收费站列表")
                        .positiveText("确认")
                        .items(namelist)
                        .itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice() {
                            @Override
                            public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                if (which != -1) {
                                    Intent intent = new Intent(getContext(), ApplyReport.class);
                                    intent.putExtra("begin", begin);
                                    intent.putExtra("end", end);
                                    intent.putExtra("which", String.valueOf(listCK.get(which).get("code")));
                                    startActivity(intent);
                                    return true;
                                }
                                return false;
                            }
                        })
                        .show();
                break;
        }
    }


    public void setTime(int type) {
        //时间选择器
        TimePickerView pvTime = new TimePickerView.Builder(getContext(), new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                if (type == 1) {
                    begin = sdf.format(date);
                    tvBegin.setText(begin);
                } else if (type == 2) {
                    end = sdf.format(date);
                    tvEnd.setText(end);
                }
            }
        }).setType(new boolean[]{true, true, true, false, false, false}) //年月日时分秒 的显示与否，不设置则默认全部显示
                .setLabel("年", "月", "日", "", "", "")//默认设置为年月日时分秒
                .build();
        pvTime.setDate(Calendar.getInstance());//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
        pvTime.show();
    }

    @OnClick({R.id.btn_begin_time, R.id.btn_end_time, R.id.btn_ly, R.id.btn_bb, R.id.clear, R.id.btn_apply})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_begin_time:
                setTime(1);
                break;
            case R.id.btn_end_time:
                setTime(2);
                break;
            case R.id.btn_ly:
                try {
                    if (begin != null && end != null) {
                        myDialog.showDialog();
                        netServerImp.getStockOutRecord(TimeUtil.dateToStamp(begin), TimeUtil.dateToStamp(end), myDialog);
                    } else Toast.makeText(myApp, "请选择日期", Toast.LENGTH_SHORT).show();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn_bb:
                myDialog.showDialog();
                if (myApp.getRoot() == 100 || myApp.getRoot() == 110) {
                    netServerImp.getStorehouseBy(myApp.user.getId(), myDialog);
                } else
                    netServerImp.getStorehouseList(myDialog);
                break;
            case R.id.clear:
                things.clear();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        picChart.clear();
                        picChart.invalidate();
                    }
                });
                break;
            case R.id.btn_apply:
                if (begin != null && end != null) {
                    Intent intent = new Intent(getContext(), ApplyReport.class);
                    intent.putExtra("begin", begin);
                    intent.putExtra("end", end);
                    if (myApp.getRoot() == 100 || myApp.getRoot() == 110) {
                        startActivity(intent);
                    } else {
                        myDialog.showDialog();
                        netServerImp.getDeptListBy(myDialog);
                    }
                } else Toast.makeText(myApp, "请选择日期", Toast.LENGTH_SHORT).show();
                break;
//            case R.id.btn_intent:
//                Intent intent = new Intent(getContext(), MenuActivity.class);
//                intent.putExtra("type", 3);
//                startActivity(intent);
//                break;

        }
    }
}
