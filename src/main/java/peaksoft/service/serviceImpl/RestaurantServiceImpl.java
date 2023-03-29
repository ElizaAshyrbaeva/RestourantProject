package peaksoft.service.serviceImpl;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.RestaurantRequest;
import peaksoft.dto.response.RestaurantResponse;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.entity.Restaurant;
import peaksoft.exceptions.NotFoundException;
import peaksoft.repository.RestaurantRepository;
import peaksoft.service.RestaurantService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepository repository;
    @Autowired
    public RestaurantServiceImpl(RestaurantRepository repository) {
        this.repository = repository;
    }

    @Override
    public SimpleResponse save(RestaurantRequest request) {
        if (repository.existsByName(request.name())){
            return SimpleResponse.builder().status(HttpStatus.CONFLICT).massage("Given name is already exists!").build();
        }
        Restaurant restaurant = new Restaurant();
            restaurant.setName(request.name());
            restaurant.setLocation(request.location());
            restaurant.setRestType(request.restType());
            restaurant.setService(request.service());
            repository.save(restaurant);
            return SimpleResponse.builder().status(HttpStatus.OK).massage(request.name() + " " + "Successfully saved!!").build();
    }

    @Override
    public List<RestaurantResponse> getAll() {
        return repository.getAllSB();
    }

    @Override
    public SimpleResponse updateRest(Long id,RestaurantRequest request) {
        Restaurant restaurant = repository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Restaurant with id: %s successfully updated", id)));
        restaurant.setName(request.name());
        restaurant.setLocation(request.location());
        restaurant.setRestType(request.restType());
        restaurant.setService(request.service());
        repository.save(restaurant);
        return SimpleResponse.builder().status(HttpStatus.OK)
                .massage("Successful update").build();
    }

    @Override
    public RestaurantResponse getById(Long id) {
        Restaurant restaurant = repository.findById(id).orElseThrow(() -> new NullPointerException("Restaurant with this id not found:"+id));
        restaurant.setNumberOfEmployees(restaurant.getEmployees().size());
        repository.save(restaurant);
        return repository.getByRestId(id).orElseThrow(() -> new NoSuchElementException("Restaurant with this id not found:"+id));
    }
    @Override
    public SimpleResponse deleteById(Long id) {
        repository.findById(id).orElseThrow(()->new NotFoundException("Restaurant with this id not found:"+id));
        repository.deleteById(id);
        return SimpleResponse.builder().status(HttpStatus.OK).massage("Delete!!!").build();
    }

}
