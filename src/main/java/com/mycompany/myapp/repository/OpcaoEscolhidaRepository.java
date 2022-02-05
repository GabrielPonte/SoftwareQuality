package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.OpcaoEscolhida;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the OpcaoEscolhida entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OpcaoEscolhidaRepository extends JpaRepository<OpcaoEscolhida, Long> {}
