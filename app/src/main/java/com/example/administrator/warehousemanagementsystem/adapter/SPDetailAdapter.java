package com.example.administrator.warehousemanagementsystem.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.warehousemanagementsystem.MyApp;
import com.example.administrator.warehousemanagementsystem.R;
import com.example.administrator.warehousemanagementsystem.bean.ApplyBean;
import com.example.administrator.warehousemanagementsystem.bean.BudgetBean;
import com.example.administrator.warehousemanagementsystem.bean.PurchaseBean;
import com.example.administrator.warehousemanagementsystem.bean.ViewType;
import com.example.administrator.warehousemanagementsystem.util.QRCodeUtil;
import com.example.administrator.warehousemanagementsystem.util.TimeUtil;
import com.githang.stepview.StepView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author: ZhongMing
 * DATE: 2018/11/26 0026
 * Description:审批item详情
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
                //添加二维码,默认数据加载
                Bitmap mBitmap = QRCodeUtil.createQRCodeBitmap(applyBean.getApplyId(), 480, 480);
                headViewHolder.ivCode.setImageBitmap(mBitmap);
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
                                if (dataBean.getReviewState().equals("通过")) {
                                    Glide.with(context).load(R.drawable.sppass).into(headViewHolder.ivHead);
                                    headViewHolder.state.setText("审批通过");
                                } else if (dataBean.getReviewState().equals("未通过")) {
                                    Glide.with(context).load(R.drawable.disagree).into(headViewHolder.ivHead);
                                    headViewHolder.state.setText("审批未通过");
                                }
                    headViewHolder.state.setTextColor(context.getResources().getColor(R.color.red));
                }
                //我的审批
                if (viewType == 2) {
                    headViewHolder.state.setTextColor(context.getResources().getColor(R.color.steelblue));
                    Glide.with(context).load(R.drawable.wode).into(headViewHolder.ivHead);
                    headViewHolder.state.setText("我的申请单");
                    for (ApplyBean.DataBean.ReviewListBean dataBean : applyBean.getReviewList()) {
                        if (dataBean.getReviewState().equals("未通过")) {
                            headViewHolder.state.setTextColor(context.getResources().getColor(R.color.red));
                            headViewHolder.state.setText("申请未通过");
                        }
                    }
                    if (applyBean.getReviewList().size() > 0)
                        if (applyBean.getReviewList().get(applyBean.getReviewList().size() - 1).getReviewState().equals("通过")) {
                            headViewHolder.state.setTextColor(context.getResources().getColor(R.color.red));
                            headViewHolder.state.setText("申请已通过");
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
                int nowPoint = 0;//更改step状态位置
                boolean bol = false;//记录是否有未通过
                if (applyBean.getReviewList() != null) {
                    for (int j = 0; j < applyBean.getReviewList().size(); j++) {
                        System.out.println(applyBean.getReviewList().get(j).toString());
                        if (!applyBean.getReviewList().get(j).getReviewState().equals("等待审批")) {
                            if (applyBean.getReviewList().get(j).getReviewState().equals("未通过")) {
                                bol = true;
                                temp.add(String.valueOf(applyBean.getReviewList().get(j).getReviewUserName()) + applyBean.getReviewList().get(j).getReviewState());
                            } else
                                temp.add(String.valueOf(applyBean.getReviewList().get(j).getReviewUserName()));
                            nowPoint = 0;
                            nowPoint += j + 1;
                        } else
                            temp.add(String.valueOf(applyBean.getReviewList().get(j).getReviewUserName()));
                    }
                }
                progressViewHolder.stepView.setSteps(temp);
                System.out.println("当前状态" + (nowPoint + 1));
                if (bol) {
                    progressViewHolder.stepView.selectedStep(nowPoint);//当前状态
                    progressViewHolder.disagreeLayout.setVisibility(View.VISIBLE);
                    progressViewHolder.tvDisagree.setTextColor(context.getResources().getColor(R.color.red));
                    progressViewHolder.tvDisagree.setText("拒绝理由");
                } else {
                    progressViewHolder.stepView.selectedStep(nowPoint + 1);//当前状态
                }
            }
        } else if (detailType == 310 || detailType == 3) {//采购单
            if (viewHolder instanceof HeadViewHolder) {
                HeadViewHolder headViewHolder = (HeadViewHolder) viewHolder;
                //添加二维码,默认数据加载
                Bitmap mBitmap = QRCodeUtil.createQRCodeBitmap(purchaseBean.getPurcId(), 480, 480);
                headViewHolder.ivCode.setImageBitmap(mBitmap);
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
                                if (dataBean.getReviewState().equals("通过")) {
                                    Glide.with(context).load(R.drawable.sppass).into(headViewHolder.ivHead);
                                    headViewHolder.state.setText("审批通过");
                                } else if (dataBean.getReviewState().equals("未通过")) {
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
                        if (dataBean.getReviewState().equals("未通过")) {
                            headViewHolder.state.setTextColor(context.getResources().getColor(R.color.red));
                            headViewHolder.state.setText("申请未通过");
                        }
                    }
                    if (purchaseBean.getReviewList().size() > 0)
                        if (purchaseBean.getReviewList().get(purchaseBean.getReviewList().size() - 1).getReviewState().equals("通过")) {
                            headViewHolder.state.setTextColor(context.getResources().getColor(R.color.red));
                            headViewHolder.state.setText("申请已通过");
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
                int nowPoint = 0;//更改step状态位置
                boolean bol = false;//记录是否有未通过
                if (purchaseBean.getReviewList() != null) {
                    for (int j = 0; j < purchaseBean.getReviewList().size(); j++) {
                        System.out.println(purchaseBean.getReviewList().get(j).toString());
                        if (!purchaseBean.getReviewList().get(j).getReviewState().equals("等待审批")) {
                            if (purchaseBean.getReviewList().get(j).getReviewState().equals("未通过")) {
                                bol = true;
                                temp.add(String.valueOf(purchaseBean.getReviewList().get(j).getReviewUserName()) + purchaseBean.getReviewList().get(j).getReviewState());
                            } else
                                temp.add(String.valueOf(purchaseBean.getReviewList().get(j).getReviewUserName()));
                            nowPoint = 0;
                            nowPoint += j + 1;
                        } else
                            temp.add(String.valueOf(purchaseBean.getReviewList().get(j).getReviewUserName()));
                    }
                }
                progressViewHolder.stepView.setSteps(temp);
                System.out.println("当前状态" + (nowPoint + 1));
                if (bol) {
                    progressViewHolder.stepView.selectedStep(nowPoint);//当前状态
                    progressViewHolder.disagreeLayout.setVisibility(View.VISIBLE);
                    progressViewHolder.tvDisagree.setTextColor(context.getResources().getColor(R.color.red));
                    progressViewHolder.tvDisagree.setText("拒绝理由");
                } else {
                    progressViewHolder.stepView.selectedStep(nowPoint + 1);//当前状态
                }
            }
        } else if (detailType == 320 || detailType == 4) {//预算单
            if (viewHolder instanceof HeadViewHolder) {
                HeadViewHolder headViewHolder = (HeadViewHolder) viewHolder;
                //添加二维码,默认数据加载
                Bitmap mBitmap = QRCodeUtil.createQRCodeBitmap(budgetBean.getBudgId(), 480, 480);
                headViewHolder.ivCode.setImageBitmap(mBitmap);
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
                                if (dataBean.getReviewState().equals("通过")) {
                                    Glide.with(context).load(R.drawable.sppass).into(headViewHolder.ivHead);
                                    headViewHolder.state.setText("审批通过");
                                } else if (dataBean.getReviewState().equals("未通过")) {
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
                        if (dataBean.getReviewState().equals("未通过")) {
                            headViewHolder.state.setTextColor(context.getResources().getColor(R.color.red));
                            headViewHolder.state.setText("申请未通过");
                        }
                    }
                    if (budgetBean.getReviewList().size() > 0)
                        if (budgetBean.getReviewList().get(budgetBean.getReviewList().size() - 1).getReviewState().equals("通过")) {
                            headViewHolder.state.setTextColor(context.getResources().getColor(R.color.red));
                            headViewHolder.state.setText("申请已通过");
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
                int nowPoint = 0;//更改step状态位置
                boolean bol = false;//记录是否有未通过
                if (budgetBean.getReviewList() != null) {
                    for (int j = 0; j < budgetBean.getReviewList().size(); j++) {
                        System.out.println(budgetBean.getReviewList().get(j).toString());
                        if (!budgetBean.getReviewList().get(j).getReviewState().equals("等待审批")) {
                            if (budgetBean.getReviewList().get(j).getReviewState().equals("未通过")) {
                                bol = true;
                                temp.add(String.valueOf(budgetBean.getReviewList().get(j).getReviewUserName()) + budgetBean.getReviewList().get(j).getReviewState());
                            } else
                                temp.add(String.valueOf(budgetBean.getReviewList().get(j).getReviewUserName()));
                            nowPoint = 0;
                            nowPoint += j + 1;
                        } else
                            temp.add(String.valueOf(budgetBean.getReviewList().get(j).getReviewUserName()));
                    }
                }
                progressViewHolder.stepView.setSteps(temp);
                System.out.println("当前状态" + (nowPoint + 1));
                if (bol) {
                    progressViewHolder.stepView.selectedStep(nowPoint);//当前状态
                    progressViewHolder.disagreeLayout.setVisibility(View.VISIBLE);
                    progressViewHolder.tvDisagree.setTextColor(context.getResources().getColor(R.color.red));
                    progressViewHolder.tvDisagree.setText("拒绝理由");
                } else {
                    progressViewHolder.stepView.selectedStep(nowPoint + 1);//当前状态
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
        StepView stepView;
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

}
