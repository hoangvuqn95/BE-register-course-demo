package com.example.demo.service;

import com.example.demo.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    public void addStudentWithCourse(Student student) ;

    public List<Student> getAllStudents();

    public Student findStudentById(int id);

    public void updateAStudent(int id, Student student);

    public void deleteStudent(int id);

    // test paging and sorting
//    public Page<Student> listAll(int pageNum);

    public Page<Student> getTest(
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<String> sortBy
    );
}
