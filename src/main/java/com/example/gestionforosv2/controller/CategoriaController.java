package com.example.gestionforosv2.controller;

import com.example.gestionforosv2.entity.Categoria;
import com.example.gestionforosv2.service.CategoriaService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    @GetMapping("/lista-categorias")
    public ResponseEntity<List<Categoria>> getAllCategorias() {
        List<Categoria> categorias =service.findAll();
        if(!categorias.isEmpty()){
            return new ResponseEntity<>(categorias, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> conseguirCategoria(@PathVariable Integer id) {
        Optional<Categoria> cate = service.findById(id);
        return cate.map(c -> new ResponseEntity<>(c, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/agregar")
    public ResponseEntity<Optional<Categoria>> agregarUsuario(@RequestBody Categoria cate){
        Optional<Categoria> categoria = service.save(cate);
        if(categoria.isPresent()){
            return new ResponseEntity<>(categoria, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<HttpStatus> eliminarCategoria(@PathVariable Integer id){
        Optional<Categoria> categoria = service.findById(id);
        if(categoria.isPresent()){
            service.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Categoria> actualizarCategoria(@PathVariable Integer id, @RequestBody Categoria categoriaActualizado){
        Categoria cate = service.updateUsuario(id, categoriaActualizado);

        if(cate != null){
            return new ResponseEntity<>(cate, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
