package com.jrrtriangle.ams.service.impl;

import com.jrrtriangle.ams.entity.Course;
import com.jrrtriangle.ams.repository.CourseRepository;
import com.jrrtriangle.ams.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseRepository courseRepository;
    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course addCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course findCourseById(Long id) {
        Optional<Course> course = courseRepository.findById(id);
        if(!course.isPresent()){

        }
        return null;
    }
}
