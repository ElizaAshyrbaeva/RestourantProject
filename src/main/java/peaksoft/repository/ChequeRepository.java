package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import peaksoft.entity.Cheque;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface ChequeRepository extends JpaRepository<Cheque, Long> {
    @Query("select avg(m.grandTotal) from Cheque m where m.employee.restaurant.id=:id")
    Double avg(Long id);
    @Query("SELECT c FROM Cheque c join Employee e join Restaurant r where r.id = :restaurantId")
    List<Cheque> getAll(Long restaurantId);


    @Query("SELECT c FROM Cheque c WHERE c.employee.id = :userId AND c.createAt = :date")
    List<Cheque> findByUser_IdAndCreatedAt(@Param("userId") Long userId, @Param("date") LocalDate date);
}