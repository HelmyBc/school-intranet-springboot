package com.example.enetcom_intranet.repository;

import com.example.enetcom_intranet.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course,Integer> {
}
