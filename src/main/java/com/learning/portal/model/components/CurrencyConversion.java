package com.learning.portal.model.components;

import com.learning.portal.model.components.Components;
import com.learning.portal.model.components.OtherComponents;

public class CurrencyConversion extends OtherComponents {
    private double value;
    private double baseValue;
    private Components components;
    public CurrencyConversion(Components components,double value,double baseValue) {
        this.components = components;
        this.value = value;
        this.baseValue = baseValue;
    }

    @Override
    public double getCharges() {
        if(value!=0) return (components.getCharges()/value);
        return components.getCharges();
    }

    @Override
    public String getDescription() {
        return components.getDescription()+", Currency Conversion";
    }
}
