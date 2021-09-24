package com.jrrtriangle.ams.service.impl;

import com.jrrtriangle.ams.entity.Course;
import com.jrrtriangle.ams.entity.CourseMaterials;
import com.jrrtriangle.ams.exception.CourseNotFoundException;
import com.jrrtriangle.ams.exception.NotFoundException;
import com.jrrtriangle.ams.repository.CourseMaterialsRepository;
import com.jrrtriangle.ams.repository.CourseRepository;
import com.jrrtriangle.ams.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CourseMaterialsRepository courseMaterialsRepository;
    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course addCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course findCourseById(Long id) throws CourseNotFoundException {
        Optional<Course> course = courseRepository.findById(id);
        if(!course.isPresent()){
            throw new CourseNotFoundException("Course is not found for id: "+id);
        }
        return course.get();
    }

    @Override
    public CourseMaterials addCourseMaterials(CourseMaterials courseMaterials) {
        return courseMaterialsRepository.save(courseMaterials);
    }

    @Override
    public CourseMaterials getCourseMaterialsById(Long id) throws NotFoundException {
        Optional<CourseMaterials> courseMaterials = courseMaterialsRepository.findById(id);
        if(!courseMaterials.isPresent()){
            throw new NotFoundException("CourseMaterials not found for id: "+id);
        }
        return courseMaterials.get();
    }
}
