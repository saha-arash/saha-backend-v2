package ir.saha.repository;

import ir.saha.domain.BilanSalGhabl;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the BilanSalGhabl entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BilanSalGhablRepository extends JpaRepository<BilanSalGhabl, Long> {

}
