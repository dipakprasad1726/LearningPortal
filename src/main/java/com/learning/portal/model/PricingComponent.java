package com.learning.portal.model;

import lombok.*;
import java.util.Map;

public class PricingComponent {
    private Map<String,Double> componentValueMap;

    public Map<String, Double> getComponentValueMap() {
        return componentValueMap;
    }

    public void setComponentValueMap(Map<String, Double> componentValueMap) {
        this.componentValueMap = componentValueMap;
    }

    @Override
    public String toString() {
        return "PricingComponent{" +
                "componentValueMap=" + componentValueMap +
                '}';
    }
}
