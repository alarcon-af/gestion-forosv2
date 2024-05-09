package com.example.gestionforosv2.repo;

import com.example.gestionforosv2.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Integer>{
    List<Post> findByEtiqueta(Integer etiqueta);

    @Query("SELECT c FROM Post c WHERE c.titulo LIKE %:texto%")
    List<Post> findTexto(String texto);
}
