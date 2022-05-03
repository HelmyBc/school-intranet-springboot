package com.example.enetcom_intranet.ServiceImpl;

import com.example.enetcom_intranet.model.Classe;
import com.example.enetcom_intranet.repository.ClasseRepository;
import com.example.enetcom_intranet.service.ClasseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ClasseServiceImpl implements ClasseService {
    @Autowired
    ClasseRepository classeRepository;


    @Override
    public List<Classe> getClasses() {
        List<Classe> posts = new ArrayList<>();
        classeRepository.findAll().forEach(posts::add);
        return posts;
    }

    @Override
    public Classe getClasseById(Integer id) {
        return classeRepository.findById(id).get();
    }

    @Override
    public Classe insert(Classe post) {
        return classeRepository.save(post);
    }

    @Override
    public void updateClasse(Integer id, Classe classe) {
        Classe postFromDb = classeRepository.findById(id).get();
        postFromDb.setLevel(classe.getLevel());
        postFromDb.setDepId(classe.getDepId());
        postFromDb.setGroupe(classe.getGroupe());
        postFromDb.setName(classe.getName());
        System.out.println(postFromDb.toString());
        classeRepository.save(postFromDb);
    }

    @Override
    public void deleteClasse(Integer id) {
        classeRepository.deleteById(id);
    }

    @Override
    public List<Integer> addToClasseStudentsList(Integer sid, Integer cid) {
        Classe classe = classeRepository.findById(cid).get();
        List<Integer> oldList = classe.getStudentsId();
        oldList.add(sid);
        classe.setStudentsId(oldList);
        classeRepository.save(classe);
        return oldList;
    }

    @Override
    public List<Integer> deleteFromClasseStudentsList(Integer sid, Integer cid) {
        Classe classe = classeRepository.findById(cid).get();
        List<Integer> oldList = classe.getStudentsId();
        oldList.remove(sid);
        classe.setStudentsId(oldList);
        classeRepository.save(classe);
        return oldList;
    }

    @Override
    public List<Integer> addToClasseTeachersList(Integer tid, Integer cid) {
        Classe classe = classeRepository.findById(cid).get();
        List<Integer> oldList = classe.getTeachersId();
        oldList.add(tid);
        classe.setTeachersId(oldList);
        classeRepository.save(classe);
        return oldList;
    }

    @Override
    public List<Integer> deleteFromClasseTeachersList(Integer tid, Integer cid) {
        Classe classe = classeRepository.findById(cid).get();
        List<Integer> oldList = classe.getTeachersId();
        oldList.remove(tid);
        classe.setTeachersId(oldList);
        classeRepository.save(classe);
        return oldList;
    }


}
