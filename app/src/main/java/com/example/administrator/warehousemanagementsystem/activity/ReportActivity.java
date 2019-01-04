package com.example.administrator.warehousemanagementsystem.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.bin.david.form.core.SmartTable;
import com.example.administrator.warehousemanagementsystem.MyApp;
import com.example.administrator.warehousemanagementsystem.R;
import com.example.administrator.warehousemanagementsystem.bean.StoreHouseReport;
import com.example.administrator.warehousemanagementsystem.net.NetServerImp;
import com.example.administrator.warehousemanagementsystem.util.MessageEvent;
import com.example.administrator.warehousemanagementsystem.util.MyDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author: ZhongMing
 * DATE: 2018/12/24 0024
 * Description:仓库库存报表查看
 **/
public class ReportActivity extends AppCompatActivity {
    @BindView(R.id.table)
    SmartTable<StoreHouseReport> table;
    private List<StoreHouseReport> list;
    MyApp myApp;
    private NetServerImp netServerImp;
    private MyDialog myDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_activity);
        ButterKnife.bind(this);
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
        myApp = (MyApp) getApplication();
        initView();
    }


    /**
     * 初始化操作
     */
    private void initView() {
        Intent intent = getIntent();
        String code = intent.getExtras().getString("which");
        myDialog = new MyDialog(ReportActivity.this, 1);
        netServerImp = new NetServerImp(myApp);
        myDialog.showDialog();
        netServerImp.getStockRecord(code, myDialog);
        list = new ArrayList<>();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getStoreData(MessageEvent messageEvent) {
        switch (messageEvent.getTag()) {
            case MyApp.CKKC:
                if (messageEvent.getStorehouseBean() != null) {
                    list = messageEvent.getStorehouseBean();
                    table.setData(list);
                    table.setZoom(true);//可以设置放大最大和最小值
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().unregister(this);
    }
}
