package com.example.gestionforosv2.repo;

import com.example.gestionforosv2.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Integer>{
    List<Post> findByEtiqueta(Integer etiqueta);

    @Query("SELECT p FROM Post p WHERE p.titulo LIKE %:texto% OR p.tags LIKE %:texto%")
    List<Post> findTexto(String texto);
}
