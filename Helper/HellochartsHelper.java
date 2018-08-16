package com.flytoyou.baseapplication.Helper;

import android.graphics.Color;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.gesture.ContainerScrollType;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.ColumnChartView;
import lecho.lib.hellocharts.view.LineChartView;

/**
 * Hellocharts帮助工具
 * @author flytoyou
 * @version 1.0.0
 * implementation 'com.github.lecho:hellocharts-android:v1.5.8'
 */
public class HellochartsHelper<T> {

    private HellochartsHelper(){
    }

    private static class SingletonHolder{
        private static HellochartsHelper helper = new HellochartsHelper();
    }

    public static HellochartsHelper getInstance(){
        return SingletonHolder.helper;
    }

    /**
     * 初始化柱形统计图
     * @param ccv 需要初始化的对象
     * @param length 柱形图的柱子个数
     * @param impl 设置柱形图数据的接口函数
     */
    public void initColumnChartView(ColumnChartView ccv, int length, HellochartsHelperImpl impl){
        List<Column> columns = new ArrayList<>();
        List<AxisValue> axisValueList = new ArrayList<>();
        for (int i = 0; i < length; ++i) {
            axisValueList.add(new AxisValue(i).setLabel(impl.setXdata(i)));//X轴数据
            List<SubcolumnValue> values = new ArrayList<>(); //Y轴数据以及属性
            values.add(new SubcolumnValue(impl.setYdata(i), ChartUtils.pickColor()));//设置Y轴数据和颜色等属性
            Column column = new Column(values);//设置Y轴数据
            column.setHasLabels(true);//是否显示柱子的数据
            columns.add(column);
        }
        ColumnChartData data = new ColumnChartData(columns);
        data.setAxisXBottom(new Axis(axisValueList));//设置X轴的属性
        ccv.setColumnChartData(data);//将数据设置给显示柱形图的控件
        ccv.setInteractive(false);//禁止交互
    }

    /**
     * 初始化折线图
     * @param lc 需要初始化的对象
     * @param length 数据长度
     * @param impl 数据处理接口函数
     */
    public void initLineChartView(LineChartView lc, int length, HellochartsHelperImpl impl){
        List<AxisValue> mAxisXValues = new ArrayList<>();//X轴
        List<PointValue> mPointValues = new ArrayList<>();//Y轴
        LineChartData data = new LineChartData();//数据

        for (int i = 0; i < length; i++) {
            mAxisXValues.add(new AxisValue(i).setLabel(impl.setXdata(i)));//X轴数据
        }
        for (int i = 0; i < length; i++) {
            mPointValues.add(new PointValue(i,impl.setYdata(i)));//Y轴数据
        }

        //折线属性
        List<Line> lines = new ArrayList<>();
        Line line = new Line(mPointValues).setColor(Color.parseColor("#a4dfdb"));//折线的颜色（橙色）
        line.setShape(ValueShape.CIRCLE);//折线图上每个数据点的形状  这里是圆形 （有三种 ：ValueShape.SQUARE  ValueShape.CIRCLE  ValueShape.DIAMOND）
        line.setCubic(false);//曲线是否平滑，即是曲线还是折线
        line.setFilled(false);//是否填充曲线的面积
        line.setHasLabels(true);//曲线的数据坐标是否加上备注
//      line.setHasLabelsOnlyForSelected(true);//点击数据坐标提示数据（设置了这个line.setHasLabels(true);就无效）
        line.setHasLines(true);//是否用线显示。如果为false 则没有曲线只有点显示
        line.setHasPoints(true);//是否显示圆点 如果为false 则没有原点只有点显示（每个数据点都是个大的圆点）
        lines.add(line);
        data.setLines(lines);

        //X轴属性
        Axis axisX = new Axis();
        axisX.setHasTiltedLabels(true);  //X坐标轴字体是斜的显示还是直的，true是斜的显示
        //axisX.setTextColor(R.color.colorToolBar);  //设置字体颜色
        //axisX.setName("date");  //表格名称
        axisX.setTextSize(10);//设置字体大小
        axisX.setMaxLabelChars(8); //最多几个X轴坐标，意思就是你的缩放让X轴上数据的个数7<=x<=mAxisXValues.length
        axisX.setValues(mAxisXValues);  //填充X轴的坐标名称
        axisX.setHasLines(true); //x 轴分割线
        data.setAxisXBottom(axisX); //x 轴在底部
        //data.setAxisXTop(axisX);  //x 轴在顶部

        // Y轴是根据数据的大小自动设置Y轴上限(在下面我会给出固定Y轴数据个数的解决方案)
        Axis axisY = new Axis();  //Y轴
        axisY.setName("");//y轴标注
        axisY.setTextSize(10);//设置字体大小
        data.setAxisYLeft(axisY);  //Y轴设置在左边
        //data.setAxisYRight(axisY);  //y轴设置在右边

        //设置行为属性，支持缩放、滑动以及平移
        lc.setInteractive(false);//禁止交互
        lc.setZoomType(ZoomType.HORIZONTAL);
        lc.setMaxZoom((float) 2);//最大放大比例
        lc.setContainerScrollEnabled(true, ContainerScrollType.HORIZONTAL);
        lc.setVisibility(View.VISIBLE);
        lc.setLineChartData(data);
        /**注：下面的7，10只是代表一个数字去类比而已
         * 当时是为了解决X轴固定数据个数。见（http://forum.xda-developers.com/tools/programming/library-hellocharts-charting-library-t2904456/page2）;
         */
        Viewport v = new Viewport(lc.getMaximumViewport());
        v.left = 0;
        v.right= 7;
        lc.setCurrentViewport(v);
    }

    public interface HellochartsHelperImpl<T>{
        String setXdata(int i);
        int setYdata(int i);
    }

}
