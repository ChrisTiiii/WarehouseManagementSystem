package com.example.administrator.warehousemanagementsystem.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.warehousemanagementsystem.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.wangyuwei.particleview.ParticleView;

/**
 * author: ZhongMing
 * DATE: 2018/12/18 0018
 * Description:欢迎页
 **/
public class WelcomeActivity extends AppCompatActivity {
    @BindView(R.id.wel)
    ParticleView wel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.welcome);
        ButterKnife.bind(this);
        wel.startAnim();
        wel.setOnParticleAnimListener(new ParticleView.ParticleAnimListener() {
            @Override
            public void onAnimationEnd() {
                startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
                finish();
            }
        });

    }
}
