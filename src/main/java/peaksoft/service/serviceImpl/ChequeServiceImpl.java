package peaksoft.service.serviceImpl;

import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.ChequeRequest;
import peaksoft.dto.response.ChequeResponse;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.entity.Cheque;
import peaksoft.entity.Employee;
import peaksoft.entity.MenuItem;
import peaksoft.repository.ChequeRepository;
import peaksoft.repository.EmployeeRepository;
import peaksoft.repository.MenuItemRepository;
import peaksoft.service.ChequeService;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class ChequeServiceImpl implements ChequeService {
    private final ChequeRepository chequeRepository;
    private final EmployeeRepository employeeRepository;
    private final MenuItemRepository menuItemRepository;

    public ChequeServiceImpl(ChequeRepository chequeRepository, EmployeeRepository employeeRepository, MenuItemRepository menuItemRepository) {
        this.chequeRepository = chequeRepository;
        this.employeeRepository = employeeRepository;
        this.menuItemRepository = menuItemRepository;
    }

    @Override
    public SimpleResponse save(ChequeRequest request) {
        MenuItem menuItem = menuItemRepository.findById(request.menuId()).orElseThrow(() -> new NoSuchElementException("Not found"));
        Employee employee = employeeRepository.findById(request.userId()).orElseThrow(() -> new NoSuchElementException("NOT found"));
        Cheque cheque = new Cheque();
        cheque.setEmployee(employee);
        cheque.setCreateAt(LocalDate.now());
        menuItem.addCheck(cheque);
        chequeRepository.save(cheque);
        return SimpleResponse.builder().status(HttpStatus.OK).massage("Cheque is saved!").build();
    }

    @Override
    public ChequeResponse findById(Long id) {
        Cheque cheque = chequeRepository.findById(id).orElseThrow(() -> new NoSuchElementException(String.format("Cheque with id: %s not found!",id)));
        Employee employee = cheque.getEmployee();
        int sum = cheque.getMenuItems().stream().mapToInt(MenuItem::getPrice).sum();
        cheque.setPriceAverage(sum);
        chequeRepository.save(cheque);
        return ChequeResponse.builder().waiterFullName(employee.getFirstName() + " " + employee.getLastName())
                .menuItems(menuItemRepository.getAllMenu())
                .averagePrice(employee.getRestaurant().getService())
                .total(cheque.getPriceAverage() + cheque.getPriceAverage() * employee.getRestaurant().getService() / 100)
                .build();
    }

    @Override
    public SimpleResponse deleteById(Long id) {
        chequeRepository.deleteById(id);
        return SimpleResponse.builder().status(HttpStatus.OK).massage("gbefvbfdvrr").build();
    }

    @Override
    public SimpleResponse update(Long id, ChequeRequest request) {
        Cheque cheque = chequeRepository.findById(id).orElseThrow(() -> new NoSuchElementException(String.format("Cheque with  id: %s not found", id)));
        Employee employee = employeeRepository.findById(request.userId()).orElseThrow(() -> new NoSuchElementException("Employee Not Fount!"));
        cheque.setEmployee(employee);
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .massage(String.format("Cheque with id : %s successfully update", id)).build();
    }

    @Override
    public List<ChequeResponse> findAll() {
        return chequeRepository.getAllChecks();
    }
    @Override
    public SimpleResponse totalSum(Long id, LocalDate date) {
        Integer top = chequeRepository.getTopByCreatedAt(date, id);
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(
                        String.format("User with id: %s not found", id)));

        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .massage(String.format("Total number of %s %s checks as of the date of %s total: %s",
                        employee.getLastName(), employee.getFirstName(), date, top))
                .build();
    }
    @Override
    public SimpleResponse avg(LocalDate date) {
        Integer avg = chequeRepository.avg(date);
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .massage(String.format("Average check as of date %s total : %s", date, avg))
                .build();
    }
}
