package ir.saha.repository;

import ir.saha.domain.Mantaghe;

import ir.saha.domain.Yegan;
import org.springframework.data.domain.PageRequest;
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

}
