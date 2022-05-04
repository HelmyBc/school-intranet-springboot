package com.example.enetcom_intranet.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("Student")
public class Student extends User {

    private int classeId;
    private int depId;

//    logics to do
//    @ElementCollection
//    private List<Integer> teachersId = new ArrayList<>();
//
//
//    @ManyToOne(cascade= CascadeType.ALL,fetch = FetchType.LAZY)
//    @JoinColumn(name = "department_id")
//    private Department department;
//
//    @ManyToMany(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
//    private List<Classe> classesId=new ArrayList<>();


}