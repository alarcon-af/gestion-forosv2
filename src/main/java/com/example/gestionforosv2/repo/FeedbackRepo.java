package com.example.gestionforosv2.repo;

import com.example.gestionforosv2.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FeedbackRepo extends JpaRepository<Feedback, Integer>{

    @Query("SELECT c FROM Feedback c WHERE c.receptor = :receptor")
    List<Feedback> findByReceptor(String receptor);
}
