package com.example.administrator.warehousemanagementsystem.adapter.mysp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.warehousemanagementsystem.R;
import com.example.administrator.warehousemanagementsystem.bean.ReviewList;
import com.example.administrator.warehousemanagementsystem.bean.SPDetailBean;
import com.example.administrator.warehousemanagementsystem.util.TimeUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author: ZhongMing
 * DATE: 2018/11/23 0023
 * Description: 审批 item 列表
 **/
public class SPAdapter extends RecyclerView.Adapter<SPAdapter.SPViewHolder> {

    private Context context;
    private List<ReviewList.DataBean> waitList;
    private List<SPDetailBean> doneList;
    private OnItemClickListener onItemClickListener;
    private int type;//0 为未审批 1 为已审批  2为已撤销

    public SPAdapter(Context context, List<ReviewList.DataBean> waitList, List<SPDetailBean> doneList, int type) {
        this.context = context;
        this.waitList = waitList;
        this.doneList = doneList;
        this.type = type;
    }


    @NonNull
    @Override
    public SPViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.sp_item, null);
        return new SPViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SPViewHolder spViewHolder, final int i) {
        if (type == 0) {
            Glide.with(context).load(R.drawable.wait).into(spViewHolder.ivHead);
//            spViewHolder.nowTime.setText((Integer) waitList.get(i).getReviewDate());
            spViewHolder.slProduct.setText(waitList.get(i).getGoodsCount() + "个物资种类数");
            spViewHolder.slTime.setText(String.valueOf(TimeUtil.stampToDate(String.valueOf(waitList.get(i).getStartDate()))));
            spViewHolder.slperson.setText("来自" + waitList.get(i).getFromUserName() + "的申请");
            spViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(v, i);
                }
            });
            spViewHolder.state.setText(waitList.get(i).getReviewState());
        } else {
            if (doneList.get(i).getState().equals(1))
                Glide.with(context).load(R.drawable.agree).into(spViewHolder.ivHead);
            else if (doneList.get(i).getState().equals(2))
                Glide.with(context).load(R.drawable.disagree).into(spViewHolder.ivHead);
            spViewHolder.nowTime.setText(doneList.get(i).getNowTime());
            spViewHolder.slProduct.setText(doneList.get(i).getSpmx().get(0).getName() + "等");
            spViewHolder.slTime.setText(doneList.get(i).getSlTime());
            spViewHolder.slperson.setText("来自" + doneList.get(i).getSpName() + "的申请");
            spViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(v, i);
                }
            });
            spViewHolder.state.setTextColor(context.getResources().getColor(R.color.red));
            spViewHolder.state.setText("已审批");
        }

    }


    @Override
    public int getItemCount() {
        return type == 0 ? waitList.size() : doneList.size();
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
