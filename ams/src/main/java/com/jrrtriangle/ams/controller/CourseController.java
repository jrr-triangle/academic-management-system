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

}
