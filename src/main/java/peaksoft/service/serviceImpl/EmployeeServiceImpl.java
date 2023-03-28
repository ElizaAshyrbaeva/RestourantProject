package peaksoft.service.serviceImpl;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.AcceptOrRejectRequest;
import peaksoft.dto.request.EmployeeRequest;
import peaksoft.dto.response.EmployeeResponse;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.entity.Employee;
import peaksoft.entity.Restaurant;
import peaksoft.enums.Role;
import peaksoft.exceptions.NotFoundException;
import peaksoft.repository.EmployeeRepository;
import peaksoft.repository.RestaurantRepository;
import peaksoft.service.EmployeeService;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder encoder;
    private final RestaurantRepository repository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, PasswordEncoder encoder, RestaurantRepository repository) {
        this.employeeRepository = employeeRepository;
        this.encoder = encoder;
        this.repository = repository;
    }
    @Override
    public List<EmployeeResponse> getAll() {
        return employeeRepository.getAllEmpl(true);
    }

    @Override
    public EmployeeResponse findById(Long id) {
         return employeeRepository.findByIdEmpl(id).orElseThrow(()->new NoSuchElementException(String.format("User with ID: %s doesn't exists",id)));
    }

    @Override
    public SimpleResponse deleteById(Long id) {
        employeeRepository.findById(id).orElseThrow(()-> new NotFoundException("user with this id not found: "+ id));
        employeeRepository.deleteById(id);
        return SimpleResponse.builder().status(HttpStatus.OK).massage("Successfully delete.").build();
    }

    @Override
    public SimpleResponse update(Long id, EmployeeRequest request) {
        if (request.role().equals(Role.ADMIN)) {
            return SimpleResponse.builder().status(HttpStatus.FORBIDDEN).massage("Not Your Level Dear").build();
        }
        if (employeeRepository.existsByEmail(request.email())) {
            return SimpleResponse.builder().status(HttpStatus.CONFLICT).massage(String.format("User with email: %s already exists!", request.email())).build();
        }
        if (request.role().equals(Role.CHEF)) {
            Period period = Period.between(request.dateOfBirth(), LocalDate.now());
            if (period.getYears() < 25 || period.getYears() > 45) {
                return SimpleResponse.builder().status(HttpStatus.BAD_REQUEST).massage(String.format("For the vacancy of a cook, the age range is from 25 to 45 years!"+ Role.CHEF)).build();
            }
            if (request.experience() <= 2) {
                return SimpleResponse.builder().status(HttpStatus.BAD_REQUEST).massage(String.format("Cooking experience must be at least 2 years!"+request.experience())).build();
            }
        }

        if (request.role().equals(Role.WAITER)) {
            Period period = Period.between(request.dateOfBirth(), LocalDate.now());
            if (period.getYears() < 18 || period.getYears() > 30) {
                return SimpleResponse.builder().status(HttpStatus.BAD_REQUEST).massage(String.format("For the vacancy of a cook, the age range is from 18 to 30 years!" + Role.WAITER)).build();
            }
            if (request.experience() <= 1) {
                return SimpleResponse.builder().status(HttpStatus.BAD_REQUEST).massage(String.format("Cooking experience must be at least 1 years!" + request.experience())).build();
            }
        }
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Npt Found!"));
        employee.setFirstName(request.firstName());
        employee.setLastName(request.lastName());
        employee.setDateOfBirth(request.dateOfBirth());
        employee.setEmail(request.email());
        employee.setPassword(encoder.encode(request.password()));
        employee.setPhoneNumber(request.phoneNumber());
        employee.setRole(request.role());
        employee.setExperience(request.experience());
        employeeRepository.save(employee);
        return SimpleResponse.builder().status(HttpStatus.OK)
                .massage("Successfully update.").build();
    }
    @Override
    public SimpleResponse acceptOrReject(EmployeeRequest request) {
        if (request.role().equals(Role.ADMIN)) {
            return SimpleResponse.builder().status(HttpStatus.FORBIDDEN).massage("Not Your Level Dear").build();
        }
        if (employeeRepository.existsByEmail(request.email())) {
            return SimpleResponse.builder().status(HttpStatus.CONFLICT).massage(String.format("User with email: %s already exists!", request.email())).build();
        }
        if (request.role().equals(Role.CHEF)) {
            Period period = Period.between(request.dateOfBirth(), LocalDate.now());
            if (period.getYears() < 25 || period.getYears() > 45) {
                return SimpleResponse.builder().status(HttpStatus.BAD_REQUEST).massage(String.format("For the vacancy of a cook, the age range is from 25 to 45 years!"+ Role.CHEF)).build();
            }
            if (request.experience() <= 2) {
                return SimpleResponse.builder().status(HttpStatus.BAD_REQUEST).massage(String.format("Cooking experience must be at least 2 years!"+request.experience())).build();
            }
        }

        if (request.role().equals(Role.WAITER)) {
            Period period = Period.between(request.dateOfBirth(), LocalDate.now());
            if (period.getYears() < 18 || period.getYears() > 30) {
                return SimpleResponse.builder().status(HttpStatus.BAD_REQUEST).massage(String.format("For the vacancy of a cook, the age range is from 18 to 30 years!"+Role.WAITER)).build();
            }
            if (request.experience() <= 1) {
                return SimpleResponse.builder().status(HttpStatus.BAD_REQUEST).massage(String.format("Cooking experience must be at least 1 years!"+request.experience())).build();
            }
        }
        Employee employee = new Employee();
        employee.setFirstName(request.firstName());
        employee.setLastName(request.lastName());
        employee.setDateOfBirth(request.dateOfBirth());
        employee.setEmail(request.email());
        employee.setPassword(encoder.encode(request.password()));
        employee.setRole(request.role());
        employee.setPhoneNumber(request.phoneNumber());
        employee.setExperience(request.experience());
        employee.setAccepted(false);
        employeeRepository.save(employee);
        return SimpleResponse.builder().status(HttpStatus.OK)
                .massage("Your Application Has Been Successfully Send!").build();
    }

    @Override
    public SimpleResponse acceptOrReject(AcceptOrRejectRequest acceptOrRejectRequest) {
        Employee employee = employeeRepository.findById(acceptOrRejectRequest.employeeId())
                .orElseThrow(() -> new NoSuchElementException("Not found!"));
        if (acceptOrRejectRequest.isTrue()){
            Restaurant restaurant = repository.findById(acceptOrRejectRequest.restaurantId()).orElseThrow(() -> new NoSuchElementException("not found!"));
           employee.setAccepted(true);
            employee.setRestaurant(restaurant);

            return SimpleResponse.builder()
                    .status(HttpStatus.OK)
                    .massage("Accepted")
                    .build();

        } else {
            employeeRepository.delete(employee);
            return SimpleResponse.builder()
                    .status(HttpStatus.OK)
                    .massage("Rejected")
                    .build();
        }
    }

    @Override
    public List<EmployeeResponse> getApplications() {
        return employeeRepository.getAllEmpl(false);
    }

    @Override
    public List<EmployeeResponse> findAll() {
        return employeeRepository.getAll();
    }

    @Override
    public SimpleResponse save(EmployeeRequest request, Long id) {
        Restaurant restaurant = repository.findById(id).orElseThrow(() -> new NoSuchElementException(String.format("Restaurant with id : %s not found! ", id)));
        if (restaurant.getEmployees().size() >= 15) {
            return SimpleResponse.builder().status(HttpStatus.BAD_REQUEST).massage("Vacancy no").build();
        }
        if (request.role().equals(Role.ADMIN)) {
            return SimpleResponse.builder().status(HttpStatus.FORBIDDEN).massage("Not Your Level Dear").build();
        }
        if (employeeRepository.existsByEmail(request.email())) {
            return SimpleResponse.builder().status(HttpStatus.CONFLICT).massage(String.format("User with email: %s already exists!"+request.email())).build();
        }
        if (request.role().equals(Role.CHEF)) {
            Period period = Period.between(request.dateOfBirth(), LocalDate.now());
            if (period.getYears() < 25 || period.getYears() > 45) {
                return SimpleResponse.builder().status(HttpStatus.BAD_REQUEST).massage(String.format("For the vacancy of a cook, the age range is from 25 to 45 years!"+ Role.CHEF)).build();
            }
            if (request.experience() <= 2) {
                return SimpleResponse.builder().status(HttpStatus.BAD_REQUEST).massage(String.format("Cooking experience must be at least 2 years!"+request.experience())).build();
            }
        }

        if (request.role().equals(Role.WAITER)) {
            Period period = Period.between(request.dateOfBirth(), LocalDate.now());
            if (period.getYears() < 18 || period.getYears() > 30) {
                return SimpleResponse.builder().status(HttpStatus.BAD_REQUEST).massage(String.format("For the vacancy of a cook, the age range is from 18 to 30 years!"+ Role.WAITER)).build();
            }
            if (request.experience() <= 1) {
                return SimpleResponse.builder().status(HttpStatus.BAD_REQUEST).massage(String.format("Cooking experience must be at least 1 years!"+request.experience())).build();
            }
        }
        Employee employee = new Employee();
        employee.setFirstName(request.firstName());
        employee.setLastName(request.lastName());
        employee.setDateOfBirth(request.dateOfBirth());
        employee.setEmail(request.email());
        employee.setPassword(encoder.encode(request.password()));
        employee.setRole(request.role());
        employee.setPhoneNumber(request.phoneNumber());
        employee.setExperience(request.experience());
        employee.setRestaurant(restaurant);
        employee.setAccepted(true);
         employeeRepository.save(employee);
         return SimpleResponse.builder().status(HttpStatus.OK).
                massage("Successfully saved"+" "+request.lastName()).build();

    }



}
