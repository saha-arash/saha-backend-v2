package ir.saha.repository;

import ir.saha.domain.FileHesabResi;

import ir.saha.domain.enumeration.FileType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the FileHesabResi entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FileHesabResiRepository extends JpaRepository<FileHesabResi, Long> {

    Page<FileHesabResi> findAllByFileTypeAndHesabResi_Id(FileType fileType,Long hesabResiId,Pageable pageable);
}
