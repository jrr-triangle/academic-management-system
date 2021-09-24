package com.jrrtriangle.ams.controller;

import com.jrrtriangle.ams.entity.Course;
import com.jrrtriangle.ams.entity.CourseMaterials;
import com.jrrtriangle.ams.exception.CourseNotFoundException;
import com.jrrtriangle.ams.exception.EmptyException;
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
    public ResponseEntity<Course> editCourse(@RequestBody Course course,@PathVariable Long id) throws CourseNotFoundException {
        Course findCourse = courseService. findCourseById(id);
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
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) throws CourseNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(courseService.findCourseById(id));
    }

    @PostMapping("/materials")
    public ResponseEntity<CourseMaterials> createCourseMaterials(@RequestBody CourseMaterials courseMaterials) throws EmptyException {
        if(courseMaterials.getUrl()==null){
            throw new EmptyException("Course materials must have url");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(courseService.addCourseMaterials(courseMaterials));
    }
    @GetMapping("/materials/{id}")
    public ResponseEntity<CourseMaterials> getCourseMaterials(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(courseService.getCourseMaterialsById(id));
    }
}
