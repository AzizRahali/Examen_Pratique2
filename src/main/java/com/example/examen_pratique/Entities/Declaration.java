package com.example.examen_pratique.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Declaration {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDate dateDeclaration;
    private String description;
    private Boolean estTraitee;
    private LocalDate dateTraitement;

    @ManyToOne
    @JoinColumn(name = "victime_id")
    private Utilisateur victime;

    @ManyToOne
    @JoinColumn(name = "policier_id")
    private Utilisateur policier;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "propriete_id")
    private Propriete propriete;

}

