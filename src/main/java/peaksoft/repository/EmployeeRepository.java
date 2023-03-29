package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.dto.response.EmployeeAllResponse;
import peaksoft.dto.response.EmployeeResponse;
import peaksoft.entity.Employee;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("select new peaksoft.dto.response.EmployeeAllResponse(e.id,concat(e.firstName,' ',e.lastName) ,e.dateOfBirth,e.email,e.role)from  Employee e where e.accepted = ?1")
    List<EmployeeAllResponse>getAllEmpl(boolean isTrue);
    @Query("select new peaksoft.dto.response.EmployeeAllResponse(e.id,concat(e.firstName,' ',e.lastName) ,e.dateOfBirth,e.email,e.role)from  Employee e ")
    List<EmployeeAllResponse>getAll();

    @Query("select new peaksoft.dto.response.EmployeeAllResponse(e.id,concat(e.firstName,' ',e.lastName) ,e.dateOfBirth,e.email,e.role)from Employee e where e.id=:id")
    Optional<EmployeeAllResponse> findByIdEmpl(Long id);
    boolean existsByEmail(String email);

}