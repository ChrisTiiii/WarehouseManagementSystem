package com.example.administrator.warehousemanagementsystem.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.warehousemanagementsystem.MyApp;
import com.example.administrator.warehousemanagementsystem.R;
import com.example.administrator.warehousemanagementsystem.adapter.other.ViewPagerAdapter;
import com.example.administrator.warehousemanagementsystem.fragment.mysp.SPFragment;
import com.example.administrator.warehousemanagementsystem.util.ViewPagerSlide;

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

@SuppressLint("ValidFragment")
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
    private MyApp myApp;

    public MySPFragment(MyApp myApp) {
        this.myApp = myApp;
    }

    public static MySPFragment newInstance(MyApp myApp) {
        Bundle args = new Bundle();
        MySPFragment fragment = new MySPFragment(myApp);
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
        uiList = new ArrayList<>();
        whitchRoot();
        viewPagerAdapter = new ViewPagerAdapter(getFragmentManager(), titleList);
        viewPagerAdapter.setList(uiList);
        spViewpager.setAdapter(viewPagerAdapter);
        spTab.setupWithViewPager(spViewpager);
        spTab.setTabMode(TabLayout.MODE_FIXED);
    }

    private void whitchRoot() {
        switch (myApp.getRoot()) {
            case 100://收费站管理员
                titleList.add("我的申领");
                titleList.add("我的预算");
                uiList.add(SPFragment.newInstance(2, myApp));
                uiList.add(SPFragment.newInstance(4, myApp));
                break;
            case 110://收费站站长
                titleList.add("待审批");
                titleList.add("已审批");
                titleList.add("我的申领");
                uiList.add(SPFragment.newInstance(0, myApp));
                uiList.add(SPFragment.newInstance(1, myApp));
                uiList.add(SPFragment.newInstance(2, myApp));
                break;
            case 120://仓库管理员
                titleList.add("我的采购");
                titleList.add("我的申领");
                uiList.add(SPFragment.newInstance(3, myApp));
                uiList.add(SPFragment.newInstance(2, myApp));
                break;
            case 130://主任
                titleList.add("待审批");
                titleList.add("已审批");
                titleList.add("申领");
                titleList.add("采购");
                titleList.add("预算");
                uiList.add(SPFragment.newInstance(0, myApp));
                uiList.add(SPFragment.newInstance(1, myApp));
                uiList.add(SPFragment.newInstance(2, myApp));
                uiList.add(SPFragment.newInstance(3, myApp));
                uiList.add(SPFragment.newInstance(4, myApp));
                break;
            case 140://处长
                titleList.add("待审批");
                titleList.add("已审批");
                titleList.add("申领");
                titleList.add("采购");
                titleList.add("预算");
                uiList.add(SPFragment.newInstance(0, myApp));
                uiList.add(SPFragment.newInstance(1, myApp));
                uiList.add(SPFragment.newInstance(2, myApp));
                uiList.add(SPFragment.newInstance(3, myApp));
                uiList.add(SPFragment.newInstance(4, myApp));
                break;
            default:
                titleList.add("待审批");
                titleList.add("已审批");
                titleList.add("申领");
                titleList.add("采购");
                titleList.add("预算");
                uiList.add(SPFragment.newInstance(0, myApp));
                uiList.add(SPFragment.newInstance(1, myApp));
                uiList.add(SPFragment.newInstance(2, myApp));
                uiList.add(SPFragment.newInstance(3, myApp));
                uiList.add(SPFragment.newInstance(4, myApp));
                break;
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
