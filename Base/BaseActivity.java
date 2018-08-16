package com.flytoyou.baseapplication.Base;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.flytoyou.baseapplication.Utils.AppUtils;
import com.google.gson.Gson;

/**
 * Activity基类
 * @author flytoyou
 * @version 1.0.0
 * implementation 'com.google.code.gson:gson:2.8.5'
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected AppUtils appUtils;

    public Gson gson = new Gson();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        appUtils = AppUtils.getInstance(this);

        initView();
        initData();
    }

    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void initData();

    @SuppressLint("HandlerLeak")
    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            gethandleMessage(msg);
        }
    };

    protected abstract void gethandleMessage(Message msg);
}
