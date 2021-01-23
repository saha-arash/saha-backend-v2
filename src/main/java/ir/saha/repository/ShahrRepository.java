package ir.saha.repository;

import ir.saha.domain.Mantaghe;
import ir.saha.domain.Shahr;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the Shahr entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ShahrRepository extends JpaRepository<Shahr, Long> {


    @Query("select  shahr from Shahr shahr where shahr.name like %:d%  ")
    List<Shahr> findNameLike(@Param("d") String name);
}
