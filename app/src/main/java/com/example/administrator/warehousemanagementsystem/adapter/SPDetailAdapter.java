package com.example.administrator.warehousemanagementsystem.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.warehousemanagementsystem.MyApp;
import com.example.administrator.warehousemanagementsystem.R;
import com.example.administrator.warehousemanagementsystem.bean.SPDetailBean;
import com.example.administrator.warehousemanagementsystem.bean.ViewType;
import com.example.administrator.warehousemanagementsystem.util.QRCodeUtil;
import com.githang.stepview.StepView;

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

    private int viewType;
    public List<ViewType> uiList;//界面布局list
    public SPDetailBean details;//具体数据list
    public OnCodeClickListener onCodeClickListener;

    public SPDetailAdapter(Context context, List<ViewType> uiList, SPDetailBean details, int viewType) {
        this.context = context;
        this.uiList = uiList;
        this.details = details;
        this.viewType = viewType;
    }

    @Override
    public int getItemViewType(int position) {
        return uiList.get(position).getType();
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view;
        switch (viewType) {
            case SP_HEAD:
                view = LayoutInflater.from(context).inflate(R.layout.sp_item_head, null);
                return new HeadViewHolder(view);
            case SP_DETAIL:
                view = LayoutInflater.from(context).inflate(R.layout.sp_item_detail, null);
                return new DetailViewHolder(view);
            case SP_EXPLAIN:
                view = LayoutInflater.from(context).inflate(R.layout.sp_item_explain, null);
                return new ExplainViewHolder(view);
            case SP_PROGRESS:
                view = LayoutInflater.from(context).inflate(R.layout.sp_item_progress, null);
                return new ProgressViewHolder(view);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof HeadViewHolder) {
            HeadViewHolder headViewHolder = (HeadViewHolder) viewHolder;
            if (viewType == 0) {
                headViewHolder.ivCode.setVisibility(View.GONE);
                Glide.with(context).load(R.drawable.spwait).into(headViewHolder.ivHead);
                headViewHolder.state.setText("待审批");
            }
            if (viewType == 1) {
                if (details.getState() == 1) {
                    headViewHolder.ivCode.setVisibility(View.VISIBLE);
                    Glide.with(context).load(R.drawable.sppass).into(headViewHolder.ivHead);
                    headViewHolder.state.setText("审批完成");
                    //添加二维码
                    Bitmap mBitmap = QRCodeUtil.createQRCodeBitmap(details.getSpBH(), 480, 480);
                    headViewHolder.ivCode.setImageBitmap(mBitmap);
                } else if (details.getState() == 2) {
                    headViewHolder.ivCode.setVisibility(View.GONE);
                    Glide.with(context).load(R.drawable.disagree).into(headViewHolder.ivHead);
                    headViewHolder.state.setText("审批未通过");
                }
                headViewHolder.state.setTextColor(context.getResources().getColor(R.color.colorAccent));
            }
            headViewHolder.spPerson.setText(details.getSpName());
            headViewHolder.spBh.setText(details.getSpBH());
            headViewHolder.tvSpBm.setText(details.getSpBM());
            headViewHolder.tvSpUsefor.setText(details.getSpUse());
        }

        if (viewHolder instanceof DetailViewHolder) {
            DetailViewHolder detailViewHolder = (DetailViewHolder) viewHolder;
            detailViewHolder.tvDetailId.setText(i + "");
            for (int j = 0; j < details.getSpmx().size(); j++) {
                detailViewHolder.tvDetailName.setText(details.getSpmx().get(j).getName());
                detailViewHolder.tvNum.setText(details.getSpmx().get(j).getNum());
            }
        }
        if (viewHolder instanceof ExplainViewHolder) {
            ExplainViewHolder explainViewHolder = (ExplainViewHolder) viewHolder;
            explainViewHolder.tvExplain.setText(details.getExplain());
        }

        if (viewHolder instanceof ProgressViewHolder) {
            ProgressViewHolder progressViewHolder = (ProgressViewHolder) viewHolder;
            progressViewHolder.stepView.setSteps(details.getProgressList());
            progressViewHolder.stepView.selectedStep(details.getNowPoint());
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
