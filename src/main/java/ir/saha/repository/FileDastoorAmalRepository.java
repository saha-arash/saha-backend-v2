package ir.saha.repository;

import ir.saha.domain.FileDastoorAmalha;
import ir.saha.domain.FileGozaresh;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the FileGozaresh entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FileDastoorAmalRepository extends JpaRepository<FileDastoorAmalha, Long> {

}
