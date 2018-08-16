package com.flytoyou.baseapplication;

import android.os.Message;
import android.widget.TextView;

import com.flytoyou.baseapplication.Base.BaseActivity;
import com.flytoyou.baseapplication.Tools.Hint;

public class MainActivity extends BaseActivity {

    private TextView tv;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        tv = findViewById(R.id.ac_main_tv);
    }

    @Override
    protected void initData() {
        tv.setText(appUtils.getVerName());
        if (appUtils.isFirst()){
            Hint.showMsg(this,"第一次打开",1);
            appUtils.notFirst();
        }
    }

    @Override
    protected void gethandleMessage(Message msg) {

    }
}
