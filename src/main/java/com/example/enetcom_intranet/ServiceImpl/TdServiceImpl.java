package com.example.enetcom_intranet.ServiceImpl;

import com.example.enetcom_intranet.model.Subject;
import com.example.enetcom_intranet.model.Td;
import com.example.enetcom_intranet.repository.SubjectRepository;
import com.example.enetcom_intranet.repository.TdRepository;
import com.example.enetcom_intranet.service.TdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class TdServiceImpl implements TdService {
    @Autowired
    TdRepository tdRepository;

    @Autowired
    SubjectRepository subjectRepository;


    @Override
    public List<Td> getTds() {
        List<Td> tds = new ArrayList<>();
        tdRepository.findAll().forEach(tds::add);
        return tds;
    }

    @Override
    public Td getTdById(Integer id) {
        return tdRepository.findById(id).get();
    }

    @Override
    public Td insert(Td td) {
        return tdRepository.save(td);
    }

    @Override
    public void updateTd(Integer id, Td td) {
        Td tdFromDb = tdRepository.findById(id).get();
        System.out.println(tdFromDb);
        tdFromDb.setName(td.getName());
        tdFromDb.setAttachmentId(td.getAttachmentId());
        tdFromDb.setSubjectId(td.getSubjectId());
        tdRepository.save(tdFromDb);
    }

    @Override
    public void deleteTd(Integer id) {
        tdRepository.deleteById(id);
    }

    public List<Integer> addToTdsList(Integer sid, Integer tid) {
        Subject subjectFromDb = subjectRepository.findById(sid).get();
        List<Integer> oldList = subjectFromDb.getTdsIds();
        oldList.add(tid);
        subjectFromDb.setTdsIds(oldList);
        subjectRepository.save(subjectFromDb);
        return oldList;
    }

    @Override
    public List<Integer> deleteFromTdsList(Integer sid, Integer tid) {
        Subject subjectFromDb = subjectRepository.findById(sid).get();
        List<Integer> oldList = subjectFromDb.getTdsIds();
        oldList.remove(tid);
        subjectFromDb.setTdsIds(oldList);
        subjectRepository.save(subjectFromDb);
        return oldList;

    }
}
