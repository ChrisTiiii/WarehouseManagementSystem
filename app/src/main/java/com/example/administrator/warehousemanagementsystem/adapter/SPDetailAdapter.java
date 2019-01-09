package com.example.administrator.warehousemanagementsystem.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baoyachi.stepview.VerticalStepView;
import com.bumptech.glide.Glide;
import com.example.administrator.warehousemanagementsystem.MyApp;
import com.example.administrator.warehousemanagementsystem.R;
import com.example.administrator.warehousemanagementsystem.bean.ApplyBean;
import com.example.administrator.warehousemanagementsystem.bean.BudgetBean;
import com.example.administrator.warehousemanagementsystem.bean.PurchaseBean;
import com.example.administrator.warehousemanagementsystem.bean.ViewType;
import com.example.administrator.warehousemanagementsystem.util.QRCodeUtil;
import com.example.administrator.warehousemanagementsystem.util.TimeUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author: ZhongMing
 * DATE: 2018/11/26 0026
 * Description:审批订单详情
 **/
public class SPDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private final int SP_HEAD = 0;//物品用途
    private final int SP_DETAIL = 1;//物品明细
    private final int SP_EXPLAIN = 2;//领用详情
    private final int SP_PROGRESS = 3;//进度

    private int viewType;//0待审 1已审 2我的申领 3我的采购 4我的预算
    private int detailType;//300/2申领 310/3采购 320/4预算
    public List<ViewType> uiList;//界面布局list
    public ApplyBean.DataBean applyBean;//具体申领单数据list
    public PurchaseBean.DataBean purchaseBean;//具体采购单数据list
    public BudgetBean.DataBean budgetBean;//具体预算单数据list
    public OnCodeClickListener onCodeClickListener;
    MyApp myApp;

    //申领单
    public SPDetailAdapter(Context context, MyApp myApp, List<ViewType> uiList, ApplyBean.DataBean applyBean, int viewType, int detailType) {
        this.context = context;
        this.myApp = myApp;
        this.uiList = uiList;
        this.applyBean = applyBean;
        this.viewType = viewType;
        this.detailType = detailType;
    }

    //采购单
    public SPDetailAdapter(Context context, MyApp myApp, List<ViewType> uiList, PurchaseBean.DataBean purchaseBean, int viewType, int detailType) {
        this.context = context;
        this.myApp = myApp;
        this.uiList = uiList;
        this.purchaseBean = purchaseBean;
        this.viewType = viewType;
        this.detailType = detailType;

    }


    //预算单
    public SPDetailAdapter(Context context, MyApp myApp, List<ViewType> uiList, BudgetBean.DataBean budgetBean, int viewType, int detailType) {
        this.context = context;
        this.myApp = myApp;
        this.uiList = uiList;
        this.budgetBean = budgetBean;
        this.viewType = viewType;
        this.detailType = detailType;
    }

    @Override
    public int getItemViewType(int position) {
        return uiList.get(position).getType();
    }


    /**
     * 筛选主任权限不必要的申领数据
     */
    public ApplyBean.DataBean removeRoot(ApplyBean.DataBean applyBean) {
        ApplyBean.DataBean temp = applyBean;
        if (myApp.getRoot() == 130) {
            for (int i = 0; i < temp.getApplyContentList().size(); i++) {
                if (!(temp.getApplyContentList().get(i).getGoodsTypeNo() == myApp.getUser().getUserTypeNo())) {
                    temp.getApplyContentList().remove(i);
                }
            }
        }
        return temp;
    }


    /**
     * 筛选主任权限不必要的采购单数据
     */
    public PurchaseBean.DataBean removeRoot(PurchaseBean.DataBean purchaseBean) {
        PurchaseBean.DataBean temp = purchaseBean;
        if (myApp.getRoot() == 130) {
            for (int i = 0; i < temp.getPurchaseContentList().size(); i++) {
                if (!(temp.getPurchaseContentList().get(i).getGoodsTypeNo() == myApp.getUser().getUserTypeNo())) {
                    temp.getPurchaseContentList().remove(i);
                }
            }
        }
        return temp;
    }


    /**
     * 筛选主任权限不必要的预算单申请
     */
    public BudgetBean.DataBean removeRoot(BudgetBean.DataBean budgetBean) {
        BudgetBean.DataBean temp = budgetBean;
        if (myApp.getRoot() == 130) {
            for (int i = 0; i < temp.getBudgetContentList().size(); i++) {
                if (!(temp.getBudgetContentList().get(i).getGoodsTypeNo() == (myApp.getUser().getUserTypeNo()))) {
                    temp.getBudgetContentList().remove(i);
                }
            }
        }
        return temp;
    }

    //初次加载apply
    public void updateList(ApplyBean.DataBean applyBean) {
        this.applyBean = applyBean;
        notifyDataSetChanged();
    }

    //初次加载Purchase
    public void updateList(PurchaseBean.DataBean purchaseBean) {
        this.purchaseBean = purchaseBean;
        notifyDataSetChanged();
    }

    //初次加载Budget
    public void updateList(BudgetBean.DataBean budgetBean) {
        this.budgetBean = budgetBean;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view;
        switch (viewType) {
            case SP_HEAD:
                view = LayoutInflater.from(context).inflate(R.layout.sp_item_head, null, false);
                return new HeadViewHolder(view);
            case SP_DETAIL:
                view = LayoutInflater.from(context).inflate(R.layout.sp_item_detail, null, false);
                return new DetailViewHolder(view);
            case SP_EXPLAIN:
                view = LayoutInflater.from(context).inflate(R.layout.sp_item_explain, null, false);
                return new ExplainViewHolder(view);
            case SP_PROGRESS:
                view = LayoutInflater.from(context).inflate(R.layout.sp_item_progress, null, false);
                return new ProgressViewHolder(view);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (detailType == 300 || detailType == 2) {//申领单
            if (viewHolder instanceof HeadViewHolder) {
                HeadViewHolder headViewHolder = (HeadViewHolder) viewHolder;
                headViewHolder.spPerson.setText(String.valueOf(applyBean.getFromUserName()));
                headViewHolder.spBh.setText(applyBean.getApplyId());
                headViewHolder.tvChange.setText("所在部门：");
                headViewHolder.tvSpBm.setText(String.valueOf(applyBean.getDeptName()));
                headViewHolder.tvSpUsefor.setText(TimeUtil.stampToDate(String.valueOf(applyBean.getApplyStartdate())));
                headViewHolder.llBao.setVisibility(View.VISIBLE);
                headViewHolder.tvBao.setTextColor(context.getResources().getColor(R.color.red));
                headViewHolder.tvBao.setText(applyBean.getApplyWarning());
                //待审批
                if (viewType == 0) {
                    Glide.with(context).load(R.drawable.spwait).into(headViewHolder.ivHead);
                    headViewHolder.state.setText(applyBean.getApplyState());
                }
                //已审批
                if (viewType == 1) {
                    if (applyBean.getReviewList() != null)
                        for (ApplyBean.DataBean.ReviewListBean dataBean : applyBean.getReviewList())
                            if (myApp.getUser().getId() == dataBean.getUserNo())
                                if (dataBean.getReviewStateNo() == 601) {
                                    Glide.with(context).load(R.drawable.sppass).into(headViewHolder.ivHead);
                                    headViewHolder.state.setText("审批通过");
                                } else if (dataBean.getReviewStateNo() == 602) {
                                    Glide.with(context).load(R.drawable.disagree).into(headViewHolder.ivHead);
                                    headViewHolder.state.setText("审批未通过");
                                }
                    headViewHolder.state.setTextColor(context.getResources().getColor(R.color.red));
                }
                //我的审批
                if (viewType == 2) {
                    headViewHolder.state.setTextColor(context.getResources().getColor(R.color.steelblue));
                    Glide.with(context).load(R.drawable.wode).into(headViewHolder.ivHead);
                    headViewHolder.state.setText("我的申领单");
                    for (ApplyBean.DataBean.ReviewListBean dataBean : applyBean.getReviewList()) {
                        if (dataBean.getReviewStateNo() == 602) {
                            headViewHolder.state.setTextColor(context.getResources().getColor(R.color.red));
                            headViewHolder.state.setText("申领未通过");
                        }
                    }
                    if (applyBean.getReviewList().size() > 0)
                        if (applyBean.getReviewList().get(applyBean.getReviewList().size() - 1).getReviewStateNo() == 601) {
                            //添加二维码,默认数据加载
                            headViewHolder.ivCode.setVisibility(View.VISIBLE);
                            Bitmap mBitmap = QRCodeUtil.createQRCodeBitmap(applyBean.getApplyId(), 480, 480);
                            headViewHolder.ivCode.setImageBitmap(mBitmap);
                            headViewHolder.state.setTextColor(context.getResources().getColor(R.color.red));
                            headViewHolder.state.setText("申请通过");
                        }
                }
            }

            if (viewHolder instanceof DetailViewHolder) {
                DetailViewHolder detailViewHolder = (DetailViewHolder) viewHolder;
                //通过当前位置计算出list列表当前项
                if (applyBean.getApplyContentList() != null && (i - 1) >= 0) {
                    detailViewHolder.tvDetailId.setText(i + "");
                    detailViewHolder.tvDetailName.setText(applyBean.getApplyContentList().get(i - 1).getGoodsName());
                    detailViewHolder.tvNum.setText(String.valueOf(applyBean.getApplyContentList().get(i - 1).getGoodsNum()));
                    detailViewHolder.tvWarning.setText(applyBean.getApplyContentList().get(i - 1).getAnnotation());
                }
            }
            if (viewHolder instanceof ExplainViewHolder) {
                ExplainViewHolder explainViewHolder = (ExplainViewHolder) viewHolder;
                explainViewHolder.tvExplain.setText(applyBean.getApplyUsage());
            }

            if (viewHolder instanceof ProgressViewHolder) {
                ProgressViewHolder progressViewHolder = (ProgressViewHolder) viewHolder;
                List<String> temp = new ArrayList<>();
                int nowPoint = 1;//更改step状态位置
                boolean bol = false;//记录是否有未通过
                String unpass = "";
                temp.add(applyBean.getFromUserName() + "  发起");
                if (applyBean.getReviewList() != null) {
                    for (int j = 0; j < applyBean.getReviewList().size(); j++) {
                        if (!(applyBean.getReviewList().get(j).getReviewStateNo() == 600)) {
                            if (applyBean.getReviewList().get(j).getReviewStateNo() == 602) {
                                bol = true;
                                unpass = applyBean.getReviewList().get(j).getReviewReason();
                                nowPoint = 1;
                                nowPoint += j;
                            } else {
                                nowPoint = 1;
                                nowPoint += (j + 1);
                            }

                        }
                        temp.add(applyBean.getReviewList().get(j).getReviewUserName() + "  " + applyBean.getReviewList().get(j).getReviewState());
                    }
                    if (applyBean.getReviewList().size() > 0)
                        if (applyBean.getReviewList().get(applyBean.getReviewList().size() - 1).getReviewStateNo() == 601)
                            temp.add("您的申领申请已完成了审批流程:)");
                }
                System.out.println("当前步数：" + nowPoint);
                if (bol) {//如果被拒绝了显示拒绝理由
                    progressViewHolder.disagreeLayout.setVisibility(View.VISIBLE);
                    progressViewHolder.tvDisagree.setTextColor(context.getResources().getColor(R.color.red));
                    progressViewHolder.tvDisagree.setText(unpass);
                    setStepView(progressViewHolder, temp, nowPoint);
                } else {
                    if (applyBean.getReviewList().size() > 0)
                        if (applyBean.getReviewList().get(applyBean.getReviewList().size() - 1).getReviewStateNo() == 601) {//如果通过就通过ui显示完成步数
                            setStepView(progressViewHolder, temp, nowPoint + 2);
                        } else
                            setStepView(progressViewHolder, temp, nowPoint);
                }
            }
        } else if (detailType == 310 || detailType == 3) {//采购单
            if (viewHolder instanceof HeadViewHolder) {
                HeadViewHolder headViewHolder = (HeadViewHolder) viewHolder;

                headViewHolder.spPerson.setText(String.valueOf(purchaseBean.getFromUserName()));
                headViewHolder.spBh.setText(purchaseBean.getPurcId());
                headViewHolder.tvChange.setText("采购供应：");
                headViewHolder.tvSpBm.setText(String.valueOf(purchaseBean.getPurcSupplier()));
                headViewHolder.tvSpUsefor.setText(TimeUtil.stampToDate(String.valueOf(purchaseBean.getPurcStartdate())));
                headViewHolder.llBao.setVisibility(View.GONE);
                //待审批
                if (viewType == 0) {
                    Glide.with(context).load(R.drawable.spwait).into(headViewHolder.ivHead);
                    headViewHolder.state.setText(purchaseBean.getPurcState());
                }
                //已审批
                if (viewType == 1) {
                    if (purchaseBean.getReviewList() != null)
                        for (PurchaseBean.DataBean.ReviewListBean dataBean : purchaseBean.getReviewList())
                            if (myApp.getUser().getId() == dataBean.getUserNo())
                                if (dataBean.getReviewStateNo() == 601) {
                                    Glide.with(context).load(R.drawable.sppass).into(headViewHolder.ivHead);
                                    headViewHolder.state.setText("审批通过");
                                } else if (dataBean.getReviewStateNo() == 602) {
                                    Glide.with(context).load(R.drawable.disagree).into(headViewHolder.ivHead);
                                    headViewHolder.state.setText("审批未通过");
                                }
                    headViewHolder.state.setTextColor(context.getResources().getColor(R.color.red));
                }
                //我的采购
                if (viewType == 3) {
                    headViewHolder.state.setTextColor(context.getResources().getColor(R.color.steelblue));
                    Glide.with(context).load(R.drawable.wode).into(headViewHolder.ivHead);
                    headViewHolder.state.setText("我的采购单");
                    for (PurchaseBean.DataBean.ReviewListBean dataBean : purchaseBean.getReviewList()) {
                        if (dataBean.getReviewStateNo() == 602) {
                            headViewHolder.state.setTextColor(context.getResources().getColor(R.color.red));
                            headViewHolder.state.setText("申请未通过");
                        }
                    }
                    if (purchaseBean.getReviewList().size() > 0)
                        if (purchaseBean.getReviewList().get(purchaseBean.getReviewList().size() - 1).getReviewStateNo() == 601) {
                            //添加二维码,默认数据加载
                            headViewHolder.ivCode.setVisibility(View.VISIBLE);
                            Bitmap mBitmap = QRCodeUtil.createQRCodeBitmap(purchaseBean.getPurcId(), 480, 480);
                            headViewHolder.ivCode.setImageBitmap(mBitmap);
                            headViewHolder.state.setTextColor(context.getResources().getColor(R.color.red));
                            headViewHolder.state.setText("申请通过");
                        }
                }
            }

            if (viewHolder instanceof DetailViewHolder) {
                DetailViewHolder detailViewHolder = (DetailViewHolder) viewHolder;
                //通过当前位置计算出list列表当前项
                if (purchaseBean.getPurchaseContentList() != null && (i - 1) >= 0) {
                    detailViewHolder.tvDetailId.setText(i + "");
                    detailViewHolder.tvDetailName.setText(purchaseBean.getPurchaseContentList().get(i - 1).getGoodsName());
                    detailViewHolder.tvNum.setText(String.valueOf(purchaseBean.getPurchaseContentList().get(i - 1).getGoodsNum()));
                    detailViewHolder.tvWarning.setText(purchaseBean.getPurchaseContentList().get(i - 1).getAnnotation());
                }
            }
            if (viewHolder instanceof ExplainViewHolder) {
                ExplainViewHolder explainViewHolder = (ExplainViewHolder) viewHolder;
                explainViewHolder.tvExplain.setText(purchaseBean.getPurcUsage());
            }
            if (viewHolder instanceof ProgressViewHolder) {
                ProgressViewHolder progressViewHolder = (ProgressViewHolder) viewHolder;
                List<String> temp = new ArrayList<>();
                int nowPoint = 1;//更改step状态位置
                boolean bol = false;//记录是否有未通过
                String unpass = "";
                temp.add(purchaseBean.getFromUserName() + "  发起");
                if (purchaseBean.getReviewList() != null) {
                    for (int j = 0; j < purchaseBean.getReviewList().size(); j++) {
                        if (!(purchaseBean.getReviewList().get(j).getReviewStateNo() == 600)) {
                            if (purchaseBean.getReviewList().get(j).getReviewStateNo() == 602) {
                                bol = true;
                                unpass = purchaseBean.getReviewList().get(j).getReviewReason();
                                nowPoint = 1;
                                nowPoint += j;
                            } else {
                                nowPoint = 1;
                                nowPoint += (j + 1);
                            }
                        }
                        temp.add(purchaseBean.getReviewList().get(j).getReviewUserName() + "  " + purchaseBean.getReviewList().get(j).getReviewState());
                    }
                    if (purchaseBean.getReviewList().size() > 0)
                        if (purchaseBean.getReviewList().get(purchaseBean.getReviewList().size() - 1).getReviewStateNo() == 601)
                            temp.add("您的采购申请已完成了审批流程:)");
                }
                System.out.println("当前步数：" + nowPoint);
                if (bol) {//如果被拒绝了显示拒绝理由
                    progressViewHolder.disagreeLayout.setVisibility(View.VISIBLE);
                    progressViewHolder.tvDisagree.setTextColor(context.getResources().getColor(R.color.red));
                    progressViewHolder.tvDisagree.setText(unpass);
                    setStepView(progressViewHolder, temp, nowPoint);
                } else {
                    if (purchaseBean.getReviewList().size() > 0)
                        if (purchaseBean.getReviewList().get(purchaseBean.getReviewList().size() - 1).getReviewStateNo() == 601) {//如果通过就通过ui显示完成步数
                            setStepView(progressViewHolder, temp, nowPoint + 2);
                        } else
                            setStepView(progressViewHolder, temp, nowPoint);
                }
            }
        } else if (detailType == 320 || detailType == 4) {//预算单
            if (viewHolder instanceof HeadViewHolder) {
                HeadViewHolder headViewHolder = (HeadViewHolder) viewHolder;

                headViewHolder.spPerson.setText(String.valueOf(budgetBean.getFromUserName()));
                headViewHolder.spBh.setText(budgetBean.getBudgId());
                headViewHolder.tvChange.setText("所在部门：");
                headViewHolder.tvSpBm.setText(String.valueOf(budgetBean.getDeptName()));
                headViewHolder.tvSpUsefor.setText(TimeUtil.stampToDate(String.valueOf(budgetBean.getBudgDate())));
                //待审批
                if (viewType == 0) {
                    Glide.with(context).load(R.drawable.spwait).into(headViewHolder.ivHead);
                    headViewHolder.state.setText(budgetBean.getBudgState());
                }
                //已审批
                if (viewType == 1) {
                    if (budgetBean.getReviewList() != null)
                        for (BudgetBean.DataBean.ReviewListBean dataBean : budgetBean.getReviewList())
                            if (myApp.getUser().getId() == dataBean.getUserNo())
                                if (dataBean.getReviewStateNo() == 601) {
                                    Glide.with(context).load(R.drawable.sppass).into(headViewHolder.ivHead);
                                    headViewHolder.state.setText("审批通过");
                                } else if (dataBean.getReviewStateNo() == 602) {
                                    Glide.with(context).load(R.drawable.disagree).into(headViewHolder.ivHead);
                                    headViewHolder.state.setText("审批未通过");
                                }
                    headViewHolder.state.setTextColor(context.getResources().getColor(R.color.red));
                }
                //我的预算
                if (viewType == 4) {
                    headViewHolder.state.setTextColor(context.getResources().getColor(R.color.steelblue));
                    Glide.with(context).load(R.drawable.wode).into(headViewHolder.ivHead);
                    headViewHolder.state.setText("我的预算单");
                    for (BudgetBean.DataBean.ReviewListBean dataBean : budgetBean.getReviewList()) {
                        if (dataBean.getReviewStateNo() == 602) {
                            headViewHolder.state.setTextColor(context.getResources().getColor(R.color.red));
                            headViewHolder.state.setText("申请未通过");
                        }
                    }
                    if (budgetBean.getReviewList().size() > 0)
                        if (budgetBean.getReviewList().get(budgetBean.getReviewList().size() - 1).getReviewStateNo() == 601) {
                            //添加二维码,默认数据加载
                            headViewHolder.ivCode.setVisibility(View.VISIBLE);
                            Bitmap mBitmap = QRCodeUtil.createQRCodeBitmap(budgetBean.getBudgId(), 480, 480);
                            headViewHolder.ivCode.setImageBitmap(mBitmap);
                            headViewHolder.state.setTextColor(context.getResources().getColor(R.color.red));
                            headViewHolder.state.setText("申请通过");
                        }
                }
            }

            if (viewHolder instanceof DetailViewHolder) {
                DetailViewHolder detailViewHolder = (DetailViewHolder) viewHolder;
                //通过当前位置计算出list列表当前项
                if (budgetBean.getBudgetContentList() != null && (i - 1) >= 0) {
                    detailViewHolder.tvDetailId.setText(i + "");
                    detailViewHolder.tvDetailName.setText(budgetBean.getBudgetContentList().get(i - 1).getGoodsName());
                    detailViewHolder.tvNum.setText(String.valueOf(budgetBean.getBudgetContentList().get(i - 1).getGoodsNum()));
                    detailViewHolder.tvWarning.setText(budgetBean.getBudgetContentList().get(i - 1).getAnnotation());
                }
            }
            if (viewHolder instanceof ExplainViewHolder) {
                ExplainViewHolder explainViewHolder = (ExplainViewHolder) viewHolder;
                explainViewHolder.tvExplain.setText(budgetBean.getBudgUsage());
            }

            if (viewHolder instanceof ProgressViewHolder) {
                ProgressViewHolder progressViewHolder = (ProgressViewHolder) viewHolder;
                List<String> temp = new ArrayList<>();
                int nowPoint = 1;//更改step状态位置
                boolean bol = false;//记录是否有未通过
                String unpass = "";
                temp.add(budgetBean.getFromUserName() + "  发起");
                if (budgetBean.getReviewList() != null) {
                    for (int j = 0; j < budgetBean.getReviewList().size(); j++) {
                        if (!(budgetBean.getReviewList().get(j).getReviewStateNo() == 600)) {
                            if (budgetBean.getReviewList().get(j).getReviewStateNo() == 602) {
                                bol = true;
                                unpass = budgetBean.getReviewList().get(j).getReviewReason();
                                nowPoint = 1;
                                nowPoint += j;
                            } else {
                                nowPoint = 1;
                                nowPoint += (j + 1);
                            }
                        }
                        temp.add(budgetBean.getReviewList().get(j).getReviewUserName() + "  " + budgetBean.getReviewList().get(j).getReviewState());
                    }
                    if (budgetBean.getReviewList().size() > 0)
                        if (budgetBean.getReviewList().get(budgetBean.getReviewList().size() - 1).getReviewStateNo() == 601)
                            temp.add("您的预算申请已完成了审批流程:)");
                }
                if (bol) {//如果被拒绝了显示拒绝理由
                    progressViewHolder.disagreeLayout.setVisibility(View.VISIBLE);
                    progressViewHolder.tvDisagree.setTextColor(context.getResources().getColor(R.color.red));
                    progressViewHolder.tvDisagree.setText(unpass);
                    setStepView(progressViewHolder, temp, nowPoint);
                } else {
                    System.out.println("当前点位：" + nowPoint);
                    if (budgetBean.getReviewList().size() > 0)
                        if (budgetBean.getReviewList().get(budgetBean.getReviewList().size() - 1).getReviewStateNo() == 601) {//如果通过就通过ui显示完成步数
                            setStepView(progressViewHolder, temp, nowPoint + 2);
                        } else
                            setStepView(progressViewHolder, temp, nowPoint);
                }
            }
        }

    }


    @Override
    public int getItemCount() {
        return uiList.size();
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    class HeadViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.iv_head)
        ImageView ivHead;
        @BindView(R.id.sp_person)
        TextView spPerson;
        @BindView(R.id.sp_bh)
        TextView spBh;
        @BindView(R.id.tv_sp_bm)
        TextView tvSpBm;
        @BindView(R.id.tv_sp_usefor)
        TextView tvSpUsefor;
        @BindView(R.id.state)
        TextView state;
        @BindView(R.id.iv_code)
        ImageView ivCode;
        @BindView(R.id.detail_change)
        TextView tvChange;
        @BindView(R.id.tv_bao)
        TextView tvBao;
        @BindView(R.id.ll_bao)
        RelativeLayout llBao;

        HeadViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            ivCode.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (onCodeClickListener != null)
                onCodeClickListener.onCodeClick(v, getAdapterPosition());
        }
    }

    static class DetailViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_detail_id)
        TextView tvDetailId;
        @BindView(R.id.tv_detail_name)
        TextView tvDetailName;
        @BindView(R.id.tv_num)
        TextView tvNum;
        @BindView(R.id.tv_warning)
        TextView tvWarning;

        DetailViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    static class ExplainViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_explain)
        TextView tvExplain;

        ExplainViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    static class ProgressViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.step_view)
        VerticalStepView stepView;
        @BindView(R.id.tv_disagree)
        TextView tvDisagree;
        @BindView(R.id.tvtv_disagree)
        TextView disagreeLayout;

        ProgressViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public interface OnCodeClickListener {
        void onCodeClick(View view, int position);
    }

    public void setOnCodeClickListener(OnCodeClickListener onCodeClickListener) {
        this.onCodeClickListener = onCodeClickListener;
    }

    /**
     * 设置自定义viewType
     *
     * @param progressViewHolder
     * @param temp               全部展示数据
     * @param nowPoint           当前位置
     */
    public void setStepView(ProgressViewHolder progressViewHolder, List<String> temp, int nowPoint) {
        progressViewHolder.stepView.setStepsViewIndicatorComplectingPosition(nowPoint)//设置完成的步数
                .reverseDraw(true)//default is true
                .setStepViewTexts(temp)//总步骤
                .setLinePaddingProportion(0.85f)//设置indicator线与线间距的比例系数
                .setStepsViewIndicatorCompletedLineColor(context.getResources().getColor(R.color.lime))//设置StepsViewIndicator完成线的颜色
                .setStepsViewIndicatorUnCompletedLineColor(context.getResources().getColor(R.color.gainsboro))//设置StepsViewIndicator未完成线的颜色
                .setStepViewComplectedTextColor(context.getResources().getColor(R.color.steelblue))//设置StepsView text完成线的颜色
                .setStepViewUnComplectedTextColor(context.getResources().getColor(R.color.grey))//设置StepsView text未完成线的颜色
                .setStepsViewIndicatorCompleteIcon(ContextCompat.getDrawable(context, R.drawable.correct))//设置StepsViewIndicator CompleteIcon
                .setStepsViewIndicatorDefaultIcon(ContextCompat.getDrawable(context, R.drawable.point))//设置StepsViewIndicator DefaultIcon
                .setStepsViewIndicatorAttentionIcon(ContextCompat.getDrawable(context, R.drawable.nowpoint));//设置StepsViewIndicator AttentionIcon

    }

}
