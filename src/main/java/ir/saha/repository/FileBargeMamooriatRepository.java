package ir.saha.repository;

import ir.saha.domain.BargeMamooriat;
import ir.saha.domain.FileBargeMamooriat;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the FileBargeMamooriat entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FileBargeMamooriatRepository extends JpaRepository<FileBargeMamooriat, Long> {

    Page<FileBargeMamooriat> findAllByBargeMamooriat(BargeMamooriat bargeMamooriat,Pageable pageable);
}
