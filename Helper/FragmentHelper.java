package com.flytoyou.baseapplication.Helper;

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Frame和碎片帮助类
 * @author flytoyou
 * @version 1.0.0
 */
public class FragmentHelper {

    private FragmentActivity fragmentActivity;
    private List<Fragment> fragments = new ArrayList<>();
    private int frameLayout;
    private int n = 0;

    private FragmentHelper(){}

    private static class SingletonHolder{
        private static FragmentHelper fragmentHelper = new FragmentHelper();
    }

    public static FragmentHelper getInstance(){
        return SingletonHolder.fragmentHelper;
    }

    /**
     * 初始化
     * @param activity 调用对象
     * @param list 碎片集合
     * @param id 用于显示的frameLayout的id
     */
    @SuppressLint("CommitTransaction")
    public void initFragment(FragmentActivity activity, List<Fragment> list, int id){
        fragmentActivity = activity;
        fragments = list;
        frameLayout = id;
    }

    /**
     * 切换碎片
     * @param i 碎片的下标
     */
    public void switchToFragment(int i) {
        FragmentTransaction transaction = fragmentActivity.getSupportFragmentManager().beginTransaction();
        hideFragments(transaction);
        if (n==0){
            for (Fragment fragment:fragments){
                transaction.add(frameLayout,fragment);
            }
            n++;
        }
        transaction.show(fragments.get(i));
        transaction.commit();
    }

    /**
     * 隐藏所有碎片
     * @param transaction 管理者对象
     */
    private void hideFragments(FragmentTransaction transaction){
        for (Fragment fragment:fragments){
            if (fragment != null){
                transaction.hide(fragment);
            }
        }
    }

}
