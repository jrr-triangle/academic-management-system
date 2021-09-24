package com.jrrtriangle.ams.service;

import com.jrrtriangle.ams.entity.Course;
import com.jrrtriangle.ams.entity.CourseMaterials;
import com.jrrtriangle.ams.exception.CourseNotFoundException;
import com.jrrtriangle.ams.exception.NotFoundException;

import java.util.List;

public interface CourseService {
    List<Course> getAllCourses();

    Course addCourse(Course course);

    Course findCourseById(Long id) throws CourseNotFoundException;

    CourseMaterials addCourseMaterials(CourseMaterials courseMaterials);

    CourseMaterials getCourseMaterialsById(Long id) throws NotFoundException;
}
