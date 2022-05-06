package com.example.enetcom_intranet.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //in the future I need to remove the username and profImage and uid because now it has a user attribute
    private String description;
    private String username;
    private int uid;
    private String profImage;
    private int imageId;
    private String imageUrl;


}