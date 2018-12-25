package com.example.administrator.warehousemanagementsystem.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.bin.david.form.core.SmartTable;
import com.example.administrator.warehousemanagementsystem.R;
import com.example.administrator.warehousemanagementsystem.bean.Report;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author: ZhongMing
 * DATE: 2018/12/24 0024
 * Description:
 **/
public class ReportActivity extends AppCompatActivity {
    @BindView(R.id.table)
    SmartTable<Report> table;
    private List<Report> list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_activity);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String which = intent.getExtras().getString("which");
        list = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            Report report = new Report(which, "商品" + i, i);
            list.add(report);
        }
        System.out.println(list.size());
        table.setData(list);
        table.setZoom(true);//可以设置放大最大和最小值
    }
}
