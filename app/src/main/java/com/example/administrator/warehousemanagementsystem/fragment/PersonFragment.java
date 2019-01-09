package com.example.administrator.warehousemanagementsystem.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.administrator.warehousemanagementsystem.MyApp;
import com.example.administrator.warehousemanagementsystem.R;
import com.example.administrator.warehousemanagementsystem.activity.ChangePWDActivity;
import com.example.administrator.warehousemanagementsystem.activity.LoginActivity;
import com.example.administrator.warehousemanagementsystem.util.SharedPreferencesUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.jpush.android.api.JPushInterface;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * author: ZhongMing
 * DATE: 2018/11/28 0028
 * Description:
 **/

@SuppressLint("ValidFragment")
public class PersonFragment extends Fragment {
    @BindView(R.id.person_back)
    ImageView personBack;
    @BindView(R.id.person_head)
    ImageView personHead;
    @BindView(R.id.user_name)
    TextView userName;
    @BindView(R.id.user_val)
    TextView userVal;
    Unbinder unbinder;
    @BindView(R.id.banben)
    Button banben;
    @BindView(R.id.exit)
    Button exit;
    MyApp myApp;
    @BindView(R.id.change_password)
    Button changePassword;


    @SuppressLint("ValidFragment")
    public PersonFragment(MyApp myApp) {
        this.myApp = myApp;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.person, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();

        SharedPreferencesUtils helper = new SharedPreferencesUtils(getContext(), "setting");
        System.out.println("success:" + helper.getBoolean("autoLogin", false));
        return view;

    }

    private void initView() {

        userName.setText(myApp.user.getUserName());
        userVal.setText(myApp.user.getUserId());

        Glide.with(getContext()).load(R.drawable.person_back).into(personBack);
//        Glide.with(getContext()).load(R.drawable.person_back)
//                .bitmapTransform(new BlurTransformation(getContext(), 25), new CenterCrop(getContext()))
//                .into(personBack);

        Glide.with(getContext()).load(R.drawable.name_head)
                .bitmapTransform(new CropCircleTransformation(getContext()))
                .into(personHead);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.banben, R.id.exit, R.id.change_password})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.banben:
                Toast.makeText(getContext(), "您已下载最新版本", Toast.LENGTH_SHORT).show();
                break;
            case R.id.exit:
                exitAccount();
                break;
            case R.id.change_password:
                startActivity(new Intent(getContext(), ChangePWDActivity.class));
                break;
        }
    }

    public void exitAccount() {
        // 退出登录删除别名
        JPushInterface.deleteAlias(getContext(), 1);
        SharedPreferencesUtils helper = new SharedPreferencesUtils(getContext(), "setting");
        helper.putValues(new SharedPreferencesUtils.ContentValue("password", ""));
        helper.putValues(new SharedPreferencesUtils.ContentValue("autoLogin", false));
        Intent intent = new Intent();
        intent.putExtra("type", 1);
        startActivity(new Intent(getActivity(), LoginActivity.class));
        getActivity().finish();
    }

}
