package com.flytoyou.baseapplication.View;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ViewTreeObserver;
import android.widget.TextView;

/**
 * 可以根据控件大小自动调整字体大小的TextView
 * @author flytoyou
 * @version 1.0.0
 */
@SuppressLint("AppCompatCustomView")
public class ScaleTextView extends TextView {
    public ScaleTextView(Context context) {
        this(context, null, 0);
    }

    public ScaleTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScaleTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {@Override
        public void onGlobalLayout() {
                //测量字符串的长度
                float measureWidth = getPaint().measureText(String.valueOf(getText()));
                //得到TextView 的宽度
                int width = getWidth() - getPaddingLeft() - getPaddingRight();
                //当前size大小
                float textSize = getTextSize();
                if (width < measureWidth) {
                    textSize = (width / measureWidth) * textSize;
                }
                //注意，使用像素大小设置
                setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
                getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });
    }
}
