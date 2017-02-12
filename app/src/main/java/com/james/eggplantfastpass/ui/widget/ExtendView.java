package com.james.eggplantfastpass.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.james.eggplantfastpass.R;

/**
 * Created by 1 on 2017/2/9.
 */

public class ExtendView extends LinearLayout implements View.OnClickListener{
    private boolean isVisible = false;
    private Context mContext;
    private LinearLayout llContainer;
    public ExtendView(Context context) {
        super(context);
        init(context);
    }


    public ExtendView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ExtendView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_extend_view,null);
        RelativeLayout rlConnectUs = (RelativeLayout) view.findViewById(R.id.id_rl_connect_us);
        llContainer = (LinearLayout) view.findViewById(R.id.id_ll_list_container);
        rlConnectUs.setOnClickListener(this);
        addView(view);
    }

    @Override
    public void onClick(View v) {
        if(isVisible){
            llContainer.setVisibility(VISIBLE);
            isVisible = false;
        }else {
            llContainer.setVisibility(INVISIBLE);
            isVisible = true;
        }
    }
}
