package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.dto.response.EmployeeAllResponse;
import peaksoft.dto.response.EmployeeResponse;
import peaksoft.entity.Employee;
import peaksoft.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("select new peaksoft.dto.response.EmployeeResponse(e.id,concat(e.firstName,' ',e.lastName) ,e.dateOfBirth,e.email,e.password,e.phoneNumber,e.role,e.experience)from  Employee e ")
    List<EmployeeResponse>getAllEmpl();

    @Query("select new peaksoft.dto.response.EmployeeResponse(e.id,concat(e.firstName,' ',e.lastName) ,e.dateOfBirth,e.email,e.password,e.phoneNumber,e.role,e.experience)from Employee e where e.id=:id")
    Optional<EmployeeResponse> findByIdEmpl(Long id);
    boolean existsByEmail(String email);
    List<Employee> findAllByAcceptedFalse();
    @Query("select new peaksoft.dto.response.EmployeeAllResponse(u.id,concat(u.lastName,' ',u.firstName),u.email,u.role)" +
            " from Employee u where u.accepted=false")
    List<EmployeeAllResponse> findAllFalse();

}