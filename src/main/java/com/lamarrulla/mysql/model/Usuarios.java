package com.lamarrulla.mysql.model;

import lombok.Data;

import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
@Data
@AttributeOverride(name = "id", column = @Column(name = "idUsuario"))
public class Usuarios {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private Integer idCurso;
}
