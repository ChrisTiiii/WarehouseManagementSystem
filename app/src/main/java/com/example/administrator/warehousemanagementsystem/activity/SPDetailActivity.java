package com.example.administrator.warehousemanagementsystem.activity;

import android.app.ActivityOptions;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.administrator.warehousemanagementsystem.MyApp;
import com.example.administrator.warehousemanagementsystem.R;
import com.example.administrator.warehousemanagementsystem.activity.other.Code;
import com.example.administrator.warehousemanagementsystem.adapter.SPDetailAdapter;
import com.example.administrator.warehousemanagementsystem.bean.SPDetailBean;
import com.example.administrator.warehousemanagementsystem.bean.ViewType;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author: ZhongMing
 * DATE: 2018/11/26 0026
 * Description:该页面为待审批的详情页面
 **/
public class SPDetailActivity extends AppCompatActivity {
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.agree)
    Button agree;
    @BindView(R.id.disagree)
    Button disagree;
    @BindView(R.id.layoutButton)
    LinearLayout layoutButton;
    @BindView(R.id.revocation)
    Button revocation;
    @BindView(R.id.layout_remove)
    LinearLayout layoutRemove;
    @BindView(R.id.ll_bottom)
    LinearLayout llBottom;

    private List<ViewType> uiList;
    private SPDetailBean detailList;
    private SPDetailAdapter spDetailAdapter;
    private int type;
    private SPDetailBean spDetailBean;
    private AlertDialog.Builder builder;
    MyApp myApp;
    Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sp_detail);
        ButterKnife.bind(this);
        myApp = (MyApp) getApplication();
        if (myApp.getRoot() != -1)
            if (myApp.getRoot() == 110 || myApp.getRoot() == 130) {
                layoutButton.setVisibility(View.GONE);
                layoutRemove.setVisibility(View.VISIBLE);
            } else {
                layoutButton.setVisibility(View.VISIBLE);
                layoutRemove.setVisibility(View.GONE);
            }
        intent = getIntent();
        type = intent.getExtras().getInt("type");
        if (type == 1) {
            llBottom.setVisibility(View.GONE);
        }
        initView();
    }

    //初始化item界面
    private void initView() {
        initData();
        spDetailAdapter = new SPDetailAdapter(SPDetailActivity.this, uiList, detailList, type);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(spDetailAdapter);
        spDetailAdapter.setOnCodeClickListener(new SPDetailAdapter.OnCodeClickListener() {
            @Override
            public void onCodeClick(View view, int position) {
                Intent intent = new Intent(SPDetailActivity.this, Code.class);
                intent.putExtra("bh", detailList.getSpBH());
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(SPDetailActivity.this, view.findViewById(R.id.iv_code), "sharedView").toBundle());
            }
        });
    }

    private void initData() {
        uiList = new ArrayList<>();
        detailList = new SPDetailBean();
        spDetailBean = new SPDetailBean();
        detailList = (SPDetailBean) getIntent().getSerializableExtra("list");

//        detailList.add(spDetailBean.initData());
        uiList.add(new ViewType(ViewType.SL_TYPE_HEAD));
        for (int i = 0; i < spDetailBean.initData().getSpmx().size(); i++) {
            uiList.add(new ViewType(ViewType.SL_TYPE_DETAIL));
        }
        uiList.add(new ViewType(ViewType.SL_TYPE_ADD));
        uiList.add(new ViewType(ViewType.SL_TYPE_EXPLAIN));
    }


    @OnClick({R.id.agree, R.id.disagree, R.id.revocation})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.revocation:
                builder = new AlertDialog.Builder(SPDetailActivity.this);
                builder.setTitle("确定要撤销吗？");
                builder.setMessage("同意撤销本次申请请按确认键");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }).show();
                break;
            case R.id.agree:
                builder = new AlertDialog.Builder(SPDetailActivity.this);
                builder.setTitle("确认提交吗？");
                builder.setMessage("同意此次申请请按确定键");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
                break;
            case R.id.disagree:
                new MaterialDialog.Builder(this)
                        .title("请输入拒绝理由")
                        .inputRangeRes(2, 20, R.color.orange)
                        .inputType(InputType.TYPE_CLASS_TEXT)
                        .input("拒绝理由", null, new MaterialDialog.InputCallback() {
                            @Override
                            public void onInput(MaterialDialog dialog, CharSequence input) {
                                finish();
                            }
                        })
                        .negativeText("取消")
                        .positiveText("确定")
                        .show();
                break;
        }
    }
}
