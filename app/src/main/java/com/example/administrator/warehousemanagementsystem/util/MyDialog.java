package com.example.administrator.warehousemanagementsystem.util;

import android.content.Context;
import android.text.InputType;

import com.afollestad.materialdialogs.MaterialDialog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author: ZhongMing
 * DATE: 2018/12/20 0020
 * Description:自定义dialog
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
            case 0://登录等待
                dialog = new MaterialDialog.Builder(context)
                        .title("正在登录")
                        .content("请稍后....")
                        .progress(true, 0)
                        .show();
                dialog.setCanceledOnTouchOutside(false);
                break;
            case 1://加载数据
                dialog = new MaterialDialog.Builder(context)
                        .title("数据加载中").content("请稍后....")
                        .progress(true, 0)
                        .show();
                dialog.setCanceledOnTouchOutside(false);
                break;
            case 2://提交请求
                dialog = new MaterialDialog.Builder(context)
                        .title("正在提交您的申请").content("请稍后....")
                        .progress(true, 0)
                        .show();
                dialog.setCanceledOnTouchOutside(false);
                break;

        }
    }

    public void dissDilalog() {
        dialog.dismiss();
    }
}
