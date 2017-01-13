package org.james.material.loaderview;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;

import org.james.material.R;


/**
 * 实现原理：
 * 绘画灰色矩形，通过ValueAnimator，来改变画笔的透明度的值，来实现闪烁效果
 * 
 * 使用方法：参照 https://github.com/elye/loaderviewlibrary
 * Created by 1 on 2016/9/9.
 */
public class LoaderTextView extends TextView implements LoaderView{
    private LoaderController loaderController;
    public LoaderTextView(Context context) {
        super(context);
        init(null);
    }

    public LoaderTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public LoaderTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public LoaderTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        loaderController = new LoaderController(this);
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.loader_view, 0, 0);
        loaderController.setWidthWeight(typedArray.getFloat(R.styleable.loader_view_width_weight, LoaderConstant.MAX_WEIGHT));
        loaderController.setHeightWeight(typedArray.getFloat(R.styleable.loader_view_height_weight, LoaderConstant.MAX_WEIGHT));
        loaderController.setUseGradient(typedArray.getBoolean(R.styleable.loader_view_use_gradient, LoaderConstant.USE_GRADIENT_DEFAULT));
        typedArray.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        loaderController.onDraw(canvas);
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        super.setText(text, type);
        if (loaderController != null) {
            loaderController.stopLoading();
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        loaderController.onSizeChanged();
    }

    @Override
    public void setRectColor(Paint rectPaint) {
        final Typeface typeface = getTypeface();
        if (typeface != null && typeface.getStyle()== Typeface.BOLD ) {
            rectPaint.setColor(LoaderConstant.COLOR_DARKER_GREY);
        } else {
            rectPaint.setColor(LoaderConstant.COLOR_DEFAULT_GREY);
        }
    }

    @Override
    public boolean valueSet() {
        return !TextUtils.isEmpty(getText());
    }

}
