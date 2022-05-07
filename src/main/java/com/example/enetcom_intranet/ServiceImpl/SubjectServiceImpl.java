package com.example.enetcom_intranet.ServiceImpl;

import com.example.enetcom_intranet.model.Classe;
import com.example.enetcom_intranet.model.Subject;
import com.example.enetcom_intranet.repository.SubjectRepository;
import com.example.enetcom_intranet.service.ClasseService;
import com.example.enetcom_intranet.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class SubjectServiceImpl implements SubjectService {
    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    ClasseService classeService;


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
    public Subject insert(Subject subject) {
        return subjectRepository.save(subject);
    }

    @Override
    public void updateSubject(Integer id, Subject subject) {
        Subject subjectFromDb = subjectRepository.findById(id).get();

        subjectFromDb.setName(subject.getName());
        subjectFromDb.setCoursesIds(subject.getCoursesIds());
        subjectFromDb.setTdsIds(subject.getTdsIds());
        subjectFromDb.setLevel(subject.getLevel());
        subjectFromDb.setDepIds(subject.getDepIds());



        List<Classe> classes = classeService.getClasses();

        // we need to delete subject from previous classes
        for (int i = 0; i < classes.size(); i++) {
            Classe classeI = classes.get(i);
            List<Integer> classeISubjects = classeI.getSubjectsId();
            if (classeISubjects.contains(id)) {
                classeISubjects.remove(id);
                classeI.setSubjectsId(classeISubjects);
            }
        }


        //While updating a subject having a depId and level
        //We need to add the subject to every classe having the same depId and level

        List<Integer> subjectDepIds = subject.getDepIds();
        for (int i = 0; i < classes.size(); i++) {
            Classe classeI = classes.get(i);
            if (subjectDepIds.contains(classeI.getDepId()) && classeI.getLevel() == subject.getLevel()) {
                List<Integer> subjectsIds = classeI.getSubjectsId();
                subjectsIds.add(id);
                classeI.setSubjectsId(subjectsIds);
                classeService.updateClasse(classeI.getId(), classeI);
            }
        }

        System.out.println(subjectFromDb);
        subjectRepository.save(subjectFromDb);
    }

    @Override
    public void deleteSubject(Integer id) {
        subjectRepository.deleteById(id);
    }

}
