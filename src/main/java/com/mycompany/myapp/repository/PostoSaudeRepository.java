package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.PostoSaude;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the PostoSaude entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PostoSaudeRepository extends JpaRepository<PostoSaude, Long> {}
