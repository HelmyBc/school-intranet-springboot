package com.example.enetcom_intranet.ServiceImpl;

import com.example.enetcom_intranet.model.Course;
import com.example.enetcom_intranet.model.Subject;
import com.example.enetcom_intranet.model.User;
import com.example.enetcom_intranet.repository.CourseRepository;
import com.example.enetcom_intranet.repository.SubjectRepository;
import com.example.enetcom_intranet.service.CourseService;
import com.example.enetcom_intranet.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    CourseRepository courseRepository;

    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    SubjectService subjectService;


    @Override
    public List<Course> getCourses() {
        List<Course> courses = new ArrayList<>();
        courseRepository.findAll().forEach(courses::add);
        return courses;
    }

    @Override
    public Course getCourseById(Integer id) {
        return courseRepository.findById(id).get();
    }

    @Override
    public Course insert(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public void updateCourse(Integer id, Course feature) {
        Course courseFromDb = courseRepository.findById(id).get();
        System.out.println(courseFromDb);
        courseFromDb.setName(feature.getName());
        courseFromDb.setAttachmentId(feature.getAttachmentId());
        courseFromDb.setPdfUrl(feature.getPdfUrl());
        courseFromDb.setSubjectId(feature.getSubjectId());
        courseRepository.save(courseFromDb);
    }

    @Override
    public void deleteCourse(Integer id) {
        courseRepository.deleteById(id);
    }

    @Override
    public List<Integer> addToCoursesList(Integer sid, Integer cid) {
        Subject subjectFromDb = subjectRepository.findById(sid).get();
        List<Integer> oldList = subjectFromDb.getCoursesIds();
        oldList.add(cid);
        subjectFromDb.setCoursesIds(oldList);
        subjectService.updateSubject(sid,subjectFromDb);
        return oldList;
    }

    @Override
    public List<Integer> deleteFromCoursesList(Integer sid, Integer cid) {
        Subject subjectFromDb = subjectRepository.findById(sid).get();
        List<Integer> oldList = subjectFromDb.getCoursesIds();
        oldList.remove(cid);
        subjectFromDb.setCoursesIds(oldList);
        subjectService.updateSubject(sid,subjectFromDb);
        return oldList;

    }
}
