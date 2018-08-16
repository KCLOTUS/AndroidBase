package com.flytoyou.baseapplication.Helper;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yyydjk.library.DropDownMenu;

import java.util.Arrays;
import java.util.List;

/**
 * 辅助DropDownMenu初始化
 * @author flytoyou
 * @version 1.0.0
 * implementation 'com.github.dongjunkun:DropDownMenu:1.0.4'
 */
public class DropDownMenuHelper {

    private DropDownMenuHelper(){}

    private static class SingletonHolder{
        private static DropDownMenuHelper helper = new DropDownMenuHelper();
    }

    public static DropDownMenuHelper getInstance(){
        return SingletonHolder.helper;
    }

    /**
     * 初始化DropDownMenu
     * @param context 调用者上下文
     * @param ddm 需要初始化的对象
     * @param headers 标题数组
     * @param views 数据列表视图
     */
    public void initDropDownMenu(Context context, DropDownMenu ddm, String[] headers, List<View> views){
        TextView contentView = new TextView(context);
        contentView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0));
        ddm.setDropDownMenu(Arrays.asList(headers),views,contentView);
    }
}
