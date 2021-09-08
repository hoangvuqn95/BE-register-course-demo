package com.example.demo.service;

import com.example.demo.model.Course;
import com.example.demo.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseServiceImp implements CourseService{
    @Autowired
    CourseRepository courseRepository;



    @Override
    public Course addCourse(Course course) {
       Course newCourse = new Course();

       newCourse.setCourseId(course.getCourseId());
       newCourse.setCourseName(course.getCourseName());

       Course saveCourse = courseRepository.save(newCourse);

       return saveCourse;
    }

    @Override
    public List<Course> getAllCourses() {
        List<Course> courseList = new ArrayList<>();
        courseRepository.findAll().forEach(courseList::add);

        return courseList;
    }

    // find courseId
    @Override
    public Course findCourseById(int courseId)
    {
        Course newCourse = courseRepository.getOne(courseId);
        if(newCourse == null) {
            ResponseEntity.notFound().build();
        }
        newCourse.getCourseId();
        newCourse.getCourseName();

        Course saveCourse = courseRepository.save(newCourse);
        return saveCourse;
    }

    // delete one course
    @Override
    public boolean deleteCourseById(int courseId) {
        Course newCourse = courseRepository.getOne(courseId);

//        courseRepository.deleteById(courseId);

        if(newCourse != null) {
            courseRepository.delete(newCourse);

            return true;
        }
        return false;
    }

    // update course
    public Course updateCourses(int courseId, Course course) {
        Course newId = courseRepository.getOne(courseId);

        newId.setCourseName(course.getCourseName());

        Course saveCourse = courseRepository.save(newId);

        return saveCourse;
    }

}
