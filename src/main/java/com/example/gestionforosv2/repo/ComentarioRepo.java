package com.example.gestionforosv2.repo;

import com.example.gestionforosv2.entity.Comentario;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ComentarioRepo extends JpaRepository<Comentario, Integer>{

    @Query("SELECT c FROM Comentario c WHERE c.id_post = :id_post")
    List<Comentario> findById_post(Integer id_post);

    @Modifying
    @Transactional
    @Query("DELETE FROM Comentario c WHERE c.id_post = :id_post")
    void deleteById_post(@Param("id_post") Integer id_post);
}
