package com.example.demo.service;

import com.example.demo.model.Course;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface CourseService {
    public Course addCourse(Course course);

    public List<Course> getAllCourses();

    public Course updateCourses(int courseId, Course course);

    public Course findCourseById(int courseId);

    public boolean deleteCourseById(int courseId);
}
