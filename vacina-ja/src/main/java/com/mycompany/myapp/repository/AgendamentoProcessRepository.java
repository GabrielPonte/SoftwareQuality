package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.AgendamentoProcess;
import java.util.Optional;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the AgendamentoProcess entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AgendamentoProcessRepository extends JpaRepository<AgendamentoProcess, Long> {
    Optional<AgendamentoProcess> findByProcessInstanceId(Long processInstanceId);
}
