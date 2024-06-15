package com.example.examen_pratique.Controllers;

import com.example.examen_pratique.Entities.Declaration;
import com.example.examen_pratique.Entities.Utilisateur;
import com.example.examen_pratique.Services.UtilisateurService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/utilisateurs")
public class UtilisateurController {


    private final UtilisateurService utilisateurService ;

    public UtilisateurController(UtilisateurService utilisateurService){
        this.utilisateurService = utilisateurService;
    }

    @PostMapping("/ajouterV")
    public Utilisateur ajouterVictime(@RequestBody Utilisateur victime) {
        return utilisateurService.ajouterVictime(victime);
    }

    @PostMapping("/ajouterP")
    public String ajouterPoliciers(@RequestBody List<Utilisateur> policiers) {
        return utilisateurService.ajouterPoliciers(policiers);
    }

    @PostMapping("/ajouterDeclarationEtAffecterAVictime")
    public String ajouterDeclarationEtAffecterAVictime(@RequestBody Declaration declaration, @RequestParam long telephone) {
        return utilisateurService.ajouterDeclarationEtAffecterAVictime(declaration, telephone);
    }

    @PutMapping("/affecterPAD")
    public void affecterPolicierADeclarataion(@RequestParam long idUtilisateur, @RequestParam long idDeclarataion) {
        utilisateurService.affecterPolicierADeclarataion(idUtilisateur, idDeclarataion);
    }

    @PutMapping("/traiterDeclarationAutomatique")
    public void traiterDeclarationAutomatiquement() {
        utilisateurService.traiterDeclarationAutomatiquement();
    }

    @GetMapping("/afficherDeclarationsTraitees")
    public List<Utilisateur> afficherDeclarationsTraitees() {
        return utilisateurService.afficherDeclarationsTraitees();
    }
}

