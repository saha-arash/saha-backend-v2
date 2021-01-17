package ir.saha.repository;

import ir.saha.domain.ChekideGardeshKar;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ChekideGardeshKar entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ChekideGardeshKarRepository extends JpaRepository<ChekideGardeshKar, Long> {

}
