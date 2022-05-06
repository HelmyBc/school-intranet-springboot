package com.example.enetcom_intranet.service;

import com.example.enetcom_intranet.model.Course;
import com.example.enetcom_intranet.model.Subject;

import java.util.List;


public interface CourseService {
    List<Course> getCourses();

    Course getCourseById(Integer id);

    Course insert(Course c);

    void updateCourse(Integer id, Course c);

    void deleteCourse(Integer id);

    List<Integer> addToCoursesList(Integer sid, Integer cid);

    List<Integer> deleteFromCoursesList(Integer sid, Integer cid);
}