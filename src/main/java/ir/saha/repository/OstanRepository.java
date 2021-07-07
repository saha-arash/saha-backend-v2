package ir.saha.repository;

import ir.saha.domain.Ostan;

import ir.saha.domain.Shahr;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the Ostan entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OstanRepository extends JpaRepository<Ostan, Long> {

    @Query("select  ostan from Ostan ostan where ostan.name like %:d%  ")
    Page<Ostan> findNameLike(@Param("d") String name, Pageable pageable);

}
