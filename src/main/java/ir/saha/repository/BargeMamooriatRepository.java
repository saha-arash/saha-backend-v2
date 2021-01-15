package ir.saha.repository;

import ir.saha.domain.BargeMamooriat;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the BargeMamooriat entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BargeMamooriatRepository extends JpaRepository<BargeMamooriat, Long> {

    List<BargeMamooriat> findAllBySaleMamooriat(Integer sal);

}
