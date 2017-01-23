package com.james.eggplantfastpass.model;

import com.james.eggplantfastpass.type.TypeFactory;

/**
 * Created by yq05481 on 2016/12/30.
 */

public class Two implements Visitable {
    String text;

    public Two(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public int type(TypeFactory typeFactory) {
        return typeFactory.type(this);
    }
}
