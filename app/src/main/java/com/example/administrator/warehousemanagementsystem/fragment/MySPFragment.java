package com.example.administrator.warehousemanagementsystem.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.warehousemanagementsystem.R;
import com.example.administrator.warehousemanagementsystem.adapter.other.ViewPagerAdapter;
import com.example.administrator.warehousemanagementsystem.fragment.mysp.SPFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * author: ZhongMing
 * DATE: 2018/11/23 0023
 * Description: 我审批的总界面
 **/


public class MySPFragment extends Fragment {
    private View rootView;
    @BindView(R.id.main_tab)
    TabLayout spTab;
    @BindView(R.id.main_viewpager)
    ViewPager spViewpager;
    Unbinder unbinder;
    private List<String> titleList;//标题
    private List<Fragment> uiList;
    private ViewPagerAdapter viewPagerAdapter;

    public static MySPFragment newInstance() {
        Bundle args = new Bundle();
        MySPFragment fragment = new MySPFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (null != rootView) {
            ViewGroup parent = (ViewGroup) rootView.getParent();
            unbinder = ButterKnife.bind(this, rootView);
            if (null != parent) {
                parent.removeView(rootView);
            }
        } else {
            rootView = inflater.inflate(R.layout.my_sp, null);
            unbinder = ButterKnife.bind(this, rootView);
            initView();
        }
        return rootView;
    }


    //初始化我的审批界面
    private void initView() {
        titleList = new ArrayList<>();
        titleList.add("待审批");
        titleList.add("已审批");
        viewPagerAdapter = new ViewPagerAdapter(getFragmentManager(), titleList);
        uiList = new ArrayList<>();
        uiList.add(SPFragment.newInstance(0));
        uiList.add(SPFragment.newInstance(1));
        viewPagerAdapter.setList(uiList);
        spViewpager.setAdapter(viewPagerAdapter);
        spTab.setupWithViewPager(spViewpager);
        spTab.setTabMode(TabLayout.MODE_FIXED);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
