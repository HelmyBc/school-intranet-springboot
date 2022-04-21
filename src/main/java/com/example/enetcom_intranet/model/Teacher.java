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
@DiscriminatorValue("Teacher")
public class Teacher extends User {

    private boolean chefDep = false;

//    @ManyToOne
//    @JoinColumn(name = "department_id")
//    private Department department;
//
//    @ManyToMany
//    @JoinTable(name = "teacher_classes",joinColumns = @JoinColumn(name = "teacherId"),inverseJoinColumns = @JoinColumn(name = "classeId"))
//    private List<Classe> classes;


}