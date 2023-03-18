package peaksoft.service.serviceImpl;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.MenuItemRequest;
import peaksoft.dto.response.MenuItemResponse;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.entity.MenuItem;
import peaksoft.entity.Restaurant;
import peaksoft.repository.MenuItemRepository;
import peaksoft.repository.RestaurantRepository;
import peaksoft.service.MenuItemService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class MenuItemServiceImpl implements MenuItemService {
    private final RestaurantRepository repository;
    private final MenuItemRepository menuItemRepository;

    public MenuItemServiceImpl(RestaurantRepository repository, MenuItemRepository menuItemRepository) {
        this.repository = repository;


        this.menuItemRepository = menuItemRepository;
    }

    @Override
    public SimpleResponse save(Long id, MenuItemRequest request) {
        Restaurant restaurant = repository.findById(id).orElseThrow(() -> new NoSuchElementException("NOD FOUND!!!"));
        MenuItem menuItem = new MenuItem();
        menuItem.setName(request.name());
        menuItem.setImage(request.image());
        menuItem.setPrice(request.price());
        menuItem.setDescription(request.description());
        menuItem.setVegetarian(false);
        menuItem.setRestaurant(restaurant);
        menuItemRepository.save(menuItem);
        return SimpleResponse.builder().status(HttpStatus.OK).massage("Successfully saved..").build();
    }

    @Override
    public List<MenuItemResponse> getAll() {
        return menuItemRepository.getAllMenu();
    }

    @Override
    public MenuItemResponse findById(Long id) {
        return menuItemRepository.findByIdMenu(id).orElseThrow(()->new NoSuchElementException("NOT FOUND!!!"));
    }

    @Override
    public SimpleResponse delete(Long id) {
        menuItemRepository.deleteById(id);
        return SimpleResponse.builder().status(HttpStatus.OK).massage("Successfully deleted.!!").build();
    }

    @Override
    public SimpleResponse update(Long id, MenuItemRequest request) {
        menuItemRepository.findById(id);
        MenuItem menuItem = new MenuItem();
        menuItem.setName(request.name());
        menuItem.setImage(request.image());
        menuItem.setPrice(request.price());
        menuItem.setDescription(request.description());
        menuItemRepository.save(menuItem);
        return SimpleResponse.builder().status(HttpStatus.OK).massage("Successfully updated.!").build();
    }
}
