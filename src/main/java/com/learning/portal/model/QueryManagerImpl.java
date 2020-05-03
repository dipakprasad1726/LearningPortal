package com.learning.portal.model;

import com.learning.portal.model.components.BaseComponent;
import com.learning.portal.model.components.Components;
import com.learning.portal.model.components.CurrencyConversion;
import com.learning.portal.model.components.Tax;
import com.learning.portal.model.db.DbConnection;
import com.learning.portal.model.db.QueryManager;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

@Component
public class QueryManagerImpl implements QueryManager {
    @Inject
    DbConnection dbConnection;
    public QueryManagerImpl(DbConnection dbConnection){
        this.dbConnection = dbConnection;
    }

    @Override
    public ArrayList<Course> getAllCourse() throws SQLException {
        Statement stmt = dbConnection.getConnection();
        String sql = "select * from courses";
        ResultSet rs = stmt.executeQuery(sql);
        ArrayList<Course> courseArrayList = new ArrayList<>();
        while (rs.next()) {
            Course course = new Course();
            course.setCourseId(rs.getInt("course_id"));
            course.setCourseDescription(rs.getString("course_description"));
            course.setBasePrice(rs.getDouble("base_price"));
            course.setCourseName(rs.getString("course_name"));
            courseArrayList.add(course);
        }
        return courseArrayList;
    }

    @Override
    public Course getCourseById(int id,String countryCode) throws SQLException {
        Statement stmt = dbConnection.getConnection();
        String sql = "select * from courses ,country_component_value as ccv where " +
                "course_id="+id+" and ccv.country_code='"+countryCode+"'";
        ResultSet rs = stmt.executeQuery(sql);
        Course course = new Course();
        while (rs.next()) {
            course.setCourseId(rs.getInt("course_id"));
            course.setCourseDescription(rs.getString("course_description"));
            course.setBasePrice(rs.getDouble("base_price"));
            course.setCourseName(rs.getString("course_name"));
            Components comp = new BaseComponent(rs.getDouble("base_price"));
            if(rs.getDouble("tax_value") > 0 ){
                comp = new Tax(comp,rs.getDouble("tax_value"),rs.getDouble("base_price"));
            }
            if(rs.getDouble("currency_conversion_value") > 0 ){
                comp = new CurrencyConversion(comp,rs.getDouble("currency_conversion_value"),rs.getDouble("base_price"));
                course.setBasePrice(Math.round((rs.getDouble("base_price")/rs.getDouble("currency_conversion_value"))*100.0)/100.0);
            }
            course.setTotalComponentPrice(Math.round((comp.getCharges()*100.0)/100.0));
            course.setComponent_included(comp.getDescription());
        }
        return course;
    }

    @Override
    public Course getCourse(GetCourseRequest getCourseRequest) throws SQLException {
        Statement stmt = dbConnection.getConnection();
        String sql = "select * from courses ,country_component_value as ccv where " +
                "(course_name ='"+getCourseRequest.getCourseName()+"' or course_id='"+
                getCourseRequest.getCourseId()+"') " +
                " and ccv.country_code='"+getCourseRequest.getCountryCode()+"'";

        ResultSet rs = stmt.executeQuery(sql);
        Course course = new Course();
        while (rs.next()) {
            course.setCourseId(rs.getInt("course_id"));
            course.setCourseDescription(rs.getString("course_description"));
            course.setBasePrice(rs.getDouble("base_price"));
            course.setCourseName(rs.getString("course_name"));
            Components comp = new BaseComponent(rs.getDouble("base_price"));
            if(rs.getDouble("tax_value") > 0 ){
                comp = new Tax(comp,rs.getDouble("tax_value"),rs.getDouble("base_price"));
            }
            if(rs.getDouble("currency_conversion_value") > 0 ){
                comp = new Tax(comp,rs.getDouble("currency_conversion_value"),rs.getDouble("base_price"));
                course.setBasePrice(rs.getDouble("base_price")/rs.getDouble("currency_conversion_value"));
            }
            course.setTotalComponentPrice(comp.getCharges());
            course.setComponent_included(comp.getDescription());
        }
        return course;
    }

    @Override
    public boolean addNewCourse(Course course) throws SQLException {
        Statement stmt = dbConnection.getConnection();
        String sql = "insert into courses(course_name,base_price,course_description) values('"+course.getCourseName()+
                "',"+course.getBasePrice()+",'"+course.getCourseDescription()+"')";
        return stmt.execute(sql);
    }

    @Override
    public boolean editCourse(Course course) throws SQLException {
        Statement stmt = dbConnection.getConnection();
        String sql = "insert into courses(course_name,base_price,course_description) values('"+course.getCourseName()+
                "',"+course.getBasePrice()+",'"+course.getCourseDescription()+"')";
        return stmt.execute(sql);
    }

    @Override
    public boolean addNewPricingComponent(AddPricingComponentRequest addPricingComponentRequest) throws SQLException {
        Statement stmt = dbConnection.getConnection();
        String sql = "alter table country_component_value add column("+addPricingComponentRequest.getComponentName()+" double not null)";
        if(stmt.execute(sql)){
            String sql2 = "select * from country_component_value where country_code = '"+addPricingComponentRequest.getCountryCode()+"'";
            ResultSet rs = stmt.executeQuery(sql2);
            while (rs.next()){
                String sql3 = "update country_component_value set tax_value="+addPricingComponentRequest.getValue()+" where country_code='"+addPricingComponentRequest.getCountryCode()+"'";
                return stmt.execute(sql3);
            }
        }
        return false;
    }
}
