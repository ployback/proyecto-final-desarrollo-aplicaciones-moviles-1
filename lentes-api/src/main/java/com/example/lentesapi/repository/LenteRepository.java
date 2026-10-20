package com.example.lentesapi.repository;

import com.example.lentesapi.entity.Lente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LenteRepository extends JpaRepository<Lente, Long> {
}
