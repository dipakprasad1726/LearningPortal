package com.learning.portal.model.components;

import java.text.DecimalFormat;

public abstract class Components {
    String description;
    public DecimalFormat df = new DecimalFormat("0.00");
    public String getDescription()
    {
        return description;
    }

    public abstract double getCharges();
}
