package com.example.gestionforosv2.controller;

import com.example.gestionforosv2.entity.Comentario;
import com.example.gestionforosv2.service.ComentarioService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Date;

@RestController
@RequestMapping("/comentario")
public class ComentarioController {

    @Autowired
    private ComentarioService service;

    @GetMapping("/lista-comentarios")
    public ResponseEntity<List<Comentario>> getAllComentarios() {
        List<Comentario> comentarios =service.findAll();
        if(!comentarios.isEmpty()){
            return new ResponseEntity<>(comentarios, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/lista-comentarios/{cat}")
    public ResponseEntity<List<Comentario>> getPostsCat(@PathVariable Integer cat) {
        List<Comentario> posts = service.findSamePost(cat);
        if(!posts.isEmpty()){
            return new ResponseEntity<>(posts, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comentario> conseguirComentario(@PathVariable Integer id) {
        Optional<Comentario> com = service.findById(id);
        return com.map(c -> new ResponseEntity<>(c, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/agregar")
    public ResponseEntity<Optional<Comentario>> agregarComentario(@RequestBody Comentario com){
        Optional<Comentario> comentario = service.save(com);
        if(comentario.isPresent()){
            return new ResponseEntity<>(comentario, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<HttpStatus> eliminarComentario(@PathVariable Integer id){
        Optional<Comentario> com = service.findById(id);
        if(com.isPresent()){
            service.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/borrar-por-post/{idPost}")
    public ResponseEntity<?> borrarComentariosPorPost(@PathVariable Integer idPost) {
        try {
            service.deleteByPostId(idPost);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al borrar comentarios por post");
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Comentario> actualizarComentario(@PathVariable Integer id, @RequestBody Comentario comActualizado){
        Comentario com = service.update(id, comActualizado);

        if(com != null){
            return new ResponseEntity<>(com, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
