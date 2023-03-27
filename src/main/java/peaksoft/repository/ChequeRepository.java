package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.entity.Cheque;
import java.time.LocalDate;

@Repository
public interface ChequeRepository extends JpaRepository<Cheque, Long> {
    @Query("select sum (m.price)from Employee u join u.cheques c join c.menuItems m where u.id=:id and " +
            "c.createAt = :date")
    Double getTopByCreatedAt(LocalDate date,Long id);

    @Query("select avg(m.grandTotal) from Cheque m where m.employee.restaurant.id=:id")
    Double avg(Long id);
}