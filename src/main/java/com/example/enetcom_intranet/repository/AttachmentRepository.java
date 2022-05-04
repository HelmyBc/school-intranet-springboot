package com.example.enetcom_intranet.repository;


import com.example.enetcom_intranet.model.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, Integer> {
}