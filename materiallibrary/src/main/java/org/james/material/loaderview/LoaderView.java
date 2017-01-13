package org.james.material.loaderview;

import android.graphics.Paint;

/**
 * Created by 1 on 2016/9/9.
 */
public interface LoaderView {
    void setRectColor(Paint rectPaint);

    void invalidate();

    boolean valueSet();
}
