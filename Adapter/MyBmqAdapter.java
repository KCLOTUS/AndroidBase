package com.flytoyou.baseapplication.Adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * BaseMultiItemQuickAdapter的封装类
 * 可以在调用处进行业务处理
 * @author flytoyou
 * @version 1.0.0
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public abstract class MyBmqAdapter extends BaseMultiItemQuickAdapter {

    /**
     * 初始化对象
     * @param data 数据
     * @param layoutResList 布局
     */
    public MyBmqAdapter(List<MultiItemEntity> data, List<Integer> layoutResList) {
        super(data);
        for (int i=0;i<layoutResList.size();i++){
            addItemType(i,layoutResList.get(i));
        }
    }

    /**
     * 在原生的绘制函数中将业务抛出给调用者自行处理
     */
    @Override
    protected void convert(BaseViewHolder helper, Object item) {
        mconvert(helper, item);
    }

    /**
     * 将数据处理的过程抛向调用者
     * @param helper
     * @param item
     */
    abstract void mconvert(BaseViewHolder helper, Object item);
}