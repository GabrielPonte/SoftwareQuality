package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.RecebaEmail;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the RecebaEmail entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RecebaEmailRepository extends JpaRepository<RecebaEmail, Long> {}
