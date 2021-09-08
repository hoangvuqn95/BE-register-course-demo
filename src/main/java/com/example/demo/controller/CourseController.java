package com.example.demo.controller;


import com.example.demo.model.Course;
import com.example.demo.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class CourseController {
    @Autowired
    CourseService courseService;

    @PostMapping("course")
    public String addCourse(@RequestBody Course course) {
        courseService.addCourse(course);

        return "New Course was added!";
    }

    @GetMapping("course")
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("course/{courseId}")
    public Course getCourseId(@PathVariable("courseId") int courseId) {
        return courseService.findCourseById(courseId);
    }

    @PatchMapping("course/{courseId}")
    public String updateCourse(@PathVariable("courseId") int courseId, @RequestBody Course course) {
        courseService.updateCourses(courseId, course);

        return "Course data was updated";
    }

    @DeleteMapping("course/{courseId}")
    public boolean deleteCourse(@PathVariable("courseId") int courseId) {
        return courseService.deleteCourseById(courseId);
    }
}
