package peaksoft.service.serviceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.EmployeeRequest;
import peaksoft.dto.response.EmployeeResponse;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.entity.Employee;
import peaksoft.enums.Role;
import peaksoft.repository.EmployeeRepository;
import peaksoft.service.EmployeeService;
import java.util.List;
@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository repository;
    private final PasswordEncoder encoder;

    public EmployeeServiceImpl(EmployeeRepository repository,PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    @Override
    public SimpleResponse saveEmployee(EmployeeRequest request) {
        Employee employee = new Employee();
        employee.setFirstName(request.firstName());
        employee.setLastName(request.lastName());
        employee.setDateOfBirth(request.dateOfBirth());
        employee.setEmail(request.email());
        employee.setPassword(encoder.encode(request.password()));
        employee.setPhoneNumber(request.phoneNumber());
        employee.setRole(Role.WAITER);
        employee.setExperience(request.experience());
        repository.save(employee);
        return SimpleResponse.builder().status(HttpStatus.OK).massage("Successfully saved"+" "+request.lastName()).build();
    }

    @Override
    public List<EmployeeResponse> getAll() {
        return repository.getAllEmpl();
    }

    @Override
    public EmployeeResponse findById(Long id) {
         return repository.findByIdEmpl(id);
    }

    @Override
    public SimpleResponse deleteById(Long id) {
        repository.deleteById(id);
        return SimpleResponse.builder().status(HttpStatus.OK).massage("Successfully delete.").build();
    }

    @Override
    public SimpleResponse update(Long id, EmployeeRequest request) {
        Employee employee = new Employee();
        employee.setFirstName(request.firstName());
        employee.setLastName(request.lastName());
        employee.setDateOfBirth(request.dateOfBirth());
        employee.setEmail(request.email());
        employee.setPassword(encoder.encode(request.password()));
        employee.setPhoneNumber(request.phoneNumber());
        employee.setExperience(request.experience());
        return SimpleResponse.builder().status(HttpStatus.OK).massage("Successfully update.").build();
    }
}
