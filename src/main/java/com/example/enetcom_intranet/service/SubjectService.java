package com.example.enetcom_intranet.service;

import com.example.enetcom_intranet.model.Subject;

import java.util.List;



public interface SubjectService {
    List<Subject> getSubjects();

    Subject getSubjectById(Integer id);

    Subject insert(Subject f);

    void updateSubject(Integer id, Subject f);

    void deleteSubject(Integer id);
}