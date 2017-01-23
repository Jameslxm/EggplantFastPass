package com.james.eggplantfastpass.type;

import android.view.View;

import com.james.eggplantfastpass.holder.BaseViewHolder;
import com.james.eggplantfastpass.model.Five;
import com.james.eggplantfastpass.model.Four;
import com.james.eggplantfastpass.model.Normal;
import com.james.eggplantfastpass.model.One;
import com.james.eggplantfastpass.model.Three;
import com.james.eggplantfastpass.model.Two;


/**
 * Created by yq05481 on 2016/12/30.
 */

public interface TypeFactory {
    int type(One one);

    int type(Two two);

    int type(Three three);
    int type(Four four);
    int type(Five five);

    int type(Normal normal);

    BaseViewHolder createViewHolder(int type, View itemView);
}
