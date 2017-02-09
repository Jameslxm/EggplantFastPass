package com.james.eggplantfastpass.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import org.james.material.utils.MyLog;

/**
 * Created by 1 on 2017/2/6.
 */

public class ProportionView extends View {
    private Context mContext;
    private Paint paint;
    private Path proportionPath;
    private Point lfPoint;
    private Point rtPoint;
    private Point center;

    private int useValue;
    public ProportionView(Context context) {
        super(context);
        init(context);
    }

    public ProportionView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ProportionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawProportionView(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        MyLog.d("width:"+width+";height:"+height);

        center = new Point(width / 2f,height /2f );
        //左上角点
        lfPoint = new Point(center.x - center.x,center.y - 5);

        //右下角点
        rtPoint = new Point(center.x + center.x,center.y + 5);

    }

    private void drawProportionView(Canvas canvas) {

        /**
         * 完整
         */
        proportionPath = new Path();
        proportionPath.moveTo(lfPoint.x,lfPoint.y);
        proportionPath.lineTo(rtPoint.x,lfPoint.y);
        proportionPath.lineTo(rtPoint.x,rtPoint.y);
        proportionPath.lineTo(lfPoint.x,rtPoint.y);
        proportionPath.close();
        paint.setColor(Color.GRAY);
        canvas.drawPath(proportionPath,paint);

        /**
         * 已经使用
         */
        proportionPath = new Path();
        proportionPath.moveTo(lfPoint.x,lfPoint.y);
        proportionPath.lineTo(rtPoint.x / 100 * useValue,lfPoint.y);
        proportionPath.lineTo(rtPoint.x / 100 * useValue,rtPoint.y);
        proportionPath.lineTo(lfPoint.x,rtPoint.y);
        proportionPath.close();
        paint.setColor(Color.BLUE);
        canvas.drawPath(proportionPath,paint);
    }

    public void setUseValue(int useValue) {
        if(useValue > 100){
            useValue = 100;
        }else if(useValue < 0){
            useValue = 0;
        }
        this.useValue = useValue;
    }

    private class Point {
        float x, y;

        public Point(float x, float y) {
            this.x = x;
            this.y = y;
        }

        public Point() {
        }
    }
}
