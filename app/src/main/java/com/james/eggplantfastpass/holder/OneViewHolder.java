package com.james.eggplantfastpass.holder;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.james.eggplantfastpass.adapter.MultiTypeAdapter;
import com.james.eggplantfastpass.model.One;
import com.james.eggplantfastpass.ui.activity.ReceiverActivity;
import com.james.eggplantfastpass.ui.activity.ChooseFileActivity;

import static com.james.eggplantfastpass.R.id.id_tv_receive;
import static com.james.eggplantfastpass.R.id.id_tv_send;


/**
 * Created by yq05481 on 2017/1/3.
 */

public class OneViewHolder extends BaseViewHolder<One> {
    public OneViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void setUpView(One model, int position, MultiTypeAdapter adapter) {
        TextView tvSend = (TextView) getView(id_tv_send);
        TextView tvReceive = (TextView) getView(id_tv_receive);
        tvSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, ChooseFileActivity.class);
                context.startActivity(intent);
            }
        });
        tvReceive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, ReceiverActivity.class);
                context.startActivity(intent);
            }
        });
    }
}
