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
import com.example.administrator.warehousemanagementsystem.bean.BudgetBean;
import com.example.administrator.warehousemanagementsystem.bean.PurchaseBean;
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
    private SPDetailAdapter spDetailAdapter;
    private NetServerImp netServerImp;

    private List<ViewType> uiList;//界面UI list
    private ApplyBean.DataBean applyBean;//申领单
    private PurchaseBean.DataBean purchaseBean;//采购单
    private BudgetBean.DataBean budgetBean;//预算单
    private int type;//type==0待审批 type==1 已审批 // 2申领单 3采购单 4预算单
    private AlertDialog.Builder builder;
    private Integer bh;//根据订单编号查询详情
    private Integer detailType;//分类哪一类订单
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
        if (detailType != null) {
            if (detailType == 300 || detailType == 2) {//我的申领单
                spDetailAdapter = new SPDetailAdapter(SPDetailActivity.this, myApp, uiList, applyBean, type, detailType);
                recycler.setAdapter(spDetailAdapter);
                netServerImp.getApplyById(String.valueOf(bh), myDialog);
            } else if (detailType == 310 || detailType == 3) {//我的采购单
                spDetailAdapter = new SPDetailAdapter(SPDetailActivity.this, myApp, uiList, purchaseBean, type, detailType);
                recycler.setAdapter(spDetailAdapter);
                netServerImp.getPurchaseById(String.valueOf(bh), myDialog);
            } else if (detailType == 320 || detailType == 4) {//我的预算单
                spDetailAdapter = new SPDetailAdapter(SPDetailActivity.this, myApp, uiList, budgetBean, type, detailType);
                recycler.setAdapter(spDetailAdapter);
                netServerImp.getBudgetById(String.valueOf(bh), myDialog);
            }
        }
        if (spDetailAdapter != null)
            clickCode();
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void loadApply(MessageEvent messageEvent) {
        switch (messageEvent.getTag()) {
            case MyApp.APPLY_DETAIL:
                applyBean = spDetailAdapter.removeRoot(messageEvent.getApplyList());//筛选分管主任对应数据
                initApplyList(applyBean);
                break;
            case MyApp.COMMIT_APPLY:
                finish();
                break;
            case MyApp.PURCHASE_DETAIL:
                purchaseBean = spDetailAdapter.removeRoot(messageEvent.getPurchaseBean());
                initPurchaseList(purchaseBean);
                break;
            case MyApp.BUDGET_DETAIL:
                budgetBean = spDetailAdapter.removeRoot(messageEvent.getBudgetBean());
                initBudgetList(budgetBean);
                break;
        }
    }


    //初始化参数
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
        detailType = intent.getExtras().getInt("detail_type");//详情类型 300申领单 310采购单 2我的申请单 3我的采购单
        bh = intent.getExtras().getInt("bh");//订单编号
        if (type == 1) {
            llBottom.setVisibility(View.GONE);
        }
        switch (detailType) {
            case 300:
                if (myApp.getRoot() != -1)
                    if (myApp.getRoot() == 100) {
                        llBottom.setVisibility(View.GONE);
                    } else {
                        layoutButton.setVisibility(View.VISIBLE);
                        layoutRemove.setVisibility(View.GONE);
                    }
                break;
            case 310:
                if (myApp.getRoot() != -1)
                    if (myApp.getRoot() == 100) {
                        llBottom.setVisibility(View.GONE);
                    } else {
                        layoutButton.setVisibility(View.VISIBLE);
                        layoutRemove.setVisibility(View.GONE);
                    }
                break;
            case 320:
                if (myApp.getRoot() != -1)
                    if (myApp.getRoot() == 100) {
                        llBottom.setVisibility(View.GONE);
                    } else {
                        layoutButton.setVisibility(View.VISIBLE);
                        layoutRemove.setVisibility(View.GONE);
                    }
                break;
            case 2:
                llBottom.setVisibility(View.GONE);
                break;
            case 3:
                llBottom.setVisibility(View.GONE);
                break;
            case 4:
                llBottom.setVisibility(View.GONE);
                break;

        }
        recycler.setLayoutManager(new LinearLayoutManager(this));
    }

    //点击code放大
    private void clickCode() {
        spDetailAdapter.setOnCodeClickListener(new SPDetailAdapter.OnCodeClickListener() {
            @Override
            public void onCodeClick(View view, int position) {
                Intent intent = new Intent(SPDetailActivity.this, Code.class);
                if (detailType == 300 || detailType == 2) {
                    intent.putExtra("bh", applyBean.getApplyId());
                    startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(SPDetailActivity.this, view.findViewById(R.id.iv_code), "sharedView").toBundle());
                } else if (detailType == 310 || detailType == 3) {
                    intent.putExtra("bh", purchaseBean.getPurcId());
                    startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(SPDetailActivity.this, view.findViewById(R.id.iv_code), "sharedView").toBundle());
                } else if (detailType == 320 || detailType == 4) {
                    intent.putExtra("bh", budgetBean.getBudgId());
                    startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(SPDetailActivity.this, view.findViewById(R.id.iv_code), "sharedView").toBundle());
                }

            }
        });
    }

    //获取我的申请网络数据并进行初始化
    private void initApplyList(ApplyBean.DataBean applyBean) {
        uiList.add(new ViewType(ViewType.SL_TYPE_HEAD));
        uiList.add(new ViewType(ViewType.SL_TYPE_ADD));
        uiList.add(new ViewType(ViewType.SL_TYPE_EXPLAIN));
        for (int i = 0; i < applyBean.getApplyContentList().size(); i++)
            uiList.add(1, new ViewType(ViewType.SL_TYPE_DETAIL));
        spDetailAdapter.updateList(applyBean);
    }

    //获取我的采购
    private void initPurchaseList(PurchaseBean.DataBean purchaseBean) {
        uiList.add(new ViewType(ViewType.SL_TYPE_HEAD));
        uiList.add(new ViewType(ViewType.SL_TYPE_ADD));
        uiList.add(new ViewType(ViewType.SL_TYPE_EXPLAIN));
        for (int i = 0; i < purchaseBean.getPurchaseContentList().size(); i++)
            uiList.add(1, new ViewType(ViewType.SL_TYPE_DETAIL));
        spDetailAdapter.updateList(purchaseBean);

    }

    //获取我的预算
    private void initBudgetList(BudgetBean.DataBean budgetBean) {
        uiList.add(new ViewType(ViewType.SL_TYPE_HEAD));
        uiList.add(new ViewType(ViewType.SL_TYPE_ADD));
        uiList.add(new ViewType(ViewType.SL_TYPE_EXPLAIN));
        for (int i = 0; i < budgetBean.getBudgetContentList().size(); i++)
            uiList.add(1, new ViewType(ViewType.SL_TYPE_DETAIL));
        spDetailAdapter.updateList(budgetBean);
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
                switch (detailType) {
                    case 300://同意申请单
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
                    case 310://同意采购单
                        builder = new AlertDialog.Builder(SPDetailActivity.this);
                        builder.setTitle("确认提交吗?");
                        builder.setMessage("同意此次申请请按确定键");
                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (purchaseBean.getReviewList() != null) {
                                    int temp = -1;
                                    for (PurchaseBean.DataBean.ReviewListBean dataBean : purchaseBean.getReviewList()) {
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
                    case 320://同意预算单
                        builder = new AlertDialog.Builder(SPDetailActivity.this);
                        builder.setTitle("确认提交吗?");
                        builder.setMessage("同意此次申请请按确定键");
                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (budgetBean.getReviewList() != null) {
                                    int temp = -1;
                                    for (BudgetBean.DataBean.ReviewListBean dataBean : budgetBean.getReviewList()) {
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
                }
                break;
            case R.id.disagree://拒绝
                switch (detailType) {
                    case 300:
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
                                            netServerImp.refuseReview(temp, String.valueOf(input));
                                    }
                                })
                                .negativeText("取消")
                                .positiveText("确定")
                                .show();
                        break;
                    case 310:
                        new MaterialDialog.Builder(this)
                                .title("请输入拒绝理由")
                                .inputRangeRes(2, 20, R.color.orange)
                                .inputType(InputType.TYPE_CLASS_TEXT)
                                .input("拒绝理由", null, new MaterialDialog.InputCallback() {
                                    @Override
                                    public void onInput(MaterialDialog dialog, CharSequence input) {
                                        int temp = -1;
                                        for (PurchaseBean.DataBean.ReviewListBean dataBean : purchaseBean.getReviewList()) {
                                            if (myApp.getUser().getId() == dataBean.getUserNo())
                                                temp = dataBean.getId();
                                        }
                                        System.out.println("review:" + temp);
                                        if (temp != -1)
                                            netServerImp.refuseReview(temp, String.valueOf(input));
                                    }
                                })
                                .negativeText("取消")
                                .positiveText("确定")
                                .show();
                        break;
                    case 320:
                        new MaterialDialog.Builder(this)
                                .title("请输入拒绝理由")
                                .inputRangeRes(2, 20, R.color.orange)
                                .inputType(InputType.TYPE_CLASS_TEXT)
                                .input("拒绝理由", null, new MaterialDialog.InputCallback() {
                                    @Override
                                    public void onInput(MaterialDialog dialog, CharSequence input) {
                                        int temp = -1;
                                        for (BudgetBean.DataBean.ReviewListBean dataBean : budgetBean.getReviewList()) {
                                            if (myApp.getUser().getId() == dataBean.getUserNo())
                                                temp = dataBean.getId();
                                        }
                                        if (temp != -1)
                                            netServerImp.refuseReview(temp, String.valueOf(input));
                                    }
                                })
                                .negativeText("取消")
                                .positiveText("确定")
                                .show();
                        break;
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
