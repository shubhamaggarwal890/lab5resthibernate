package com.example.erp.dao;

import com.example.erp.bean.Courses;

import java.util.List;

public interface CourseDao {

    boolean registerCourse(Courses course);
    List<Courses> getCourses();
    Courses getCourseByID(Integer id);
}
