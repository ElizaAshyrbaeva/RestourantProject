package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.dto.response.ChequeResponse;
import peaksoft.entity.Cheque;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ChequeRepository extends JpaRepository<Cheque, Long> {
    @Query("select new peaksoft.dto.response.ChequeResponse( c.id,concat(c.employee.firstName,c.employee.lastName),c.priceAverage,c.employee.restaurant.service,c.grandTotal,c.menuItems ) from Cheque c")
    List<ChequeResponse> getAllChecks();

    @Query("select new peaksoft.dto.response.ChequeResponse(l.id,concat(l.employee.firstName,l.employee.lastName),l.priceAverage,l.employee.restaurant.service,l.grandTotal ,l.menuItems)from Cheque l where l.id=:id")
    Optional<ChequeResponse> getCHeckById(Long id);

    @Query("select sum (m.price)from Employee u join u.cheques c join c.menuItems m where u.id=:id and " +
            "c.createAt = :date")
    Integer getTopByCreatedAt(LocalDate date,Long id);

    @Query("select avg(m.price) from Restaurant r join r.employees u join u.cheques c join c.menuItems m where r.id=1 and c.createAt=:date")
    Integer avg(LocalDate date);
}