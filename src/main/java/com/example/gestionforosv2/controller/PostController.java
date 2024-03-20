package com.example.gestionforosv2.controller;

import com.example.gestionforosv2.entity.Post;
import com.example.gestionforosv2.service.PostService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Date;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService service;

    @GetMapping("/lista-posts")
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> postss =service.findAll();
        if(!postss.isEmpty()){
            return new ResponseEntity<>(postss, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/lista-posts/{cat}")
    public ResponseEntity<List<Post>> getPostsCat(@PathVariable Integer cat) {
        List<Post> posts = service.findSameCat(cat);
        if(!posts.isEmpty()){
            return new ResponseEntity<>(posts, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> conseguirPost(@PathVariable Integer id) {
        Optional<Post> post = service.findById(id);
        return post.map(c -> new ResponseEntity<>(c, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/agregar")
    public ResponseEntity<Optional<Post>> agregarPost(@RequestBody Post post){
        Optional<Post> publicacion = service.save(post);
        if(publicacion.isPresent()){
            return new ResponseEntity<>(publicacion, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<HttpStatus> eliminarPost(@PathVariable Integer id){
        Optional<Post> post = service.findById(id);
        if(post.isPresent()){
            service.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Post> actualizarPost(@PathVariable Integer id, @RequestBody Post postActualizado){
        Post post = service.update(id, postActualizado);

        if(post != null){
            return new ResponseEntity<>(post, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
