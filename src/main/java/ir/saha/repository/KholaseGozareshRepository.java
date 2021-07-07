package ir.saha.repository;

import ir.saha.domain.KholaseGozaresh;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the KholaseGozaresh entity.
 */
@SuppressWarnings("unused")
@Repository
public interface KholaseGozareshRepository extends JpaRepository<KholaseGozaresh, Long> {

}
