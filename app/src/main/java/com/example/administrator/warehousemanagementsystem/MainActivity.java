package com.example.administrator.warehousemanagementsystem;

import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.example.administrator.warehousemanagementsystem.fragment.ReportFragment;
import com.example.administrator.warehousemanagementsystem.fragment.MySPFragment;
import com.example.administrator.warehousemanagementsystem.fragment.PersonFragment;
import com.example.administrator.warehousemanagementsystem.fragment.SLFragment;
import com.example.administrator.warehousemanagementsystem.util.BottomNavigationViewHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.bottomNavigationView)
    BottomNavigationView bottomNavigationView;
    @BindView(R.id.content)
    FrameLayout content;
    List<Fragment> listFragment;

    private int lastfragment;//用于记录上个选择的Fragmenet
    MyApp myApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        myApp = (MyApp) getApplication();
        initView();
        initNavigation();
    }

    private void initView() {
        listFragment = new ArrayList<>();
        System.out.println("root:" + myApp.getRoot());
        if (myApp.getRoot() != -1)
            if (myApp.getRoot() == 110 || myApp.getRoot() == 130) {
                listFragment.add(MySPFragment.newInstance());
                listFragment.add(new SLFragment(myApp));
                listFragment.add(new PersonFragment(myApp));
            } else {
                listFragment.add(MySPFragment.newInstance());
                listFragment.add(new SLFragment(myApp));
                listFragment.add(ReportFragment.newInstance());
                listFragment.add(new PersonFragment(myApp));
            }


    }

    //初始化底部导航栏
    private void initNavigation() {
        lastfragment = 0;
        switchFragment(lastfragment, 0);
        if (myApp.getRoot() != -1)
            if (myApp.getRoot() == 110 || myApp.getRoot() == 130) {
                bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.sp_sp:
                                if (lastfragment != 0) {
                                    switchFragment(lastfragment, 0);
                                    lastfragment = 0;
                                }
                                return true;
                            case R.id.sp_send:
                                if (lastfragment != 1) {
                                    switchFragment(lastfragment, 1);
                                    lastfragment = 1;
                                }
                                return true;
                            case R.id.person:
                                if (lastfragment != 2) {
                                    switchFragment(lastfragment, 2);
                                    lastfragment = 2;
                                }
                                return true;
                        }
                        return false;
                    }
                });
            } else {
                bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.sp_sp:
                                if (lastfragment != 0) {
                                    switchFragment(lastfragment, 0);
                                    lastfragment = 0;
                                }
                                return true;
                            case R.id.sp_send:
                                if (lastfragment != 1) {
                                    switchFragment(lastfragment, 1);
                                    lastfragment = 1;
                                }
                                return true;
                            case R.id.sp_statement:
                                if (lastfragment != 2) {
                                    switchFragment(lastfragment, 2);
                                    lastfragment = 2;
                                }
                                return true;
                            case R.id.person:
                                if (lastfragment != 3) {
                                    switchFragment(lastfragment, 3);
                                    lastfragment = 3;
                                }
                                return true;
                        }
                        return false;
                    }
                });
            }
    }

    private void switchFragment(int lastfragment, int index) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.hide(listFragment.get(lastfragment));
        if (listFragment.get(index).isAdded() == false)
            transaction.add(R.id.content, listFragment.get(index));
        transaction.show(listFragment.get(index)).commitAllowingStateLoss();

    }


}
