package ir.saha.repository;

import ir.saha.domain.BilanSeSalGhabl;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the BilanSeSalGhabl entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BilanSeSalGhablRepository extends JpaRepository<BilanSeSalGhabl, Long> {

}
