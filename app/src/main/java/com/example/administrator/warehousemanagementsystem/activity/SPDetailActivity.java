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
import android.widget.LinearLayout;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.administrator.warehousemanagementsystem.MyApp;
import com.example.administrator.warehousemanagementsystem.R;
import com.example.administrator.warehousemanagementsystem.activity.other.Code;
import com.example.administrator.warehousemanagementsystem.adapter.SPDetailAdapter;
import com.example.administrator.warehousemanagementsystem.bean.ApplyBean;
import com.example.administrator.warehousemanagementsystem.bean.ViewType;
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
import butterknife.OnClick;

/**
 * author: ZhongMing
 * DATE: 2018/11/26 0026
 * Description:该页面为审批的详情页面
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
    private ApplyBean.DataBean applyBean;
    private SPDetailAdapter spDetailAdapter;
    private int type;//type==0待审批 type==1 已审批 //自己
    private AlertDialog.Builder builder;
    private Integer bh;//根据订单编号查询详情
    private NetServerImp netServerImp;
    MyApp myApp;
    Intent intent;
    MyDialog myDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sp_detail);
        ButterKnife.bind(this);
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
        initView();
        witchRoot();//根据权限显示不同界面

        netServerImp.getApply(String.valueOf(bh), myDialog);
        if (spDetailAdapter != null)
            clickCode();
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void loadApply(MessageEvent messageEvent) {
        switch (messageEvent.getTag()) {
            case MyApp.APPLY_DETAIL:
                applyBean = messageEvent.getApplyList();
                initList(applyBean);
                break;
            case MyApp.COMMIT_APPLY:
                finish();
                break;
        }
    }

    void initView() {
        myApp = (MyApp) getApplication();
        netServerImp = new NetServerImp(myApp);
        uiList = new ArrayList<>();
        applyBean = new ApplyBean.DataBean();
        myDialog = new MyDialog(SPDetailActivity.this, 1);
        myDialog.showDialog();
    }

    //根据权限显示不同界面
    public void witchRoot() {
        intent = getIntent();
        type = intent.getExtras().getInt("type");
        System.out.println("type:" + type);
        if (type == 1) {
            llBottom.setVisibility(View.GONE);
        }
        bh = intent.getExtras().getInt("bh");
        if (myApp.getRoot() != -1)
            if (myApp.getRoot() == 100 || myApp.getRoot() == 120) {
                layoutButton.setVisibility(View.GONE);
                layoutRemove.setVisibility(View.VISIBLE);
            } else {
                layoutButton.setVisibility(View.VISIBLE);
                layoutRemove.setVisibility(View.GONE);
            }
        recycler.setLayoutManager(new LinearLayoutManager(this));
        spDetailAdapter = new SPDetailAdapter(SPDetailActivity.this, myApp, uiList, applyBean, type);
        recycler.setAdapter(spDetailAdapter);
    }

    //点击code放大
    private void clickCode() {
        spDetailAdapter.setOnCodeClickListener(new SPDetailAdapter.OnCodeClickListener() {
            @Override
            public void onCodeClick(View view, int position) {
                Intent intent = new Intent(SPDetailActivity.this, Code.class);
                intent.putExtra("bh", applyBean.getApplyId());
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(SPDetailActivity.this, view.findViewById(R.id.iv_code), "sharedView").toBundle());
            }
        });
    }


    //获取网络数据并进行初始化
    private void initList(ApplyBean.DataBean applyBean) {
        uiList.add(new ViewType(ViewType.SL_TYPE_HEAD));
        uiList.add(new ViewType(ViewType.SL_TYPE_ADD));
        uiList.add(new ViewType(ViewType.SL_TYPE_EXPLAIN));
        for (int i = 0; i < applyBean.getApplyContentList().size(); i++)
            uiList.add(1, new ViewType(ViewType.SL_TYPE_DETAIL));
        spDetailAdapter.updateList(applyBean);
    }


    @OnClick({R.id.agree, R.id.disagree, R.id.revocation})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.revocation:
                Toast.makeText(myApp, "您当前无法撤销", Toast.LENGTH_SHORT).show();
//                builder = new AlertDialog.Builder(SPDetailActivity.this);
//                builder.setTitle("确定要撤销吗？");
//                builder.setMessage("同意撤销本次申请请按确认键");
//                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        finish();
//                    }
//                });
//                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                    }
//                }).show();
                break;
            case R.id.agree:
                builder = new AlertDialog.Builder(SPDetailActivity.this);
                builder.setTitle("确认提交吗?");
                builder.setMessage("同意此次申请请按确定键");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (applyBean.getReviewList() != null) {
                            int temp = -1;
                            for (ApplyBean.DataBean.ReviewListBean dataBean : applyBean.getReviewList()) {
                                if (myApp.getUser().getId() == dataBean.getUserNo())
                                    temp = dataBean.getId();
                            }
                            System.out.println("review:" + temp);
                            if (temp != -1)
                                netServerImp.agreeReview(temp);
                        }
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
                                int temp = -1;
                                for (ApplyBean.DataBean.ReviewListBean dataBean : applyBean.getReviewList()) {
                                    if (myApp.getUser().getId() == dataBean.getUserNo())
                                        temp = dataBean.getId();
                                }
                                System.out.println("review:" + temp);
                                if (temp != -1)
                                    netServerImp.refuseReview(temp);
                            }
                        })
                        .negativeText("取消")
                        .positiveText("确定")
                        .show();
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
