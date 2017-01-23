package com.james.eggplantfastpass.model;

import com.james.eggplantfastpass.type.TypeFactory;

/**
 * Created by Administrator on 2017/1/15 0015.
 */

public class Four implements Visitable{
    @Override
    public int type(TypeFactory typeFactory) {
        return typeFactory.type(this);
    }
}
