package com.example.gestionforosv2.repo;

import com.example.gestionforosv2.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Integer>{
    List<Post> findByEtiqueta(Integer etiqueta);
}
