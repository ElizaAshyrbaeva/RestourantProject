package peaksoft.service.serviceImpl;

import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.ChequeRequest;
import peaksoft.dto.response.ChequeResponse;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.entity.*;
import peaksoft.exceptions.NotFoundException;
import peaksoft.repository.ChequeRepository;
import peaksoft.repository.EmployeeRepository;
import peaksoft.repository.MenuItemRepository;
import peaksoft.service.ChequeService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ChequeServiceImpl implements ChequeService {
    private final ChequeRepository chequeRepository;
    private final EmployeeRepository employeeRepository;
    private final MenuItemRepository menuItemRepo;
    private static final Logger LOGGER = LoggerFactory.getLogger(ChequeServiceImpl.class);

    public ChequeServiceImpl(ChequeRepository chequeRepository, EmployeeRepository employeeRepository, MenuItemRepository menuItemRepo) {
        this.chequeRepository = chequeRepository;
        this.employeeRepository = employeeRepository;
        this.menuItemRepo = menuItemRepo;
    }

    @Override
    public SimpleResponse save(ChequeRequest request) throws NotFoundException {
        Employee employee = employeeRepository.findById(request.userId())
                .orElseThrow(() -> new NotFoundException("Cheque with this id : " + request.userId() + " was not found!"));

        List<MenuItem> menuItems = new ArrayList<>();

        for (Long id : request.menuId()) {
            MenuItem menuItem = menuItemRepo.findById(id)
                    .orElseThrow(() -> new NotFoundException("Menu item with this id " + id + " was not found!"));
            menuItems.add(menuItem);
        }


        double count = menuItems.stream().mapToDouble(MenuItem::getPrice).sum();
        double servicePercentage = employee.getRestaurant().getService();
        double total = count + (count * servicePercentage / 100);
        LocalDate createdAt = LocalDate.now();

        Cheque cheque = new Cheque();
        cheque.setPriceAverage(count);
        cheque.setGrandTotal(count + total);
        cheque.setCreateAt(createdAt);
        cheque.setEmployee(employee);

        chequeRepository.save(cheque);
        for (MenuItem menuItem : menuItems) {
            cheque.addMenu(menuItem);
        }
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .massage("Cheque with id: " + cheque.getId() + " is saved!")
                .build();
    }


    @Override
    public List<ChequeResponse> getAll() {
        List<Cheque> cheques = chequeRepository.findAll();
        List<ChequeResponse> chequeResponses = new ArrayList<>();
        for (Cheque cheque : cheques) {
            ChequeResponse chequeResponse = new ChequeResponse(
                    cheque.getId(),
                    cheque.getEmployee().getFirstName() + " " + cheque.getEmployee().getLastName(),
                    cheque.getMenuItems().stream().map(MenuItem::getName).collect(Collectors.toList()),
                    cheque.getCreateAt(),
                    cheque.getPriceAverage(),
                    cheque.getEmployee().getRestaurant().getService(),
                    cheque.getGrandTotal()
            );
            chequeResponses.add(chequeResponse);
        }
        return chequeResponses;
    }

    @Override
    public ChequeResponse getById(Long id) throws NotFoundException {
        Cheque che = chequeRepository.findById(id).orElseThrow(() -> new NotFoundException("Cheque with this id : " + id + " was not found!"));
        List<String> stringMenuItems = new ArrayList<>();
        for (MenuItem menuItem : che.getMenuItems()) {
            stringMenuItems.add(menuItem.getName());
        }
        return new ChequeResponse(
                che.getId(),
                che.getEmployee().getFirstName(),
                stringMenuItems,
                che.getCreateAt(),
                che.getPriceAverage(),
                che.getEmployee().getRestaurant().getService(),
                che.getGrandTotal());
    }


    @Transactional
    @Override
    public SimpleResponse update(Long id, ChequeRequest request) throws NotFoundException {
        Cheque cheque = chequeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Cheque with this id : " + id + " was not found!"));
        Employee employee = employeeRepository.findById(request.userId())
                .orElseThrow(() -> new NotFoundException("User with this id : " + id + " was not found!"));
        List<MenuItem> menuItems = new ArrayList<>();
        LOGGER.debug("Updating cheque with ID {}", id);
        for (Long menuItemId : request.menuId()) {
            MenuItem menuItem = menuItemRepo.findById(menuItemId)
                    .orElseThrow(() -> new NotFoundException("MenuItem with this id : " + id + " was not found!"));
            menuItems.add(menuItem);
        }

        cheque.setMenuItems(menuItems.size() != 0 ? menuItems : cheque.getMenuItems());
        cheque.setEmployee(employee != null ? employee : cheque.getEmployee());
        chequeRepository.save(cheque);
        return SimpleResponse.builder().status(HttpStatus.OK).massage("Cheque with id: " + cheque.getId() + " is successfully updated!").build();
    }

    @Override
    public SimpleResponse deleteById(Long id) throws NotFoundException {
        Cheque cheque = chequeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Cheque was not found!"));
        chequeRepository.delete(cheque);
        return SimpleResponse.builder().status(HttpStatus.NO_CONTENT).massage("Cheque with id: " + id + " is successfully deleted!!").build();
    }

    @Override
    public Double getAllChequesByUser(Long userId) {
        LocalDate today = LocalDate.now();
        DoubleSummaryStatistics stats = chequeRepository.findAll()
                .stream()
                .filter(cheque -> cheque.getEmployee().getId().equals(userId) && cheque.getCreateAt().equals(today))
                .mapToDouble(Cheque::getGrandTotal)
                .summaryStatistics();
        return stats.getSum();
    }

    @Override
    public Double getAverageSum(Long restId) {
        return chequeRepository.avg(restId);
    }
}