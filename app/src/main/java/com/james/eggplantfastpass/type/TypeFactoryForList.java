package com.james.eggplantfastpass.type;

import android.view.View;

import com.james.eggplantfastpass.R;
import com.james.eggplantfastpass.holder.BaseViewHolder;
import com.james.eggplantfastpass.holder.FiveViewHolder;
import com.james.eggplantfastpass.holder.FourViewHolder;
import com.james.eggplantfastpass.holder.NormalViewHolder;
import com.james.eggplantfastpass.holder.OneViewHolder;
import com.james.eggplantfastpass.holder.ThreeViewHolder;
import com.james.eggplantfastpass.holder.TwoViewHolder;
import com.james.eggplantfastpass.model.Five;
import com.james.eggplantfastpass.model.Four;
import com.james.eggplantfastpass.model.Normal;
import com.james.eggplantfastpass.model.One;
import com.james.eggplantfastpass.model.Three;
import com.james.eggplantfastpass.model.Two;


/**
 * Created by yq05481 on 2016/12/30.
 */

public class TypeFactoryForList implements TypeFactory {
    private final int TYPE_RESOURCE_ONE = R.layout.layout_send_receiver;
    private final int TYPE_RESOURCE_TWO = R.layout.layout_second;
    private final int TYPE_RESOURCE_THREE = R.layout.layout_item_three;
    private final int TYPE_RESOURCE_FOUR = R.layout.layout_item_four;
    private final int TYPE_RESOURCE_FIVE = R.layout.layout_item_five;
    private final int TYPE_RESOURCE_NORMAL = R.layout.layout_item_normal;
    @Override
    public int type(One one) {
        return TYPE_RESOURCE_ONE;
    }

    @Override
    public int type(Two one) {
        return TYPE_RESOURCE_TWO;
    }

    @Override
    public int type(Three three) {
        return TYPE_RESOURCE_THREE;
    }

    @Override
    public int type(Four four) {
        return TYPE_RESOURCE_FOUR;
    }

    @Override
    public int type(Five five) {
        return TYPE_RESOURCE_FIVE;
    }

    @Override
    public int type(Normal normal) {
        return TYPE_RESOURCE_NORMAL;
    }

    @Override
    public BaseViewHolder createViewHolder(int type, View itemView) {

        if(TYPE_RESOURCE_ONE == type){
            return new OneViewHolder(itemView);
        }else if (TYPE_RESOURCE_TWO == type){
            return new TwoViewHolder(itemView);
        }else if (TYPE_RESOURCE_THREE == type){
            return new ThreeViewHolder(itemView);
        }else if (TYPE_RESOURCE_NORMAL == type){
            return new NormalViewHolder(itemView);
        }else if(TYPE_RESOURCE_FOUR == type){
            return new FourViewHolder(itemView);
        }else if(TYPE_RESOURCE_FIVE == type){
            return new FiveViewHolder(itemView);
        }

        return null;
    }
}
