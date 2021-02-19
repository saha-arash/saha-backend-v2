package ir.saha.repository;

import ir.saha.domain.FileDastoorAmalha;
import ir.saha.domain.FileFormMoredeNiaz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the FileGozaresh entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FileFormMoredeNiazRepository extends JpaRepository<FileFormMoredeNiaz, Long> {

}
