package ir.saha.repository;

import ir.saha.domain.RafeIradat;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the RafeIradat entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RafeIradatRepository extends JpaRepository<RafeIradat, Long> {

}
