package ir.saha.repository;

import ir.saha.domain.Madarek;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Madarek entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MadarekRepository extends JpaRepository<Madarek, Long> {

}
