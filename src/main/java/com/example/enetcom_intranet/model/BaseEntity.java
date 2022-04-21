package com.example.enetcom_intranet.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
//    private int id;
//
//    @Column(name = "name")
//    private String name;
//
//    @Column(name = "deleted", columnDefinition = "Bit(1) default false")
//    private boolean deleted = false;


//    @Column(name = "CreatedBy", nullable = false, updatable = false)
//    private String CreatedBy="Admin";

    @Column(name = "createdTime", nullable = false, updatable = false)
    private Date createdTime;

//    @Column(name = "LastModifiedBy")
//    private String LastModifiedBy="Admin";

    @Column(name = "lastModifiedTime")
    private Date LastModifiedTime;

    @PrePersist
    protected void prePersist() {
        if (this.createdTime == null) createdTime = new Date();
        if (this.LastModifiedTime == null) LastModifiedTime = new Date();
    }

    @PreUpdate
    protected void preUpdate() {
        this.LastModifiedTime = new Date();
    }

    @PreRemove
    protected void preRemove() {
        this.LastModifiedTime = new Date();
    }
}