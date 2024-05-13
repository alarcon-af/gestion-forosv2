package com.example.gestionforosv2.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="POST")
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_post", length = 255)
    private Integer id_post;

    @Column(name = "id_op", length = 255)
    private String id_op;

    @JoinColumn(name = "etiqueta", referencedColumnName = "id_categoria")
    private Integer etiqueta;

    @Column(name = "titulo", length = 255)
    private String titulo;

    @Column(name = "contenido", length = 255)
    private String contenido;

    @Column(name = "material", length = 255)
    private String material;

    @Column(name = "video")
    private String video;

    @Column(name = "valoracion", length = 255)
    private Integer valoracion;

    @Column(name = "fecha", length = 255)
    private Date fecha;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "tags", length = 255)
    private String tags;
}
