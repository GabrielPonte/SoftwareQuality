package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Agendamento;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Agendamento entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {}
