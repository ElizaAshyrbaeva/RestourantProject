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
import peaksoft.exceptions.NotFoundException;
import peaksoft.repository.ChequeRepository;
import peaksoft.repository.EmployeeRepository;
import peaksoft.repository.MenuItemRepository;
import peaksoft.service.ChequeService;

import java.time.LocalDate;
import java.util.ArrayList;
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
        double count = 0;
        Employee employee = employeeRepository.findById(request.userId()).orElseThrow(() -> new NoSuchElementException("not found"));
        Cheque cheque = new Cheque();
        cheque.setEmployee(employee);
        for (MenuItem menuItem : menuItemRepository.findAllById(request.menuId())) {
            cheque.addMenu(menuItem);
            count += menuItem.getPrice();
        }
        cheque.setPriceAverage(count);
        cheque.setCreateAt(LocalDate.now());
        double total = (count * cheque.getEmployee().getRestaurant().getService()) / 100;
        cheque.setGrandTotal(total+total);
        chequeRepository.save(cheque);
        return SimpleResponse.builder().status(HttpStatus.OK).massage("Cheque is saved!").build();
    }

    @Override
    public ChequeResponse findById(Long id) {
        Cheque cheque = chequeRepository.findById(id).orElseThrow(() -> new NoSuchElementException(String.format("Cheque with id: %s not found!", id)));
        ChequeResponse chequeResponse = new ChequeResponse();
        chequeResponse.setId(cheque.getId());
        chequeResponse.setFullName(cheque.getEmployee().getFirstName() + cheque.getEmployee().getLastName());
        chequeResponse.setItems(cheque.getMenuItems());
        chequeResponse.setAveragePrice(cheque.getPriceAverage());
        chequeResponse.setService(cheque.getEmployee().getRestaurant().getService());
        chequeResponse.setGrandTotal(cheque.getGrandTotal());
        return chequeResponse;
    }


    @Override
    public SimpleResponse deleteById(Long id) {
        chequeRepository.findById(id).orElseThrow(()->new NotFoundException("Cheque with this id not found:"+id));
        chequeRepository.deleteById(id);
        return SimpleResponse.builder().status(HttpStatus.OK).massage("gbefvbfdvrr").build();
    }

    @Override
    public SimpleResponse update(Long id, ChequeRequest request) {
        Cheque cheque = chequeRepository.findById(id).orElseThrow(() -> new NoSuchElementException(String.format("Cheque with  id: %s not found", id)));
        Employee employee = employeeRepository.findById(request.userId()).orElseThrow(() -> new NoSuchElementException("Employee Not Fount!"));
        List<MenuItem> menuItems = menuItemRepository.findAllById(request.menuId());
        cheque.setMenuItems(menuItems);
        cheque.setEmployee(employee);
        chequeRepository.save(cheque);
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .massage(String.format("Cheque with id : %s successfully update", id)).build();
    }

    @Override
    public List<ChequeResponse> findAll() {
        List<Cheque> cheques = chequeRepository.findAll();
        List<ChequeResponse> chequeResponses = new ArrayList<>();
        ChequeResponse chequeResponse = new ChequeResponse();
        for (Cheque cheque : cheques) {
            chequeResponse.setId(cheque.getId());
            chequeResponse.setFullName(cheque.getEmployee().getFirstName() + cheque.getEmployee().getLastName());
            chequeResponse.setItems(cheque.getMenuItems());
            chequeResponse.setAveragePrice(cheque.getPriceAverage());
            chequeResponse.setService(cheque.getEmployee().getRestaurant().getService());
            chequeResponse.setGrandTotal(cheque.getGrandTotal());
            chequeResponses.add(chequeResponse);
        }
       return chequeResponses;
    }

    @Override
    public Double totalSum(Long id) {
        double count = 0;
        for (Cheque cheque : chequeRepository.findAll()) {
            if (cheque.getEmployee().getId().equals(id) && cheque.getCreateAt().equals(LocalDate.now())){
                count += cheque.getGrandTotal();
            }
        }
        return count;
    }

    @Override
    public Double avg(Long id) {
        return chequeRepository.avg(id);
    }
    }