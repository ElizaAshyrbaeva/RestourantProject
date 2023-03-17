package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.dto.response.EmployeeResponse;
import peaksoft.entity.Employee;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("select new peaksoft.dto.response.EmployeeResponse(e.firstName,e.lastName,e.dateOfBirth,e.email,e.password,e.phoneNumber,e.experience)from  Employee e")
    List<EmployeeResponse>getAllEmpl();

    @Query("select new peaksoft.dto.response.EmployeeResponse(concat(e.firstName,' ',e.lastName) ,e.dateOfBirth,e.email,e.password,e.phoneNumber,e.experience)from Employee e where e.id=:id")
    EmployeeResponse findByIdEmpl(Long id);
}