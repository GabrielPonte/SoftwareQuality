package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.TipoInformacao;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the TipoInformacao entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TipoInformacaoRepository extends JpaRepository<TipoInformacao, Long> {}
