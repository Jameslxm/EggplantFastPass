package com.james.eggplantfastpass.ui.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import com.james.eggplantfastpass.R;


/**
 * Created by 1 on 2017/2/10.
 */

public class ExpandIconView extends View {
    private  Bitmap bitmap;
    public ExpandIconView(Context context) {
        super(context);
    }

    public ExpandIconView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ExpandIconView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode =  MeasureSpec.getMode(widthMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        if(widthMode != MeasureSpec.EXACTLY){
            if(bitmap != null) {
                width = bitmap.getWidth();

            }
        }
        if(heightMode != MeasureSpec.EXACTLY){
            if(bitmap != null) {
                height = bitmap.getHeight();
            }
        }

        setMeasuredDimension(width,height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //画箭头
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_arrow);

        canvas.drawBitmap(bitmap,0,0,null);
    }
}
