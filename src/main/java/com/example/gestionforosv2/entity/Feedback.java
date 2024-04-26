package com.example.gestionforosv2.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="FEEDBACK")
@Entity
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", length = 255)
    private Integer id;

    @Column(name = "comentador", length = 255)
    private String comentador;

    @Column(name = "receptor", length = 255)
    private String receptor;

    @Column(name = "nombre", length = 255)
    private String nombre;

    @Column(name = "mensaje", length = 255)
    private String mensaje;

    @Column(name = "valor", length = 255)
    private Integer valor;
}
