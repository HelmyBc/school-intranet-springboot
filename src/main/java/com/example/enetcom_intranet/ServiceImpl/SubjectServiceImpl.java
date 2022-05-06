package com.example.enetcom_intranet.ServiceImpl;

import com.example.enetcom_intranet.model.Subject;
import com.example.enetcom_intranet.repository.SubjectRepository;
import com.example.enetcom_intranet.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class SubjectServiceImpl implements SubjectService {
    @Autowired
    SubjectRepository subjectRepository;


    @Override
    public List<Subject> getSubjects() {
        List<Subject> subjects = new ArrayList<>();
        subjectRepository.findAll().forEach(subjects::add);
        return subjects;
    }

    @Override
    public Subject getSubjectById(Integer id) {
        return subjectRepository.findById(id).get();
    }

    @Override
    public Subject insert(Subject post) {
        return subjectRepository.save(post);
    }

    @Override
    public void updateSubject(Integer id, Subject feature) {
        Subject subjectFromDb = subjectRepository.findById(id).get();
        System.out.println(subjectFromDb.toString());
        subjectFromDb.setName(feature.getName());
        subjectFromDb.setCoursesIds(feature.getCoursesIds());
        subjectFromDb.setTdsIds(feature.getTdsIds());


        subjectRepository.save(subjectFromDb);
    }

    @Override
    public void deleteSubject(Integer id) {
        subjectRepository.deleteById(id);
    }

}
