package com.learning.portal.model;

public class AddPricingComponentRequest {
    private String componentName;
    private double value;
    private String countryCode;

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    @Override
    public String toString() {
        return "AddPricingComponentRequest{" +
                "componentName='" + componentName + '\'' +
                ", value=" + value +
                ", countryCode='" + countryCode + '\'' +
                '}';
    }
}
