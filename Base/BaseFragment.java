package com.flytoyou.baseapplication.Base;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 碎片基类
 * @author flytoyou
 * @version 1.0.0
 */
public abstract class BaseFragment extends Fragment implements View.OnClickListener {

    private View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null){
            rootView = inflater.inflate(getLayoutId(),null);
            initView(rootView);
        }else {
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (parent != null) {
                parent.removeView(rootView);
            }
        }
        return rootView;
    }

    protected abstract int getLayoutId();

    protected abstract void initView(View rootView);

    @Override
    public void onClick(View view) {

    }

    @SuppressLint("HandlerLeak")
    public Handler mhandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            getMessage(msg);
        }
    };

    protected abstract void getMessage(Message msg);

}
