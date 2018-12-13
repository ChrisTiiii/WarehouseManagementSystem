package com.example.administrator.warehousemanagementsystem.activity.other;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.administrator.warehousemanagementsystem.R;
import com.example.administrator.warehousemanagementsystem.util.QRCodeUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author: ZhongMing
 * DATE: 2018/12/5 0005
 * Description:二维码界面
 **/
public class Code extends AppCompatActivity {
    @BindView(R.id.code)
    ImageView code;
    private String bhCode = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.large_code);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        bhCode = intent.getExtras().getString("bh");
        Bitmap mBitmap = QRCodeUtil.createQRCodeBitmap(bhCode, 480, 480);
        code.setImageBitmap(mBitmap);
    }

    @OnClick(R.id.code)
    public void onViewClicked() {
        ActivityCompat.finishAfterTransition(Code.this);
    }
}
