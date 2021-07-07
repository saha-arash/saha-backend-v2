package ir.saha.repository;

import ir.saha.domain.BankEtelaati;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the BankEtelaati entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BankEtelaatiRepository extends JpaRepository<BankEtelaati, Long> {

}
