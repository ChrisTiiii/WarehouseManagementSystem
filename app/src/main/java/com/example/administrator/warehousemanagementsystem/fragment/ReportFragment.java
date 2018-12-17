package com.example.administrator.warehousemanagementsystem.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.administrator.warehousemanagementsystem.MyApp;
import com.example.administrator.warehousemanagementsystem.R;
import com.example.administrator.warehousemanagementsystem.activity.other.MenuActivity;
import com.example.administrator.warehousemanagementsystem.util.MessageEvent;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
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
public class ReportFragment extends Fragment {

    Unbinder unbinder;
    @BindView(R.id.btn_intent)
    Button btnIntent;
    @BindView(R.id.pic_chart)
    PieChart picChart;
    @BindView(R.id.clear)
    Button clear;

    private List<PieEntry> things;//饼状图个体
    private ArrayList<Integer> colors;//饼状图色体

    public static ReportFragment newInstance() {
        Bundle args = new Bundle();
        ReportFragment fragment = new ReportFragment();
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
        things = new ArrayList<>();
        return view;
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
        if (messageEvent.getTag() == MyApp.REPORT_BT) {
            if (messageEvent.getMapList() != null) {
                Description description = new Description();
                description.setText("宁通仓库管理上月支出");
                picChart.setDescription(description);
                for (Map<String, Object> map : messageEvent.getMapList()) {
                    String name = (String) map.get("name");
                    things.add(new PieEntry(randomNum(), name));
                }
                PieDataSet dataSet = new PieDataSet(things, "Label");
                initColor();
                dataSet.setColors(colors);
                PieData pieData = new PieData(dataSet);
                pieData.setDrawValues(true);
                picChart.setData(pieData);
                picChart.invalidate();
            }


        }
    }


    public int randomNum() {
        return new Random().nextInt(100);
    }


    @OnClick({R.id.btn_intent, R.id.clear})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_intent:
                Intent intent = new Intent(getContext(), MenuActivity.class);
                intent.putExtra("type", 3);
                startActivity(intent);
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
        }
    }
}
