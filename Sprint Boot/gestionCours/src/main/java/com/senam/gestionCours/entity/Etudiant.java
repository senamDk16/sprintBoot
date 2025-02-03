package com.senam.gestionCours.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Etudiant {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Temporal(value = TemporalType.DATE)
    private Date dateNaissance;



}
