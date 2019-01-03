package com.example.administrator.warehousemanagementsystem.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.administrator.warehousemanagementsystem.R;
import com.example.administrator.warehousemanagementsystem.bean.MyGoods;
import com.example.administrator.warehousemanagementsystem.bean.MyLeader;
import com.example.administrator.warehousemanagementsystem.bean.ViewType;
import com.githang.stepview.StepView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author: ZhongMing
 * DATE: 2018/11/21 0021
 * Description:申领物资适配器
 **/
public class SLAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    public List<ViewType> uiList;//界面布局list
    private OnAddClickListener onAddClickListener;
    private OnItemClickListener onItemClickListener;
    private OnAddLeaderListener onAddLeaderListener;

    private final int HEAD_VIEW = 0;//物品用途
    private final int DETAIL_VIEW = 1;//物品明细
    private final int ADD_VIEW = 2;//添加
    private final int EXPLAIN_VIEW = 3;//领用详情
    private final int LEADER_VIEW = 4;//审批人

    List<MyGoods> goodsList;
    List<MyLeader> leaderList;
    private String caigou = "";
    private String explain = "";
    private String type = "";

    volatile boolean flag_goods = false;//判断是否重复物资
    volatile boolean flag_type = false;//判断是否选择不同类型物资

    public SLAdapter(Context context, List<ViewType> list) {
        this.context = context;
        this.uiList = list;
        goodsList = new ArrayList<>();
        leaderList = new ArrayList<>();
    }

    @Override
    public int getItemViewType(int position) {
        return uiList.get(position).getType();
    }

    //添加物品信息
    public void addData(int position, MyGoods myGoods) {
        flag_goods = false;
        flag_type = false;
        for (MyGoods bean : goodsList) {
            if (myGoods.getCode().equals(bean.getCode()))
                flag_goods = true;
            if (!myGoods.getGoodsTypeNo().equals(bean.getGoodsTypeNo()))
                flag_type = true;
        }
        int uiPosition = position - 1;//界面位置
        int listPosition = uiPosition - 1;//goodsList数据list
        if (uiPosition == 1) {
            goodsList.add(myGoods);
            uiList.add(uiPosition, new ViewType(DETAIL_VIEW));
        } else {
            if (myGoods != null)
                goodsList.add(listPosition, myGoods);
            uiList.add(uiPosition, new ViewType(DETAIL_VIEW));
        }
        notifyDataSetChanged();
    }

    //删除物品信息
    public void deleteData(int position) {
        goodsList.remove(position);
        if (position != 0) {
            uiList.remove(position);
        } else {
            uiList.remove(1);
        }
        notifyDataSetChanged();
        notifyItemRemoved(position);
    }

    //更新leader
    public void achieveLeader(MyLeader leader) {
        if (leaderList == null) {
            leaderList = new ArrayList<>();
            leaderList.add(leader);
        } else {
            if (leaderList.size() < 5)
                leaderList.add(leader);
            else Toast.makeText(context, "审批人最多选五个", Toast.LENGTH_SHORT).show();
        }
        notifyDataSetChanged();

    }

    //删除leader
    public void deleteLeader() {
        if (leaderList != null)
            if (leaderList.size() > 0) {
                leaderList.remove(leaderList.size() - 1);
                notifyDataSetChanged();
            } else
                notifyDataSetChanged();
    }


    //提交成功后初始化适配器,避免数据重复
    public void initView() {
        goodsList.clear();
        leaderList.clear();
        uiList.clear();
        caigou = "";
        explain = "";
        uiList.add(new ViewType(ViewType.SL_TYPE_HEAD));
        uiList.add(new ViewType(ViewType.SL_TYPE_ADD));
        uiList.add(new ViewType(ViewType.SL_TYPE_EXPLAIN));
        uiList.add(new ViewType(ViewType.SL_TYPE_LEADER));
        notifyDataSetChanged();
    }

    //判断是否为重复添加项
    public boolean isReGoods() {
        return flag_goods;
    }

    //判断是否为不同种类的物资
    public boolean isReGoodsType() {
        return flag_type;
    }

    public String getType() {
        return type;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view;
        switch (viewType) {
            case HEAD_VIEW:
                view = LayoutInflater.from(context).inflate(R.layout.sl_head, null);
                return new HeadViewHolder(view);
            case DETAIL_VIEW:
                view = LayoutInflater.from(context).inflate(R.layout.sl_detail, null);
                return new DetailViewHolder(view);
            case ADD_VIEW:
                view = LayoutInflater.from(context).inflate(R.layout.sl_add, null);
                return new AddViewHolder(view);
            case EXPLAIN_VIEW:
                view = LayoutInflater.from(context).inflate(R.layout.sl_explain, null);
                return new ExplainViewHolder(view);
            case LEADER_VIEW:
                view = LayoutInflater.from(context).inflate(R.layout.sl_spperson, null);
                return new LeaderViewHolder(view);
            default:
                return null;
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int position) {
        //物品用途
        if (viewHolder instanceof HeadViewHolder) {
            HeadViewHolder headViewHolder = (HeadViewHolder) viewHolder;
            if (headViewHolder.etUse.getTag() instanceof TextWatcher)
                headViewHolder.etUse.removeTextChangedListener((TextWatcher) headViewHolder.etUse.getTag());
            headViewHolder.etUse.setText(caigou);
            TextWatcher headWatcher = new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }

                @Override
                public void afterTextChanged(Editable s) {
                    caigou = "";
                    caigou += s.toString();
                    caigou.replaceAll(caigou, s.toString());
                }
            };
            headViewHolder.etUse.addTextChangedListener(headWatcher);
            headViewHolder.etUse.setTag(headWatcher);
            headViewHolder.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (position != 1)
                        headViewHolder.caigou.setVisibility(View.GONE);
                    else headViewHolder.caigou.setVisibility(View.VISIBLE);
                    notifyDataSetChanged();
                    type = parent.getItemAtPosition(position).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }

        //领用详情
        if (viewHolder instanceof ExplainViewHolder) {
            ExplainViewHolder explainViewHolder = (ExplainViewHolder) viewHolder;
            if (explainViewHolder.etExplain.getTag() instanceof TextWatcher)
                explainViewHolder.etExplain.removeTextChangedListener((TextWatcher) explainViewHolder.etExplain.getTag());
            explainViewHolder.etExplain.setText(explain);
            TextWatcher explainWatcher = new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }

                @Override
                public void afterTextChanged(Editable s) {
                    explain = "";
                    explain += s.toString();
                    explain.replaceAll(explain, s.toString());
                }
            };
            explainViewHolder.etExplain.addTextChangedListener(explainWatcher);
            explainViewHolder.etExplain.setTag(explainWatcher);

        }

        //物品明细
        if (viewHolder instanceof DetailViewHolder) {
            DetailViewHolder detailViewHolder = (DetailViewHolder) viewHolder;
            detailViewHolder.tvDetailId.setText("物品明细(" + position + ")");
            if (detailViewHolder.etName.getTag() instanceof TextWatcher)
                detailViewHolder.etName.removeTextChangedListener((TextWatcher) detailViewHolder.etName.getTag());
            if (detailViewHolder.etNum.getTag() instanceof TextWatcher)
                detailViewHolder.etNum.removeTextChangedListener((TextWatcher) detailViewHolder.etNum.getTag());
            if (position - 1 >= 0 && goodsList.size() > 0) {
                detailViewHolder.etName.setText(goodsList.get(position - 1).getName());
                detailViewHolder.etNum.setText(goodsList.get(position - 1).getNum());
            }

            TextWatcher numWatcher = new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (goodsList.get(position - 1).getNum() != null) {
                        goodsList.remove(position - 1);
                    }
                    goodsList.add(position - 1, new MyGoods(goodsList.get(position - 1).getCode(), goodsList.get(position - 1).getName(), s.toString()));
                }
            };
            detailViewHolder.etNum.addTextChangedListener(numWatcher);
            detailViewHolder.etNum.setTag(numWatcher);
        }

        //审批人
        if (viewHolder instanceof LeaderViewHolder) {
            LeaderViewHolder leaderViewHolder = (LeaderViewHolder) viewHolder;
            if (leaderList != null) {
                leaderViewHolder.ivDelete.setVisibility(View.VISIBLE);
                leaderViewHolder.stepView.setSteps(stepLeader());
                leaderViewHolder.stepView.selectedStep(leaderList.size());
            } else {
                leaderViewHolder.ivDelete.setVisibility(View.GONE);
            }
        }

    }

    public List<String> stepLeader() {
        List<String> list = new ArrayList<>();
        for (MyLeader leader : leaderList) {
            list.add(leader.getName());
        }
        return list;
    }

    public String getExplain() {
        return !explain.equals("") ? explain : "暂无备注";
    }

    public String getCaigou() {
        return !caigou.equals("") ? caigou : "暂无采购商";
    }


    public List<MyGoods> getGoodsList() {
        return goodsList.size() != 0 ? goodsList : null;
    }


    public List<MyLeader> getLeaderList() {
        return leaderList.size() != 0 ? leaderList : null;
    }

    @Override
    public int getItemCount() {
        return uiList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class HeadViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.et_use)
        EditText etUse;
        @BindView(R.id.spinner2)
        Spinner spinner;
        @BindView(R.id.caigou)
        RelativeLayout caigou;

        HeadViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    class DetailViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.tv_detail_id)
        TextView tvDetailId;
        @BindView(R.id.btn_del)
        Button btnDel;
        @BindView(R.id.et_detail_name)
        TextView etName;
        @BindView(R.id.et_num)
        TextView etNum;

        DetailViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            btnDel.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(v, getAdapterPosition());
            }
        }

    }

    class AddViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.btn_add)
        Button btnAdd;

        AddViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            btnAdd.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (onAddClickListener != null) {
                onAddClickListener.onAddClick(v, getAdapterPosition());
            }
        }
    }

    class ExplainViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.et_explain)
        EditText etExplain;

        ExplainViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

    }

    class LeaderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.btn_add)
        Button btnAdd;
        @BindView(R.id.step_view)
        StepView stepView;
        @BindView(R.id.iv_delete)
        ImageView ivDelete;

        LeaderViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            btnAdd.setOnClickListener(this);
            ivDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteLeader();
                }
            });
        }

        @Override
        public void onClick(View v) {
            if (onAddLeaderListener != null)
                onAddLeaderListener.onAddLeader(v, getAdapterPosition());
        }
    }

    public interface OnAddClickListener {
        void onAddClick(View view, int position);
    }

    public void setOnAddClickListener(OnAddClickListener onAddClickListener) {
        this.onAddClickListener = onAddClickListener;
    }

    //定义一个回调接口来实现Click
    public interface OnItemClickListener {
        void onItemClick(View view, int position);

    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    //添加审批人回调接口
    public interface OnAddLeaderListener {
        void onAddLeader(View view, int position);
    }

    public void setOnAddLeaderListener(OnAddLeaderListener onAddLeaderListener) {
        this.onAddLeaderListener = onAddLeaderListener;
    }


}
