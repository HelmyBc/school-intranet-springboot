package com.example.enetcom_intranet.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue
    private int id;

    private String name;

    private String shortName;

    @ElementCollection
    private List<Integer> classesId = new ArrayList<>();

    @ElementCollection
    private List<Integer> teachersId = new ArrayList<>();

    @ElementCollection
    private List<Integer> studentsId = new ArrayList<>();

//    @OneToMany(cascade = CascadeType.ALL , fetch = FetchType.LAZY)
//    @JoinColumn(name = "department_id")
//    private List<Classe> classes;
//
//    @OneToMany(cascade = CascadeType.ALL , fetch = FetchType.LAZY)
//    @JoinColumn(name = "department_id")
//    private List<Teacher> teachers;
//
//    @OneToMany(cascade = CascadeType.ALL , fetch = FetchType.LAZY)
//    @JoinColumn(name = "department_id")
//    private List<Student> students;


}