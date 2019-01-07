package com.example.administrator.warehousemanagementsystem.util;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.List;

/**
 * author: ZhongMing
 * DATE: 2019/1/4 0004
 * Description:自定义柱状图X轴
 **/
public class MyXFormatter implements IAxisValueFormatter {
    private List<String> mValues;

    public MyXFormatter(List<String> mValues) {
        this.mValues = mValues;
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        if (((int) value >= 0 && (int) value < mValues.size()))
            return mValues.get((int) value);
        else
            return "";
    }

}

