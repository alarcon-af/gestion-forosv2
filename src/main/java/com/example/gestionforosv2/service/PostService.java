package com.example.gestionforosv2.service;

import com.example.gestionforosv2.entity.Post;
import com.example.gestionforosv2.repo.PostRepo;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.Date;
import java.util.stream.Collectors;

@Service
public class PostService {

    @Autowired
    private PostRepo postRepository;

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public List<Post> findSameCat(Integer cat){
        return postRepository.findByEtiqueta(cat);
    }

    public Optional<Post> findById(Integer id){
        Optional<Post> post = postRepository.findById(id);
        return post;
    }

    public List<Post> findTitulo(String texto){
        return postRepository.findTexto(texto);
    }

    public Optional<Post> save(Post post){
        postRepository.save(post);
        Optional<Post> publicacion =postRepository.findById(post.getId_post());
        return publicacion;
    }

    public void delete(Integer id){
        postRepository.deleteById(id);
    }

    public Post update(Integer id, Post publicacion){
        Post post = postRepository.findById(id).orElse(null);

        if (post != null && publicacion != null){
            if(publicacion.getId_op() != null){
                post.setId_op(publicacion.getId_op());
            }
            if(publicacion.getEtiqueta() != null){
                post.setEtiqueta(publicacion.getEtiqueta());
            }
            if(publicacion.getTitulo() != null){
                post.setTitulo(publicacion.getTitulo());
            }
            if(publicacion.getContenido() != null){
                post.setContenido(publicacion.getContenido());
            }
            if(publicacion.getMaterial() != null){
                post.setMaterial(publicacion.getMaterial());
            }
            if(publicacion.getVideo() != null){
                    post.setVideo(publicacion.getVideo());
            }
            if(publicacion.getValoracion() != null){
                post.setValoracion(publicacion.getValoracion());
            }
            if(publicacion.getFecha() != null){
                post.setFecha(publicacion.getFecha());
            }
            if(publicacion.getNombre() != null){
                post.setNombre(publicacion.getNombre());
            }
            if(publicacion.getTags() != null){
                post.setTags(publicacion.getTags());
            }
            postRepository.save(post);
            return post;
        }else{
            return null;
        }
    }

}
