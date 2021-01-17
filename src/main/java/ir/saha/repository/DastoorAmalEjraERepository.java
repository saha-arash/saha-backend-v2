package ir.saha.repository;

import ir.saha.domain.DastoorAmalEjraE;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the DastoorAmalEjraE entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DastoorAmalEjraERepository extends JpaRepository<DastoorAmalEjraE, Long> {

}
