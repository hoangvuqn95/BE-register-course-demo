package com.example.demo.model;


import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "courseId")
    private int courseId;

    private String courseName;

    @JsonBackReference
    @ManyToMany(mappedBy = "likedCourses")

    private Set<Student> likedStudent = new HashSet<>();

    public Course() {
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Set<Student> getLikedStudent() {
        return likedStudent;
    }

    public void setLikedStudent(Set<Student> likedStudent) {
        this.likedStudent = likedStudent;
    }

    public void addStudent(Student student) {
        this.likedStudent.add(student);
    }

    public void removeStudent(Student student) {
        this.likedStudent.remove(student);
    }
}
