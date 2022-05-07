package com.example.enetcom_intranet.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int level=0;


    //I need to add depId attribute to subject
    //So the subject will be added automatically to classes having the same depId and level
    @ElementCollection
    private List<Integer> depIds = new ArrayList<>();

    private String name;

    @ElementCollection
    private List<Integer> coursesIds = new ArrayList<>();

    @ElementCollection
    private List<Integer> tdsIds = new ArrayList<>();


}