package com.example.demo.controller;


import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api")
public class StudentController {
    @Autowired
    StudentService studentService;

    // Add
    @PostMapping("students")
    public String addStudent(@RequestBody Student student) {
        studentService.addStudentWithCourse(student);

        return "Data was added!";
    }

    // Get all
    @GetMapping("students")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    // Get by id
    @GetMapping("students/{id}")
    public Student findStudentById(@PathVariable(value = "id") int id) {
        return studentService.findStudentById(id);
    }

    // Edit - update
    @PatchMapping("students/{id}")
    public String editStudent(@PathVariable int id, @RequestBody Student student) {
        studentService.updateAStudent(id, student);
        return "Data was update";
    }

    // TESTING
    // Delete - not yet
    @DeleteMapping("students/{id}")
    public void deleteStudent(@PathVariable("id") int id) {
        studentService.deleteStudent(id);
    }

    // paging and sorting
    @GetMapping("test")
    public Page<Student> getStudents(
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<String> sortBy
    ) {
        return studentService.getTest(page, sortBy);
    }
}
