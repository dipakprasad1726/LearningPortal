package com.learning.portal.model.db;

import com.learning.portal.model.AddPricingComponentRequest;
import com.learning.portal.model.Course;
import com.learning.portal.model.GetCourseRequest;
import com.learning.portal.model.ResponseObject;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public interface QueryManager {
    public List<Course> getAllCourse() throws SQLException;
    public Course getCourseById(int id,String countryCode) throws SQLException;
    public Course getCourse(GetCourseRequest getCourseRequest) throws SQLException;
    public boolean addNewCourse(Course course) throws SQLException;
    public boolean editCourse(Course course) throws SQLException;
    public boolean addNewPricingComponent(AddPricingComponentRequest addPricingComponentRequest) throws SQLException;
}
