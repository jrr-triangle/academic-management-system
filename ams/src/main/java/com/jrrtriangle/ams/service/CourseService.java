package com.jrrtriangle.ams.service;

import com.jrrtriangle.ams.entity.Course;

import java.util.List;

public interface CourseService {
    List<Course> getAllCourses();

    Course addCourse(Course course);

    Course findCourseById(Long id);
}
