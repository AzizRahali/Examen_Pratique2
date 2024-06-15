package com.example.examen_pratique.Repositories;

import com.example.examen_pratique.Entities.Declaration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeclarationRepository extends JpaRepository<Declaration, Long> {
    List<Declaration> findByEstTraiteeTrue();
}

