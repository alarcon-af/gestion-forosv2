package com.example.gestionforosv2.service;

import com.example.gestionforosv2.entity.Categoria;
import com.example.gestionforosv2.repo.CategoriaRepo;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepo cateRepository;

    public List<Categoria> findAll() {
        return cateRepository.findAll();
    }

    public Optional<Categoria> findById(Integer id){
        Optional<Categoria> categoria = cateRepository.findById(id);
        return categoria;
    }

    public Optional<Categoria> save(Categoria categoria){
        cateRepository.save(categoria);
        Optional<Categoria> usuario =cateRepository.findById(categoria.getId_categoria());
        return usuario;
    }

    public void delete(Integer id){
        cateRepository.deleteById(id);
    }

    public Categoria updateUsuario(Integer id, Categoria categoria){
        Categoria cate = cateRepository.findById(id).orElse(null);

        if (cate != null && categoria != null){
            if(categoria.getTitulo() != null){
                cate.setTitulo(categoria.getTitulo());
            }
            if(categoria.getId_materia() != null){
                cate.setId_materia(categoria.getId_materia());
            }

            cateRepository.save(cate);
            return cate;
        }else{
            return null;
        }
    }
}
