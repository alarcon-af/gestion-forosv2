package com.example.gestionforosv2.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="CATEGORIA")
@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria", length = 255)
    private Integer id_categoria;

    @Column(name = "titulo", length = 255)
    private String titulo;

    @Column(name = "id_materia", length = 255)
    private Integer id_materia;
}
