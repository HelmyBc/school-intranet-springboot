package com.example.enetcom_intranet.service;

import com.example.enetcom_intranet.model.Td;

import java.util.List;

public interface TdService {
    List<Td> getTds();

    Td getTdById(Integer id);

    Td insert(Td f);

    void updateTd(Integer id, Td f);

    void deleteTd(Integer id);

    List<Integer> addToTdsList(Integer sid, Integer tid);

    List<Integer> deleteFromTdsList(Integer sid, Integer tid);
}