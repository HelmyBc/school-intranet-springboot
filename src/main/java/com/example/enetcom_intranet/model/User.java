package com.example.enetcom_intranet.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type")

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private int cin;
    private int phone;
    private String imageUrl = "https://icon-library.com/images/default-user-icon/default-user-icon-13.jpg";

    //all logics are done for users
    @ElementCollection
    private List<Integer> postsId = new ArrayList<>();

    @Column(name = "user_type", insertable = false, updatable = false)
    protected String userType;

    public String getUserType() {
        return userType;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }



}