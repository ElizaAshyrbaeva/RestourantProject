package peaksoft.service.serviceImpl;

import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.MenuItemRequest;
import peaksoft.dto.response.*;
import peaksoft.entity.MenuItem;
import peaksoft.entity.Restaurant;
import peaksoft.entity.SubCategory;
import peaksoft.repository.MenuItemRepository;
import peaksoft.repository.RestaurantRepository;
import peaksoft.repository.SubcategoryRepository;
import peaksoft.service.MenuItemService;

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

    public MenuItemServiceImpl(RestaurantRepository repository, MenuItemRepository menuItemRepository, SubcategoryRepository subcategoryRepository) {
        this.repository = repository;
        this.menuItemRepository = menuItemRepository;
        this.subcategoryRepository = subcategoryRepository;
    }

    @Override
    @Transactional
    public SimpleResponse save(Long id, MenuItemRequest request) {
        Restaurant restaurant = repository.findById(id).orElseThrow(() -> new NoSuchElementException(String.format("NOD FOUND!!!", request.restId())));
        SubCategory subCategory = subcategoryRepository.findById(request.subCategoryId()).orElseThrow(() -> new NoSuchElementException(String.format("Not found!", request.subCategoryId())));
        MenuItem menuItem = new MenuItem();
        menuItem.setName(request.name());
        menuItem.setImage(request.image());
        menuItem.setPrice(request.price());
        menuItem.setDescription(request.description());
        menuItem.setIsVegetarian(request.isVegetarian());
        menuItem.setRestaurant(restaurant);
        menuItem.setSubcategory(subCategory);
        menuItemRepository.save(menuItem);
        return SimpleResponse.builder().status(HttpStatus.OK).massage("Successfully saved..").build();
    }

    @Override
    public List<MenuAllResponse> getAll() {
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
    public List<MenuItemResponse> sortByPriceAndFilterVeganAndSearch(String word, Boolean vegan, String sort) {
        if (word.equalsIgnoreCase("asc")) {
            return menuItemRepository.sortByAsc();
        } else if (sort.equalsIgnoreCase("desc")) {
            return menuItemRepository.sortByDesc();
        } else if (word == null) {

        }
        return null;
    }
}

