package com.example.demo.service;

import com.example.demo.model.Course;
import com.example.demo.model.Student;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImp implements StudentService {
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    CourseRepository courseRepository;

    // Add new student
    @Override
    public void addStudentWithCourse(Student student) {
        Student newStudent = new Student();

        newStudent.setId(student.getId());
        newStudent.setName(student.getName());
        newStudent.setAge(student.getAge());

        Student saveStudent = studentRepository.save(newStudent);

        saveStudent.setLikedCourses(student.getLikedCourses().stream().map(
                course -> {
                    Course newCourse = course;
                    if (newCourse.getCourseId() > 0) {
                        newCourse = courseRepository.getOne(newCourse.getCourseId());
                    }
                    newCourse.addStudent(saveStudent);

                    return newCourse;
                }).collect(Collectors.toSet()));

        studentRepository.save(saveStudent);
    }

    // Get all student
    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // Get a student
    @Override
    public Student findStudentById(int id) {
        Student newStudent = studentRepository.getOne(id);

        if (newStudent == null) {
            ResponseEntity.notFound().build();
        }

        newStudent.getId();
        newStudent.getName();
        newStudent.getAge();
        newStudent.getLikedCourses();

        Student saveStudent = studentRepository.save(newStudent);

        return saveStudent;
    }

    // Delete
    @Override
    public void deleteStudent(int id) {
        Student std = studentRepository.getOne(id);

        if (std != null) {
            studentRepository.delete(std);
        }
    }

    // Edit
    @Override
    public void updateAStudent(int id, Student student) {
        Student newStudent = studentRepository.getOne(id);

        newStudent.setName(student.getName());
        newStudent.setAge(student.getAge());

        newStudent.setLikedCourses(student.getLikedCourses().stream().map(
                course -> {
                    Course newCourse = course;
                    if (newCourse.getCourseId() > 0) {
                        newCourse = courseRepository.getOne(newCourse.getCourseId());
                    }
                    newCourse.addStudent(newStudent);

                    return newCourse;
                }).collect(Collectors.toSet()));
        studentRepository.save(newStudent);
    }

    // test
//    public Page<Student> listAll(int pageNum) {
//        int pageSize = 10;
//
//        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
//
//        return studentRepository.findAll(pageable);
//    }

    public Page<Student> getTest(
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<String> sortBy
    ) {
        return studentRepository.findAll(
                PageRequest.of(
                        page.orElse(0),
                        10,
                        Sort.Direction.ASC, sortBy.orElse("id")
                )
        );
    }

}
