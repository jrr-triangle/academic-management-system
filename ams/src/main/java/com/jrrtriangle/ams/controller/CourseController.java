package com.jrrtriangle.ams.controller;

import com.jrrtriangle.ams.entity.Course;
import com.jrrtriangle.ams.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses(){
        List<Course> courseList = courseService.getAllCourses();
        return ResponseEntity.status(HttpStatus.OK).body(courseList);
    }
    @PostMapping
    public ResponseEntity<Course> saveCourse(@RequestBody Course course){
        return ResponseEntity.status(HttpStatus.CREATED).body(courseService.addCourse(course) );
    }
    @PutMapping
    public ResponseEntity<Course> editCourse(@RequestBody Course course,@PathVariable Long id){
        Course findCourse = courseService.findCourseById(id);
        if(!course.getTitle().isEmpty()){
            findCourse.setTitle(course.getTitle());
        }
        if(course.getCourseMaterials()!=null){
            findCourse.setCourseMaterials(course.getCourseMaterials());
        }
        if(course.getDepartments().size()>0){
            findCourse.setDepartments(course.getDepartments());
        }
        if (course.getTeacher()!=null){
            findCourse.setTeacher(course.getTeacher());
        }


        return ResponseEntity.status(HttpStatus.OK).body(courseService.addCourse(findCourse));

    }
    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(courseService.findCourseById(id));
    }

}
