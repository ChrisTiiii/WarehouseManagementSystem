package com.example.administrator.warehousemanagementsystem.adapter.mysp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.warehousemanagementsystem.MyApp;
import com.example.administrator.warehousemanagementsystem.R;
import com.example.administrator.warehousemanagementsystem.bean.ApplyList;
import com.example.administrator.warehousemanagementsystem.bean.BudgetList;
import com.example.administrator.warehousemanagementsystem.bean.PurchaseList;
import com.example.administrator.warehousemanagementsystem.bean.ReviewList;
import com.example.administrator.warehousemanagementsystem.bean.ReviewListHaveDone;
import com.example.administrator.warehousemanagementsystem.util.TimeUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author: ZhongMing
 * DATE: 2018/11/23 0023
 * Description: 审批 item 列表
 **/
public class SPAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private MyApp myApp;
    private List<ReviewList.DataBean> waitList;
    private List<ReviewListHaveDone.DataBean> doneList;
    private List<ApplyList.DataBean> myApplyList;
    private List<PurchaseList.DataBean> purchaseList;
    private List<BudgetList.DataBean> budgetList;
    private OnItemClickListener onItemClickListener;
    private int type;//0 为未审批 1 为已审批  2为我的申领 3为我的采购 4为我的预算


    public SPAdapter(Context context, MyApp myApp, List<ReviewList.DataBean> waitList, List<ReviewListHaveDone.DataBean> doneList, List<ApplyList.DataBean> myApplyList, List<PurchaseList.DataBean> purchaseList, List<BudgetList.DataBean> budgetList, int type) {
        this.context = context;
        this.myApp = myApp;
        this.waitList = waitList;
        this.doneList = doneList;
        this.myApplyList = myApplyList;
        this.purchaseList = purchaseList;
        this.budgetList = budgetList;
        this.type = type;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sp_item, viewGroup, false);
        return new SPViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof SPViewHolder) {
            SPViewHolder spViewHolder = (SPViewHolder) viewHolder;
            switch (type) {
                case 0:
                    Glide.with(context).load(R.drawable.wait).into(spViewHolder.ivHead);
                    spViewHolder.slProduct.setText("物品种类：" + waitList.get(i).getGoodsCount() + "个物资种类");
                    spViewHolder.slTime.setText(String.valueOf(TimeUtil.stampToDate(String.valueOf(waitList.get(i).getStartDate()))));
                    spViewHolder.slperson.setText("来自" + waitList.get(i).getFromUserName() + "的申请");
                    spViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onItemClickListener.onItemClick(v, i);
                        }
                    });
                    spViewHolder.state.setText(waitList.get(i).getReviewState());
                    spViewHolder.slType.setText(waitList.get(i).getReviewType() + "单");
                    break;
                case 1:
                    if (doneList.get(i).getReviewStateNo() == 601)
                        Glide.with(context).load(R.drawable.agree).into(spViewHolder.ivHead);
                    else if (doneList.get(i).getReviewStateNo() == 602)
                        Glide.with(context).load(R.drawable.disagree).into(spViewHolder.ivHead);
                    spViewHolder.slProduct.setText("物品种类：" + doneList.get(i).getGoodsCount() + "个物资种类");
                    spViewHolder.slTime.setText(TimeUtil.stampToDate(String.valueOf(doneList.get(i).getReviewDate())));
                    spViewHolder.slperson.setText("来自" + doneList.get(i).getFromUserName() + "的申请");
                    spViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onItemClickListener.onItemClick(v, i);
                        }
                    });
                    spViewHolder.state.setTextColor(context.getResources().getColor(R.color.red));
                    spViewHolder.state.setText(doneList.get(i).getReviewState());
                    spViewHolder.slType.setText(doneList.get(i).getReviewType() + "单");
                    break;
                case 2:
                    Glide.with(context).load(R.drawable.sh).into(spViewHolder.ivHead);
                    spViewHolder.slperson.setText(myApp.getUser().getUserName() + "的申请");
                    spViewHolder.slProduct.setText("物品用途：" + myApplyList.get(i).getApplyUsage());
                    spViewHolder.slTime.setText(String.valueOf(TimeUtil.stampToDate(String.valueOf(myApplyList.get(i).getApplyStartdate()))));
                    spViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onItemClickListener.onItemClick(v, i);
                        }
                    });
                    spViewHolder.state.setText(myApplyList.get(i).getApplyState());
                    spViewHolder.slType.setText("申领单");
                    break;
                case 3:
                    Glide.with(context).load(R.drawable.sh).into(spViewHolder.ivHead);
                    spViewHolder.slperson.setText(myApp.getUser().getUserName() + "的申请");
                    spViewHolder.slProduct.setText("物品用途：" + purchaseList.get(i).getPurcUsage());
                    spViewHolder.slTime.setText(String.valueOf(TimeUtil.stampToDate(String.valueOf(purchaseList.get(i).getPurcStartdate()))));
                    spViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onItemClickListener.onItemClick(v, i);
                        }
                    });
                    spViewHolder.state.setText(purchaseList.get(i).getPurcState());
                    spViewHolder.slType.setText("采购单");
                    break;
                case 4:
                    Glide.with(context).load(R.drawable.sh).into(spViewHolder.ivHead);
                    spViewHolder.slperson.setText(myApp.getUser().getUserName() + "的申请");
                    spViewHolder.slProduct.setText("物品用途：" + budgetList.get(i).getBudgUsage());
                    spViewHolder.slTime.setText(String.valueOf(TimeUtil.stampToDate(String.valueOf(budgetList.get(i).getBudgDate()))));
                    spViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onItemClickListener.onItemClick(v, i);
                        }
                    });
                    spViewHolder.state.setText(budgetList.get(i).getBudgState());
                    spViewHolder.slType.setText("预算单");
                    break;
            }
        }
    }


    @Override
    public int getItemCount() {
        switch (type) {
            case 0:
                return waitList.size();
            case 1:
                return doneList.size();
            case 2:
                return myApplyList.size();
            case 3:
                return purchaseList.size();
            case 4:
                return budgetList.size();
            default:
                return 0;
        }
    }


    static class SPViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_head)
        ImageView ivHead;
        @BindView(R.id.sl_slperson)
        TextView slperson;
        @BindView(R.id.now_time)
        TextView nowTime;
        @BindView(R.id.sl_product)
        TextView slProduct;
        @BindView(R.id.sl_time)
        TextView slTime;
        @BindView(R.id.state)
        TextView state;
        @BindView(R.id.no_data)
        LinearLayout noData;
        @BindView(R.id.have_data)
        LinearLayout haveData;
        @BindView(R.id.sl_type)
        TextView slType;

        SPViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

}
