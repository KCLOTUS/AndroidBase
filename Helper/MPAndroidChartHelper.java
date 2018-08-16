package com.flytoyou.baseapplication.Helper;

import android.content.Context;

import com.flytoyou.baseapplication.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * MPAndroidChart图表帮助类
 * @author flytoyou
 * @version 1.0.0
 * https://github.com/PhilJay/MPAndroidChart
 */
public class MPAndroidChartHelper {

    private MPAndroidChartHelper(){}

    private static class SingletonHolder{
        private static MPAndroidChartHelper helper = new MPAndroidChartHelper();
    }

    public static MPAndroidChartHelper getInstance(){
        return SingletonHolder.helper;
    }

    /**
     * 初始化饼图
     * @param pieChart 需要初始化的对象
     * @param tableName 表名，图标下方显示
     * @param center 饼图中间文字
     * @param color 饼图进度颜色
     * @param f 进度百分比
     */
    public void initPieChart(Context context,PieChart pieChart, String tableName, String center, int color, float f){
        pieChart.setCenterText(center);//设置中心文字
        pieChart.setDescription(null);//隐藏右边文字
        pieChart.setHoleRadius(63f);//设置中心圆半径
        pieChart.setRotationEnabled(false);//禁止旋转
        //pieChart.getLegend().setEnabled(false);//隐藏左下角文字
        pieChart.animateXY(1000,1000);//动画效果
        List<PieEntry> entries = new ArrayList<>();//设置饼图占比顺时针
        entries.add(new PieEntry(f));
        entries.add(new PieEntry((float) 100-f));
        PieDataSet set = new PieDataSet(entries, tableName);
        ArrayList<Integer> colors = new ArrayList<Integer>();//设置饼图的颜色顺时针
        colors.add(context.getResources().getColor(color));
        colors.add(context.getResources().getColor(R.color.colorno));//饼图非进度颜色,
        set.setColors(colors);
        PieData data = new PieData(set);
        data.setDrawValues(false);//隐藏颜色上的文字
        pieChart.setData(data);
        pieChart.invalidate();
    }

}
