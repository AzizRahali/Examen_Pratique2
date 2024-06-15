package com.example.examen_pratique.Repositories;

import com.example.examen_pratique.Entities.Propriete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProprieteRepository extends JpaRepository<Propriete, Long> {
}

