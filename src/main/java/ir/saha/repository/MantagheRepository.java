package ir.saha.repository;

import ir.saha.domain.Mantaghe;

import ir.saha.domain.Shahr;
import ir.saha.domain.Yegan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the Mantaghe entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MantagheRepository extends JpaRepository<Mantaghe, Long> {

    @Query("select  mantaghe from Mantaghe mantaghe where mantaghe.name like %:d%  ")
    Page<Mantaghe> findNameLike(@Param("d") String name, Pageable pageable);
}
