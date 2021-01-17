package ir.saha.repository;

import ir.saha.domain.GozareshHozoor;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the GozareshHozoor entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GozareshHozoorRepository extends JpaRepository<GozareshHozoor, Long> {

}
