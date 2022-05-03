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

public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String email;
    private int cin;
    private int phone;
    private String imageUrl = "https://icon-library.com/images/default-user-icon/default-user-icon-13.jpg";

    //all logics are done for users
    @ElementCollection
    private List<Integer> postsId = new ArrayList<>();

//    @OneToMany(cascade = CascadeType.ALL , fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    private List<Post> posts;

}