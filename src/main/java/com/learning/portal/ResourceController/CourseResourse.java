package com.learning.portal.ResourceController;

import com.learning.portal.exceptions.CountryNotFoundException;
import com.learning.portal.exceptions.CourseNotFoundException;
import com.learning.portal.exceptions.QueryFailureException;
import com.learning.portal.model.AddPricingComponentRequest;
import com.learning.portal.model.Course;
import com.learning.portal.model.GetCourseRequest;
import com.learning.portal.model.db.QueryManager;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.ws.rs.ext.Provider;
import java.sql.SQLException;
import java.util.List;

@RequestMapping("course")
@Provider
@RestController
public class CourseResourse {

    @Inject
    private QueryManager queryManager;

    public CourseResourse(QueryManager queryManager){
        this.queryManager = queryManager;
    }

    @GetMapping("/")
    public List<Course> getAllCourses() throws SQLException {
        return queryManager.getAllCourse();
    }

    @GetMapping("/{id}/{countryCode}")
    public Course getCourse(@PathVariable int id,@PathVariable String countryCode) throws SQLException {
        Course course = queryManager.getCourseById(id,countryCode);
        if(course==null) throw new CourseNotFoundException();
        else return course;
    }

    @GetMapping("/getCourse")
    public Course getCourse(GetCourseRequest getCourseRequest) throws SQLException {
        Course course = queryManager.getCourse(getCourseRequest);
        if(course==null) throw new CourseNotFoundException();
        else return course;
    }

    @PutMapping("/addNewCourse")
    public ResponseEntity<Object> addNewCourse(Course course) throws SQLException, JSONException {
        if(queryManager.addNewCourse(course)){
            throw new QueryFailureException();
        }else{
            JSONObject jo = new JSONObject();
            jo.put("status","SUCCESS");
            jo.put("message","Course added successfully");
            return new ResponseEntity<>(jo.toString(), HttpStatus.ACCEPTED);
        }
    }

    @PutMapping("/addNewPricingComponent")
    public ResponseEntity<Object> addNewPricingComponent(AddPricingComponentRequest addPricingComponentRequest) throws SQLException, JSONException {
        if(!queryManager.addNewPricingComponent(addPricingComponentRequest)) throw new QueryFailureException();
        JSONObject jo = new JSONObject();
        jo.put("status","SUCCESS");
        jo.put("message","Pricing component "+addPricingComponentRequest.getComponentName()+" added successfully");
        return new ResponseEntity<>(jo.toString(), HttpStatus.ACCEPTED);
    }

    @PutMapping("/editCourse")
    public ResponseEntity<Object> editCourse(Course course) throws JSONException, SQLException {
        if(!queryManager.editCourse(course)) throw new QueryFailureException();
        JSONObject jo = new JSONObject();
        jo.put("status","SUCCESS");
        jo.put("message","Course "+course.getCourseName()+" edited successfully");
        return new ResponseEntity<>(jo.toString(), HttpStatus.ACCEPTED);
    }
}
