package com.example.gestionforosv2.service;

import com.example.gestionforosv2.entity.Feedback;
import com.example.gestionforosv2.repo.FeedbackRepo;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepo repo;

    public List<Feedback> findAll(){ return repo.findAll(); }

    public List<Feedback> findSameRec(String id){
        return repo.findByReceptor(id);
    }

    public Optional<Feedback> findById(Integer id){
        Optional<Feedback> feed = repo.findById(id);
        return feed;
    }

    public Optional<Feedback> save(Feedback feed){
        repo.save(feed);
        Optional<Feedback> feedSaved = repo.findById(feed.getId());
        return feedSaved;
    }

    public void delete(Integer id){
        repo.deleteById(id);
    }

    public Feedback update(Integer id, Feedback feed){
        Feedback feed2 = repo.findById(id).orElse(null);

        if(feed2!=null && feed != null){
            if(feed.getComentador()!=null){
                feed2.setComentador(feed.getComentador());
            }
            if(feed.getReceptor()!=null){
                feed2.setReceptor(feed.getReceptor());
            }
            if(feed.getNombre()!=null){
                feed2.setNombre(feed.getNombre());
            }
            if(feed.getMensaje()!=null){
                feed2.setMensaje(feed.getMensaje());
            }
            if(feed.getValor()!=null){
                feed2.setValor(feed.getValor());
            }
            repo.save(feed2);
            return feed2;
        }else{
            return null;
        }
    }
}
