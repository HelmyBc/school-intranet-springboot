package com.example.enetcom_intranet.service;

import com.example.enetcom_intranet.model.Subject;

import java.util.List;



public interface SubjectService {
    List<Subject> getSubjects();

    Subject getSubjectById(Integer id);

    Subject insert(Subject s);

    void updateSubject(Integer id, Subject s);

    void deleteSubject(Integer id);


}