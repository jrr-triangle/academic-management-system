package com.jrrtriangle.ams.repository;

import com.jrrtriangle.ams.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course,Long> {
}
