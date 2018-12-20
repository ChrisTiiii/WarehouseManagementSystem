package com.example.administrator.warehousemanagementsystem.util;

import android.content.Context;

import com.afollestad.materialdialogs.MaterialDialog;

/**
 * author: ZhongMing
 * DATE: 2018/12/20 0020
 * Description:
 **/
public class MyDialog {
    private Context context;
    private Integer type;
    private MaterialDialog dialog;

    public MyDialog(Context context, Integer type) {
        this.context = context;
        this.type = type;
    }

    public void showDialog() {
        switch (type) {
            case 0://登录
                dialog = new MaterialDialog.Builder(context)
                        .title("正在登录")
                        .content("请稍后....")
                        .progress(true, 0)
                        .show();
                break;
            case 1://加载数据
                dialog = new MaterialDialog.Builder(context)
                        .title("数据加载中").content("请稍后....")
                        .progress(true, 0)
                        .show();
                break;
        }
    }

    public void dissDilalog() {
        dialog.dismiss();
    }
}
