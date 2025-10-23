/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ejerpract1.domain;

import jakarta.persistence.*;

import java.io.Serializable;
import lombok.*;
/**
 *
 * @author Juan
 */

@Entity
@Table(name = "queja")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Queja implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreCliente;

    private String email;

    private String telefono;

    @Enumerated(EnumType.STRING)
    private TipoQueja tipo = TipoQueja.CONSULTA;

    private String asunto;

    @Column(columnDefinition = "TEXT")
    private String mensaje;

    private Boolean tratado = false;

    public enum TipoQueja {
        QUEJA, SUGERENCIA, CONSULTA
    }
}
