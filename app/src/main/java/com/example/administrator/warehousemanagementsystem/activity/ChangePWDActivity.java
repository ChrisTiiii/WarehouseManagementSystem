package com.example.administrator.warehousemanagementsystem.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.administrator.warehousemanagementsystem.MyApp;
import com.example.administrator.warehousemanagementsystem.R;
import com.example.administrator.warehousemanagementsystem.net.NetServerImp;
import com.example.administrator.warehousemanagementsystem.util.MessageEvent;
import com.example.administrator.warehousemanagementsystem.util.MyDialog;
import com.example.administrator.warehousemanagementsystem.util.SharedPreferencesUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author: ZhongMing
 * DATE: 2019/1/3 0003
 * Description:用户更改密码
 **/
public class ChangePWDActivity extends AppCompatActivity {
    @BindView(R.id.et_old)
    TextInputEditText etOld;
    @BindView(R.id.et_new)
    TextInputEditText etNew;
    @BindView(R.id.et_new_again)
    TextInputEditText etNewAgain;
    @BindView(R.id.btn_commit)
    Button btnCommit;
    @BindView(R.id.btn_clear)
    Button btnClear;
    private NetServerImp netServerImp;
    private MyApp myApp;
    private MyDialog myDialog;

    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_pwd);
        ButterKnife.bind(this);
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
        myApp = (MyApp) getApplication();
        myDialog = new MyDialog(ChangePWDActivity.this, 2);
        netServerImp = new NetServerImp(myApp);

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void backData(MessageEvent messageEvent) {
        switch (messageEvent.getTag()) {
            case MyApp.CHANGE_SUCCESS:
                exitAccount();
                break;
        }
    }

    /**
     * 用户退出操作
     */
    public void exitAccount() {
        SharedPreferencesUtils helper = new SharedPreferencesUtils(ChangePWDActivity.this, "setting");
        helper.putValues(new SharedPreferencesUtils.ContentValue("password", ""));
        helper.putValues(new SharedPreferencesUtils.ContentValue("autoLogin", false));
        Intent intent = new Intent();
        intent.putExtra("type", 1);
        startActivity(new Intent(ChangePWDActivity.this, LoginActivity.class));
        finish();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().unregister(this);
    }

    @OnClick({R.id.btn_clear, R.id.btn_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_clear:
                etNewAgain.setText("");
                etNew.setText("");
                etOld.setText("");
                break;
            case R.id.btn_commit:
                String old = etOld.getText().toString();
                String newPwd = etNew.getText().toString();
                String newAgain = etNewAgain.getText().toString();
                if (old != null && newPwd != null && newAgain != null) {
                    if (newPwd.length() >= 6)
                        if (newPwd.equals(newAgain)) {
                            myDialog.showDialog();
                            netServerImp.changePass(old, newPwd, newAgain, myDialog);
                        } else Toast.makeText(myApp, "两次填写密码不一致，请重新输入", Toast.LENGTH_SHORT).show();
                    else Toast.makeText(myApp, "密码不能少于6位", Toast.LENGTH_SHORT).show();
                } else Toast.makeText(myApp, "您有未填项", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
