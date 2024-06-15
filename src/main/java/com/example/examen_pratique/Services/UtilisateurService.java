package com.example.examen_pratique.Services;

import com.example.examen_pratique.Entities.Declaration;
import com.example.examen_pratique.Entities.Role;
import com.example.examen_pratique.Entities.Utilisateur;
import com.example.examen_pratique.Repositories.DeclarationRepository;
import com.example.examen_pratique.Repositories.UtilisateurRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class UtilisateurService {

    private UtilisateurRepository utilisateurRepository;

    private DeclarationRepository declarationRepository;

    public UtilisateurService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
        this.declarationRepository = declarationRepository;
    }
    public Utilisateur ajouterVictime(Utilisateur victime) {
        if (victime.getRole() == Role.POLICIER) {
            return null;
        }
        return utilisateurRepository.save(victime);
    }

    public String ajouterPoliciers(List<Utilisateur> policiers) {
        int count = 0;
        for (Utilisateur policier : policiers) {
            if (policier.getRole() == Role.POLICIER) {
                utilisateurRepository.save(policier);
                count++;
            }
        }
        return count + " policiers sont ajoutés avec succès !";
    }

    public String ajouterDeclarationEtAffecterAVictime(Declaration declaration, long telephone) {
        Utilisateur victime = utilisateurRepository.findByTelephone(telephone);
        if (victime != null) {
            declaration.setVictime(victime);
            declarationRepository.save(declaration);
            return "Déclaration ajoutée et affectée à la victime " + victime.getNom();
        }
        return "Victime non trouvée";
    }

    public void affecterPolicierADeclarataion(long idUtilisateur, long idDeclarataion) {
        Declaration declaration = declarationRepository.findById(idDeclarataion).orElse(null);
        Utilisateur policier = utilisateurRepository.findById(idUtilisateur).orElse(null);
        if (declaration != null && policier != null) {
            declaration.setPolicier(policier);
            declarationRepository.save(declaration);
        }
    }

    public void traiterDeclarationAutomatiquement() {
        List<Declaration> declarations = declarationRepository.findAll();
        for (Declaration declaration : declarations) {
            if (!declaration.getEstTraitee() && declaration.getDateDeclaration().isBefore(LocalDate.now().minusMonths(1))) {
                declaration.setEstTraitee(true);
                declaration.setDateTraitement(LocalDate.now());
                declarationRepository.save(declaration);
            }
        }
    }

    public List<Utilisateur> afficherDeclarationsTraitees() {
        List<Declaration> declarations = declarationRepository.findByEstTraiteeTrue();
        return declarations.stream()
                .map(Declaration::getPolicier)
                .distinct()
                .collect(Collectors.toList());
    }
}

