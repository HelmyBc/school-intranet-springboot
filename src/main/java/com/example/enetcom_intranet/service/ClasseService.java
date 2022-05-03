package com.example.enetcom_intranet.service;

import com.example.enetcom_intranet.model.Classe;
import com.example.enetcom_intranet.model.Post;

import java.util.List;

public interface ClasseService {

    List<Classe> getClasses();

    Classe getClasseById(Integer id);

    Classe insert(Classe p);

    void updateClasse(Integer id, Classe p);

    void deleteClasse(Integer id);

    List<Integer> addToClasseStudentsList(Integer sid, Integer cid);

    List<Integer> deleteFromClasseStudentsList(Integer sid, Integer cid);

    List<Integer> addToClasseTeachersList(Integer tid, Integer cid);

    List<Integer> deleteFromClasseTeachersList(Integer tid, Integer cid);


}