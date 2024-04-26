package com.example.gestionforosv2.controller;

import com.example.gestionforosv2.entity.Feedback;
import com.example.gestionforosv2.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService service;

    @GetMapping("/lista-feedback")
    public ResponseEntity<List<Feedback>> getAllFeedback(){
        List<Feedback> lista = service.findAll();
        if(!lista.isEmpty()){
            return new ResponseEntity<>(lista, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/receptor/{id}")
    public ResponseEntity<List<Feedback>> getFeedRec(@PathVariable String cat) {
        List<Feedback> feeds = service.findSameRec(cat);
        if(!feeds.isEmpty()){
            return new ResponseEntity<>(feeds, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Feedback> conseguirFeedback(@PathVariable Integer id) {
        Optional<Feedback> post = service.findById(id);
        return post.map(c -> new ResponseEntity<>(c, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/agregar")
    public ResponseEntity<Optional<Feedback>> agregarFeed(@RequestBody Feedback post){
        Optional<Feedback> publicacion = service.save(post);
        if(publicacion.isPresent()){
            return new ResponseEntity<>(publicacion, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<HttpStatus> eliminarFeed(@PathVariable Integer id){
        Optional<Feedback> post = service.findById(id);
        if(post.isPresent()){
            service.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Feedback> actualizarFeed(@PathVariable Integer id, @RequestBody Feedback postActualizado){
        Feedback post = service.update(id, postActualizado);

        if(post != null){
            return new ResponseEntity<>(post, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
