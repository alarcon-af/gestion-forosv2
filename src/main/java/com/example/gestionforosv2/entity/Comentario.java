package com.example.gestionforosv2.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="COMENTARIO")
@Entity
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_com", length = 255)
    private Integer id_com;

    @Column(name = "id_comentador", length = 255)
    private Integer id_comentador;

    @JoinColumn(name = "id_post", referencedColumnName = "id_post")
    private Integer id_post;

    @Column(name = "nombre", length = 255)
    private String nombre;

    @Column(name = "mensaje", length = 255)
    private String mensaje;

    @Column(name = "valoracion", length = 255)
    private Integer valoracion;

    @Column(name = "fecha", length = 255)
    private Date fecha;
}
