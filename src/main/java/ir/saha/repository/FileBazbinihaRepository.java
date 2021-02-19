package ir.saha.repository;

import ir.saha.domain.FileBazbiniha;
import ir.saha.domain.GardeshKar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the GardeshKar entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FileBazbinihaRepository extends JpaRepository<FileBazbiniha, Long> {

}
