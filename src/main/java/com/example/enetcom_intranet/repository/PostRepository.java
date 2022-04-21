package com.example.enetcom_intranet.repository;

import com.example.enetcom_intranet.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Integer> {
}
