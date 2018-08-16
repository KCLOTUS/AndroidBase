package com.flytoyou.baseapplication.Utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.widget.EditText;

/**
 * APP工具
 * @author flytoyou
 * @version 1.0
 */
public final class AppUtils {

    private Context mContext;
    @SuppressLint("StaticFieldLeak")
    private static AppUtils appUtils;
    private static SharedPreferences sp;
    private static SharedPreferences.Editor editor;

    @SuppressLint("CommitPrefEdits")
    private AppUtils(Context context){
        mContext = context;
        sp = context.getSharedPreferences("APP", Context.MODE_PRIVATE);
        editor = sp.edit();
    }

    public static AppUtils getInstance(Context context){
        if (appUtils == null){
            appUtils = new AppUtils(context);
        }
        return appUtils;
    }

    /**
     * 判读是不是第一次启动APP
     */
    public boolean isFirst(){
        return sp.getBoolean("isFirst",true);
    }

    /**
     * 如果已经打开过app，则执行此方法。
     */
    public void notFirst(){
        editor.putBoolean("isFirst",false);
        editor.commit();
    }

    /**
     * 获取当前本地apk的版本
     */
    public int getVersionCode() {
        int versionCode = 0;
        try {
            //获取软件版本号，对应AndroidManifest.xml下android:versionCode
            versionCode = mContext.getPackageManager().
                    getPackageInfo(mContext.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    /**
     * 获取版本号名称
     */
    public String getVerName() {
        String verName = "";
        try {
            verName = mContext.getPackageManager().
                    getPackageInfo(mContext.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return verName;
    }

    /**
     * 给输入框设置错误信息，并获取焦点
     */
    public void setError(EditText editText,String error){
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        editText.setError(error);
    }

}

