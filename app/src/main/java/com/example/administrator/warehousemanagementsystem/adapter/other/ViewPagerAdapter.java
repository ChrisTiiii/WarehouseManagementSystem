package com.example.administrator.warehousemanagementsystem.adapter.other;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * author: ZhongMing
 * DATE: 2018/11/23 0023
 * Description:ViewPager适配器
 **/
public class ViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> list;
    private List<String> titlelList;

    public ViewPagerAdapter(FragmentManager fm, List<String> titlelList) {
        super(fm);
        this.titlelList = titlelList;
    }

    public void setList(List<Fragment> list) {
        this.list = list;
        notifyDataSetChanged();
    }


    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list != null ? list.size() : 0;
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titlelList.get(position);
    }
}
