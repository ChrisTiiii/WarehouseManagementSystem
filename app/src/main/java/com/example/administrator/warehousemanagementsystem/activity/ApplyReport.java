package com.example.administrator.warehousemanagementsystem.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.warehousemanagementsystem.MyApp;
import com.example.administrator.warehousemanagementsystem.R;
import com.example.administrator.warehousemanagementsystem.bean.MyGoods;
import com.example.administrator.warehousemanagementsystem.net.NetServerImp;
import com.example.administrator.warehousemanagementsystem.util.MessageEvent;
import com.example.administrator.warehousemanagementsystem.util.MyDialog;
import com.example.administrator.warehousemanagementsystem.util.MyXFormatter;
import com.example.administrator.warehousemanagementsystem.util.TimeUtil;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author: ZhongMing
 * DATE: 2019/1/4 0004
 * Description:收费站物资领用情况
 **/
public class ApplyReport extends AppCompatActivity {
    @BindView(R.id.mHorizontalBarChart)
    HorizontalBarChart mChart;
    private ArrayList<String> name;  //保存X轴坐标数据
    private MyDialog myDialog;
    private MyApp myApp;
    private String begin;
    private String end;
    private String which;
    private NetServerImp netServerImp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.apply_report);
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
        ButterKnife.bind(this);
        myApp = (MyApp) getApplication();
        myDialog = new MyDialog(ApplyReport.this, 1);
        netServerImp = new NetServerImp(myApp);
        Intent intent = getIntent();
        if (intent != null) {
            myDialog.showDialog();
            begin = intent.getExtras().getString("begin");
            end = intent.getExtras().getString("end");
            which = intent.getExtras().getString("which");
            try {
                if (myApp.getRoot() == 100 || myApp.getRoot() == 110) {
                    netServerImp.getCountStockOutRecord(TimeUtil.dateToStamp(begin), TimeUtil.dateToStamp(end), myApp.getUser().getDeptNo(), myDialog);
                } else if (which != null) {
                    netServerImp.getCountStockOutRecord(TimeUtil.dateToStamp(begin), TimeUtil.dateToStamp(end), Integer.parseInt(which), myDialog);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void backData(MessageEvent messageEvent) {
        switch (messageEvent.getTag()) {
            case MyApp.COUNT_STOCK:
                if (messageEvent.getGoodsList() != null) {
                    name = new ArrayList<>();//名称
                    List<Integer> num = new ArrayList<>();//数量
                    for (MyGoods myGoods : messageEvent.getGoodsList()) {
                        name.add(myGoods.getName());
                        num.add(Integer.parseInt(myGoods.getNum()));
                    }
                    initView(num);
                }
                break;
        }
    }


    private void initView(List<Integer> num) {
        mChart.setDrawBarShadow(false);
        mChart.setDrawValueAboveBar(true);
        mChart.setContentDescription("宁通收费站领用情况");
        mChart.getDescription().setEnabled(false);
        mChart.setPinchZoom(false);//scaling can now only be done on x- and y-axis separately
        mChart.setDrawGridBackground(false);
        mChart.getAxisRight().setEnabled(false);//不绘制右侧轴线
        XAxis xl = mChart.getXAxis();
        xl.setValueFormatter(new MyXFormatter(name));
        xl.setPosition(XAxis.XAxisPosition.BOTTOM);
//        xl.setLabelRotationAngle(45);//标签倾斜
        xl.setDrawAxisLine(true);
        xl.setDrawGridLines(false);
        xl.setCenterAxisLabels(false);//可不加这句，默认为false
        //xl.setGranularity(sCount);  //x轴坐标占的宽度
        xl.setGranularity(1f); //x轴坐标占的宽度
        //xl.setCenterAxisLabels(true);
        xl.setAxisMinimum(-0.5f);// 此轴显示的最小值
        //xl.setAxisMaximum(sCount*sCount);  // 此轴显示的最大值
        xl.setLabelCount(name.size()); //显示的坐标数量

        YAxis yl = mChart.getAxisLeft();
        yl.setDrawAxisLine(true);
        yl.setDrawGridLines(true);
        yl.setAxisMinimum(0f); //this replaces setStartAtZero(true)

        setData(name.size(), num);
        mChart.setFitBars(true);
        mChart.animateXY(2000, 2000);

        Legend legend = mChart.getLegend();
        legend.setEnabled(true);//显示图例
    }


    private void setData(int count, List<Integer> num) {
        //float barWidth = count-1;    //每个彩色数据条的宽度
        //float spaceForBar = count;   //每个数据条实际占的宽度
        float barWidth = 0.8f; //每个彩色数据条的宽度
        float spaceForBar = 1f; //每个数据条实际占的宽度
        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
        for (int i = 0; i < count; i++) {
            yVals1.add(new BarEntry(i * spaceForBar, num.get(i), null));
        }
        BarDataSet set1;
        if (mChart.getData() != null && mChart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) mChart.getData().getDataSetByIndex(0);
            set1.setValues(yVals1);
            mChart.getData().notifyDataChanged();
            mChart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(yVals1, "宁通收费站申领使用情况");
//            set1.setDrawIcons(false);
            ArrayList<IBarDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);
            BarData data = new BarData(dataSets);
            data.setValueTextSize(10f);
            data.setBarWidth(barWidth);
            mChart.setData(data);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().unregister(this);
    }
}
