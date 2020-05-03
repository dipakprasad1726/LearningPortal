package com.learning.portal.model.components;

public class Tax extends OtherComponents {
    private double value;
    private double baseValue;
    private Components components;
    public Tax(Components components,double value,double baseValue) {
        this.components = components;
        this.value = value;
        this.baseValue = baseValue;
    }

    @Override
    public double getCharges() {
        return (value/100)*baseValue + components.getCharges();
    }

    @Override
    public String getDescription() {
        return components.getDescription()+", Tax";
    }
}
