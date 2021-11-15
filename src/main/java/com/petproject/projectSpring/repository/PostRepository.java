package com.petproject.projectSpring.repository;

import com.petproject.projectSpring.models.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {



}
