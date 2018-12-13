package com.example.administrator.warehousemanagementsystem.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.warehousemanagementsystem.R;
import com.example.administrator.warehousemanagementsystem.bean.MenuListBean;

import java.util.List;


/**
 * author: ZhongMing
 * DATE: 2018/11/23 0023
 * Description:选择物品List 快捷适配器
 **/
public class ProductListAdapter extends BaseQuickAdapter<MenuListBean, BaseViewHolder> {


    private final Context context;
    private final List<MenuListBean> data;

    public ProductListAdapter(Context context, @Nullable List<MenuListBean> data) {
        super(R.layout.menu_item, data);
        this.context = context;
        this.data = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, MenuListBean item) {
        helper.setText(R.id.item_recyclerview_tv, (CharSequence) item.getMap().get("name"))
                .setBackgroundColor(R.id.item_recyclerview_ll, item.isSelect() ? Color.LTGRAY : Color.WHITE);
    }
}