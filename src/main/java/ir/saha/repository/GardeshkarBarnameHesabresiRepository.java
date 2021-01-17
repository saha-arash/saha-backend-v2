package ir.saha.repository;

import ir.saha.domain.GardeshkarBarnameHesabresi;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the GardeshkarBarnameHesabresi entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GardeshkarBarnameHesabresiRepository extends JpaRepository<GardeshkarBarnameHesabresi, Long> {

}
