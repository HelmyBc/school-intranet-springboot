package com.example.enetcom_intranet.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("Teacher")
public class Teacher extends User {

    private boolean chefDep = false;

    private int depId;

    @ElementCollection
    private List<Integer> classesId = new ArrayList<>();

//    @ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
//    @JoinColumn(name = "department_id")
//    private Department department;

//
//    //@JoinTable(name = "teacher_classes",joinColumns = @JoinColumn(name = "teacherId"),inverseJoinColumns = @JoinColumn(name = "classeId"),)


//    @ManyToMany(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
//    private List<Classe> classes=new ArrayList<>();

//    public void addClass(Classe classe){
//        classes.add(classe);
//    }
//
//    public void removeClass(Classe classe){
//        classes.remove(classe);
//    }


}