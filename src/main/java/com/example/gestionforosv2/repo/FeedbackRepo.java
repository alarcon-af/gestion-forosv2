package com.example.gestionforosv2.repo;

import com.example.gestionforosv2.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedbackRepo extends JpaRepository<Feedback, Integer>{

    List<Feedback> findByReceptor(String receptor);
}
