package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.InformacoesProcess;
import java.util.Optional;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the InformacoesProcess entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InformacoesProcessRepository extends JpaRepository<InformacoesProcess, Long> {
    Optional<InformacoesProcess> findByProcessInstanceId(Long processInstanceId);
}
