package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.LocalDesejado;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the LocalDesejado entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LocalDesejadoRepository extends JpaRepository<LocalDesejado, Long> {}
