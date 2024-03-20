package com.example.gestionforosv2.service;

import com.example.gestionforosv2.entity.Comentario;
import com.example.gestionforosv2.entity.Post;
import com.example.gestionforosv2.repo.ComentarioRepo;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.Date;

@Service
public class ComentarioService {

    @Autowired
    private ComentarioRepo comRepository;

    public List<Comentario> findAll() {
        return comRepository.findAll();
    }

    public List<Comentario> findSamePost(Integer id_post){
        return comRepository.findById_post(id_post);
    }

    public Optional<Comentario> findById(Integer id){
        Optional<Comentario> com = comRepository.findById(id);
        return com;
    }

    public Optional<Comentario> save(Comentario com){
        comRepository.save(com);
        Optional<Comentario> comentario =comRepository.findById(com.getId_com());
        return comentario;
    }

    public void delete(Integer id){
        comRepository.deleteById(id);
    }

    public void deleteByPostId(Integer idPost) {
        comRepository.deleteById_post(idPost);
    }

    public Comentario update(Integer id, Comentario comentario){
        Comentario com = comRepository.findById(id).orElse(null);

        if (com != null && comentario != null){
            if(comentario.getNombre() != null){
                com.setNombre(comentario.getNombre());
            }
            if(comentario.getMensaje() != null){
                com.setMensaje(comentario.getMensaje());
            }
            if(comentario.getValoracion() != null){
                com.setValoracion(comentario.getValoracion());
            }
            comRepository.save(com);
            return com;
        }else{
            return null;
        }
    }
}
