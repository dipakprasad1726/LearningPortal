package com.learning.portal.model.components;

import com.learning.portal.model.components.Components;

public class BaseComponent extends Components {
    private double value;
    public BaseComponent(double value){
        this.value = value;
        this.description = "BaseComponent";
    }
    @Override
    public double getCharges() {
        return value;
    }
}
