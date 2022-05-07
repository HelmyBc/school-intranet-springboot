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
public class Classe {

    @Id
    @GeneratedValue
    private int id;

//    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(name = "department_id")
//    private Department department;


    private int level;
    private int groupe;
    private int depId;
    private String name;

    @ElementCollection
    private List<Integer> teachersId = new ArrayList<>();

    @ElementCollection
    private List<Integer> studentsId = new ArrayList<>();

    //will be added automatically after creating the subject with depIds and level
    //So I need to add depId attribute to subject
    @ElementCollection
    private List<Integer> subjectsId = new ArrayList<>();



//    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinTable(name = "class_teachers",
//            joinColumns = @JoinColumn(name = "classe_id"),
//            inverseJoinColumns = @JoinColumn(name = "teacher_id")
//    )
//    private List<Teacher> teachers = new ArrayList<>();
//    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinTable(name = "class_students",
//            joinColumns = @JoinColumn(name = "classe_id"),
//            inverseJoinColumns = @JoinColumn(name = "student_id")
//    )
//    private List<Student> students = new ArrayList<>();
//
//    public void setName() {
//        this.name = level + " " + groupe;
//    }
//
//    public void addTeacher(Teacher teacher) {
//        teachers.add(teacher);
//    }
//
//    public void deleteTeacher(Teacher teacher) {
//        teachers.remove(teacher);
//    }
//
//    public void addStudent(Student student) {
//        students.add(student);
//    }
//
//    public void deleteStudent(Student student) {
//        students.remove(student);
//    }


}