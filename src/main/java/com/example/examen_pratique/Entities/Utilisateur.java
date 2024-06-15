package com.example.examen_pratique.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Utilisateur{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nom;
    private String prenom;
    private String adresse;
    private long telephone;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "victime")
    private List<Declaration> declarationsVictime;

    @OneToMany(mappedBy = "policier")
    private List<Declaration> declarationsPolicier;

}



