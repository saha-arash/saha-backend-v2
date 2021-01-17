package ir.saha.repository;

import ir.saha.domain.GardeshKar;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the GardeshKar entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GardeshKarRepository extends JpaRepository<GardeshKar, Long> {

}
