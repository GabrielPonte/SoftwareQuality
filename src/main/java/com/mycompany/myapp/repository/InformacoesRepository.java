package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Informacoes;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Informacoes entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InformacoesRepository extends JpaRepository<Informacoes, Long> {}
