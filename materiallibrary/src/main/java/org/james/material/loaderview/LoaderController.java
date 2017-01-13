package org.james.material.loaderview;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.Log;
import android.view.animation.LinearInterpolator;

/**
 * Created by 1 on 2016/9/9.
 */
public class LoaderController {
    private Paint rectPaint;
    private final static int ANIMATION_CYCLE_DURATION = 750;
    private float widthWeight = LoaderConstant.MAX_WEIGHT;
    private float heightWeight = LoaderConstant.MAX_WEIGHT;
    private boolean useGradient = LoaderConstant.USE_GRADIENT_DEFAULT;
    private final static int MAX_COLOR_CONSTANT_VALUE = 255;
    private LinearGradient linearGradient;
    private LoaderView mLoaderView;
    private float progress;
    private ValueAnimator valueAnimator;
    public LoaderController(LoaderView loaderView) {
        this.mLoaderView = loaderView;
        init();
    }

    private void init() {
        rectPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);
        mLoaderView.setRectColor(rectPaint);
        setValueAnimator(0.5f, 1, ObjectAnimator.INFINITE);
    }
    public void stopLoading() {
        valueAnimator.cancel();
        setValueAnimator(progress, 0, 0);
        valueAnimator.start();
    }
    public void setHeightWeight(float heightWeight) {
        this.heightWeight = validateWeight(heightWeight);
    }

    public void setWidthWeight(float widthWeight) {
        this.widthWeight = validateWeight(widthWeight);
    }
    public void setUseGradient(boolean useGradient) {
        this.useGradient = useGradient;
    }

    private float validateWeight(float weight) {
        if (weight > LoaderConstant.MAX_WEIGHT)
            return LoaderConstant.MAX_WEIGHT;
        if (weight < LoaderConstant.MIN_WEIGHT)
            return LoaderConstant.MIN_WEIGHT;
        return weight;
    }
    public void onDraw(Canvas canvas){

        float margin_height = canvas.getHeight() * (1-heightWeight)/2;
        rectPaint.setAlpha((int) (progress * MAX_COLOR_CONSTANT_VALUE));
        if (useGradient) {
            prepareGradient(canvas.getWidth() * widthWeight);
        }
        canvas.drawRect(0, margin_height, canvas.getWidth() * widthWeight, canvas.getHeight() - margin_height, rectPaint);
    }
    public void onSizeChanged() {
        if (valueAnimator != null && !mLoaderView.valueSet()) {
            valueAnimator.start();
        }
    }
    private void prepareGradient(float width) {
        if (linearGradient == null) {
            linearGradient = new LinearGradient(0, 0, width, 0, rectPaint.getColor(), LoaderConstant.COLOR_DEFAULT_GRADIENT, Shader.TileMode.MIRROR);
        }
        rectPaint.setShader(linearGradient);
    }
    private void setValueAnimator(float begin, float end, int repeatCount) {
        valueAnimator = ValueAnimator.ofFloat(begin, end);
        valueAnimator.setRepeatCount(repeatCount);
        valueAnimator.setDuration(ANIMATION_CYCLE_DURATION);
        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                progress = (float) animation.getAnimatedValue();
                Log.d("debug","progress:"+progress);
                mLoaderView.invalidate();
            }
        });
    }

}
