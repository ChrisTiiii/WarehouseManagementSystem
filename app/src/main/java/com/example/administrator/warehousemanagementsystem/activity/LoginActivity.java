package com.example.administrator.warehousemanagementsystem.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.administrator.warehousemanagementsystem.MainActivity;
import com.example.administrator.warehousemanagementsystem.MyApp;
import com.example.administrator.warehousemanagementsystem.R;
import com.example.administrator.warehousemanagementsystem.net.NetServerImp;
import com.example.administrator.warehousemanagementsystem.util.MessageEvent;
import com.example.administrator.warehousemanagementsystem.util.MyDialog;
import com.example.administrator.warehousemanagementsystem.util.NotificationUtil;
import com.example.administrator.warehousemanagementsystem.util.SharedPreferencesUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author: ZhongMing
 * DATE: 2018/11/29 0029
 * Description:用户登录页面
 * 需根据用户权限显示不同页面
 **/
public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.et_name)
    EditText et_name;
    @BindView(R.id.et_pwd)
    EditText et_password;
    @BindView(R.id.login)
    Button login;
    @BindView(R.id.check_pwd)
    CheckBox checkBox_password;
    MyDialog myDialog;
    @BindView(R.id.pwdclick)
    ImageView pwdclick;
    private SharedPreferencesUtils helper;
    private NetServerImp netServerImp;
    MyApp myApp;


    private static boolean canSee = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.login_activity);
        ButterKnife.bind(this);
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
        myApp = (MyApp) getApplication();
        initData();
        NotificationUtil.getAppDetailSettingIntent(LoginActivity.this);
    }


    private void initData() {
        //判断用户第一次登陆
        if (firstLogin()) {
            checkBox_password.setChecked(false);//取消记住密码的复选框
        }
        //判断是否记住密码
        if (remenberPassword()) {
            checkBox_password.setChecked(true);//勾选记住密码
            setTextNameAndPassword();//把密码和账号输入到输入框中
        } else {
            setTextName();//把用户账号放到输入账号的输入框中
        }
        //判断是否登录过
        if (autoLogin()) {
            login();//去登录就可以
        }
    }

    /**
     * 把本地保存的数据设置数据到输入框中
     */
    public void setTextNameAndPassword() {
        et_name.setText("" + getLocalName());
        et_password.setText("" + getLocalPassword());
    }

    /**
     * 设置数据到输入框中
     */
    public void setTextName() {
        et_name.setText("" + getLocalName());
    }


    /**
     * 获得保存在本地的用户名
     */
    public String getLocalName() {
        //获取SharedPreferences对象，使用自定义类的方法来获取对象
        helper = new SharedPreferencesUtils(this, "setting");
        String name = helper.getString("name");
        return name;
    }


    /**
     * 获得保存在本地的密码
     */
    public String getLocalPassword() {
        //获取SharedPreferences对象，使用自定义类的方法来获取对象
        helper = new SharedPreferencesUtils(this, "setting");
        String password = helper.getString("password");
//        return Base64Utils.encodeBase64(password);   //解码一下
        return password;   //解码一下

    }


    /**
     * 判断是否自动登录
     */
    private boolean autoLogin() {
        //获取SharedPreferences对象，使用自定义类的方法来获取对象
        helper = new SharedPreferencesUtils(this, "setting");
        boolean autoLogin = helper.getBoolean("autoLogin", false);
        return autoLogin;
    }

    /**
     * 判断是否记住密码
     */
    private boolean remenberPassword() {
        //获取SharedPreferences对象，使用自定义类的方法来获取对象
        helper = new SharedPreferencesUtils(this, "setting");
        boolean remenberPassword = helper.getBoolean("remenberPassword", false);
        return remenberPassword;
    }

    /**
     * 判断是否是第一次登陆
     */
    private boolean firstLogin() {
        //获取SharedPreferences对象，使用自定义类的方法来获取对象
        helper = new SharedPreferencesUtils(this, "setting");
        boolean first = helper.getBoolean("first", true);
        if (first) {
            //创建一个ContentVa对象（自定义的）设置不是第一次登录，,并创建记住密码和自动登录是默认不选，创建账号和密码为空
            helper.putValues(new SharedPreferencesUtils.ContentValue("first", false),
                    new SharedPreferencesUtils.ContentValue("remenberPassword", false),
                    new SharedPreferencesUtils.ContentValue("autoLogin", false),
                    new SharedPreferencesUtils.ContentValue("name", ""),
                    new SharedPreferencesUtils.ContentValue("password", ""));
            return true;
        }
        return false;
    }

    /**
     * 实现网络验证登陆
     */
    private void login() {
        myApp.initUser();
        //先做一些基本的判断，比如输入的用户命为空，密码为空，网络不可用多大情况，都不需要去链接服务器了，而是直接返回提示错误
        if (getAccount().isEmpty()) {
            showToast("你输入的账号为空！");
            return;
        }
        if (getPassword().isEmpty()) {
            showToast("你输入的密码为空！");
            return;
        }
        //登录一般都是请求服务器来判断密码是否正确，要请求网络，要子线程
        showLoading();//显示加载框
        //网络请求
        netServerImp = new NetServerImp(myApp);
        netServerImp.getUser(getAccount(), getPassword(), myDialog);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void intentLogin(MessageEvent messageEvent) {
        switch (messageEvent.getTag()) {
            case MyApp.LOGIN_SUCCESS:
                if (messageEvent.getMessage() != "") {
                    showToast(messageEvent.getMessage());
                } else {
                    if (myApp.getUser() != null) {
                        //获取SharedPreferences对象，使用自定义类的方法来获取对象
                        helper = new SharedPreferencesUtils(this, "setting");
                        helper.putValues(new SharedPreferencesUtils.ContentValue("autoLogin", true));
                        showToast("登录成功");
                        loadCheckBoxState();//记录下当前用户记住密码和自动登录的状态;
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();//关闭页面
                    } else {
                        showToast("登录失败");
                    }
                }
                break;
            case MyApp.LOGIN_FAIL:
                showToast("请检查你的网络是否连接正常");
                break;
        }
    }


    /**
     * 保存用户账号
     */
    public void loadUserName() {
        if (!getAccount().equals("") || !getAccount().equals("请输入登录账号")) {
            helper = new SharedPreferencesUtils(this, "setting");
            helper.putValues(new SharedPreferencesUtils.ContentValue("name", getAccount()));
        }
    }

    /**
     * 获取账号
     */
    public String getAccount() {
        return et_name.getText().toString().trim();//去掉空格
    }

    /**
     * 获取密码
     */
    public String getPassword() {
        return et_password.getText().toString().trim();//去掉空格
    }

    /**
     * 保存用户选择“记住密码”和“自动登陆”的状态
     */
    private void loadCheckBoxState() {
        loadCheckBoxState(checkBox_password);
    }

    /**
     * 保存按钮的状态值
     */
    public void loadCheckBoxState(CheckBox checkBox_password) {
        //获取SharedPreferences对象，使用自定义类的方法来获取对象
        helper = new SharedPreferencesUtils(this, "setting");
        if (!checkBox_password.isChecked()) { //如果没有保存密码，那么自动登录也是不选的
            //创建记住密码和自动登录是默认不选,密码为空
            helper.putValues(
                    new SharedPreferencesUtils.ContentValue("remenberPassword", false),
                    new SharedPreferencesUtils.ContentValue("password", ""));
        } else if (checkBox_password.isChecked()) {   //如果保存密码，没有自动登录
            //创建记住密码为选中和自动登录是默认不选,保存密码数据
            helper.putValues(
                    new SharedPreferencesUtils.ContentValue("remenberPassword", true),
                    new SharedPreferencesUtils.ContentValue("password", getPassword()));
        }
    }


    /**
     * 显示加载的进度款
     */
    public void showLoading() {
        myDialog = new MyDialog(LoginActivity.this, 0);
        myDialog.showDialog();
    }


    public void showToast(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });

    }

    @OnClick({R.id.check_pwd, R.id.check_login, R.id.login, R.id.pwdclick})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.check_pwd:
                break;
            case R.id.check_login:
                break;
            case R.id.login:
                loadUserName();    //无论如何保存一下用户名
                login(); //登陆
                break;
            case R.id.pwdclick:
                //通过全局的一个变量的设置，这个就是判断控件里面的内容是不是能被看到
                if (canSee == false) {
                    //如果是不能看到密码的情况下，
                    et_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    canSee = true;
                } else {
                    //如果是能看到密码的状态下
                    et_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    canSee = false;
                }
                break;
        }
    }


    @Override
    protected void onDestroy() {
        if (myDialog != null)
            myDialog.dissDilalog();
        if (EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
