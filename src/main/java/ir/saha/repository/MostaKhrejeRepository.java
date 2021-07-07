package ir.saha.repository;

import ir.saha.domain.MostaKhreje;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the MostaKhreje entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MostaKhrejeRepository extends JpaRepository<MostaKhreje, Long> {

}
