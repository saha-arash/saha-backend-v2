package ir.saha.repository;

import ir.saha.domain.Nameh;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Nameh entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NamehRepository extends JpaRepository<Nameh, Long> {

}
