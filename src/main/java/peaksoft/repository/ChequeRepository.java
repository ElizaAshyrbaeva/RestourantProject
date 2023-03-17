package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import peaksoft.entity.Cheque;
@Repository
public interface ChequeRepository extends JpaRepository<Cheque, Long> {
}