package peaksoft.service.serviceImpl;

import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.MenuItemRequest;
import peaksoft.dto.response.*;
import peaksoft.entity.MenuItem;
import peaksoft.entity.Restaurant;
import peaksoft.entity.StopList;
import peaksoft.entity.SubCategory;
import peaksoft.repository.MenuItemRepository;
import peaksoft.repository.RestaurantRepository;
import peaksoft.repository.StopListRepository;
import peaksoft.repository.SubcategoryRepository;
import peaksoft.service.MenuItemService;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Transactional
public class MenuItemServiceImpl implements MenuItemService {
    private final RestaurantRepository repository;
    private final MenuItemRepository menuItemRepository;
    private final SubcategoryRepository subcategoryRepository;
    private final StopListRepository stopListRepository;

    public MenuItemServiceImpl(RestaurantRepository repository, MenuItemRepository menuItemRepository, SubcategoryRepository subcategoryRepository, StopListRepository stopListRepository) {
        this.repository = repository;
        this.menuItemRepository = menuItemRepository;
        this.subcategoryRepository = subcategoryRepository;
        this.stopListRepository = stopListRepository;
    }

    @Override
    @Transactional
    public SimpleResponse save(MenuItemRequest request) {
        SubCategory subCategory = subcategoryRepository.findById(request.subCategoryId()).orElseThrow(() -> new NoSuchElementException("Not fount"));
        Restaurant restaurant = repository.findById(request.restId()).orElseThrow(() -> new NoSuchElementException("NOd found"));
        StopList stopList = stopListRepository.findById(request.listId()).orElseThrow(() -> new NoSuchElementException("Not found!"));
        MenuItem menuItem = new MenuItem();
        menuItem.setName(request.name());
        menuItem.setImage(request.image());
        menuItem.setPrice(request.price());
        menuItem.setDescription(request.description());
        menuItem.setIsVegetarian(request.isVegetarian());
        menuItem.setInStock(true);
        menuItem.setRestaurant(restaurant);
        menuItem.setSubcategory(subCategory);
        menuItem.setList(stopList);
        menuItemRepository.save(menuItem);
        return SimpleResponse.builder().status(HttpStatus.OK).
                massage(" Successfully saved..").build();
    }

    @Override
    public List<MenuItemResponse> getAll() {
        return menuItemRepository.getAllMenu();
    }

    @Override
    public MenuItemResponse findById(Long id) {
        return menuItemRepository.findByIdMenu(id).orElseThrow(() -> new NoSuchElementException("NOT FOUND!!!"));
    }

    @Override
    public SimpleResponse delete(Long id) {
        menuItemRepository.deleteById(id);
        return SimpleResponse.builder().status(HttpStatus.OK).massage("Successfully deleted.!!").build();
    }

    @Override
    public SimpleResponse update(Long id, MenuItemRequest request) {
        MenuItem menuItem1 = menuItemRepository.findById(id).orElseThrow(() -> new NoSuchElementException("vdbv"));
        menuItem1.setName(request.name());
        menuItem1.setImage(request.image());
        menuItem1.setDescription(request.description());
        menuItem1.setPrice(request.price());
        menuItem1.setIsVegetarian(request.isVegetarian());
        menuItemRepository.save(menuItem1);
        return SimpleResponse.builder().status(HttpStatus.OK).massage("Successfully updated.!").build();

    }

    @Override
    public List<MenuItemResponse> sort(String sort) {
        if (sort.equalsIgnoreCase("asc")) {
            return menuItemRepository.sortByAsc();
        } else if (sort.equalsIgnoreCase("desc")) {
            return menuItemRepository.sortByDesc();
        } else {
            return getAll();
        }
    }

    @Override
    public Map<Boolean, List<MenuItemResponse>> filterByVegetarian() {
        return menuItemRepository.getAllMenu().stream().collect(Collectors.groupingBy(MenuItemResponse::isVegetarian));

    }

    @Override
    public List<MenuItemResponse> globalSearch(String word) {
        LocalDate date=LocalDate.now();
        if (word==null){
            for (StopList stopList : stopListRepository.findAll()) {
                 if(stopList.getDate().equals(date)){
                    stopList.getMenuitem().setInStock(false);
                    stopListRepository.save(stopList);
                } else {
                    stopList.getMenuitem().setInStock(true);
                    stopListRepository.save(stopList);
                }
            }
            return menuItemRepository.getAllMenu();
        } else {
            return menuItemRepository.globalSearch(word);

            }
    }
}

