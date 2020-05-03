package com.learning.portal.model;

public class Course {
    private String courseName;
    private int courseId;
    private String courseDescription;
    private double basePrice;
    private double totalComponentPrice;
    private String component_included;
    private PricingComponent pricingComponent;

    public Course(String courseName, int courseId, String courseDescription, PricingComponent pricingComponent) {
        this.courseName = courseName;
        this.courseId = courseId;
        this.courseDescription = courseDescription;
        this.pricingComponent = pricingComponent;
    }

    public Course() {
    }

    public double getTotalComponentPrice() {
        return totalComponentPrice;
    }

    public void setTotalComponentPrice(double totalComponentPrice) {
        this.totalComponentPrice = totalComponentPrice;
    }

    public String getComponent_included() {
        return component_included;
    }

    public void setComponent_included(String component_included) {
        this.component_included = component_included;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public PricingComponent getPricingComponent() {
        return pricingComponent;
    }

    public void setPricingComponent(PricingComponent pricingComponent) {
        this.pricingComponent = pricingComponent;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseName='" + courseName + '\'' +
                ", courseId=" + courseId +
                ", courseDescription='" + courseDescription + '\'' +
                ", pricingComponent=" + pricingComponent +
                '}';
    }
}
