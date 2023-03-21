package peaksoft.service.serviceImpl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.RestaurantRequest;
import peaksoft.dto.response.RestaurantResponse;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.entity.Restaurant;
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
        Restaurant restaurant = new Restaurant();
        if (request.name() != null && request.name().length() > 4) {
            restaurant.setName(request.name());
            restaurant.setLocation(request.location());
            restaurant.setRestType(request.restType());
            restaurant.setService(request.service());
            repository.save(restaurant);
            return SimpleResponse.builder().status(HttpStatus.OK).massage(request.name() + " " + "Successfully saved!!").build();
        }
        return null;
    }

    @Override
    public List<RestaurantResponse> getAll() {
        return repository.getAllSB();
    }

    @Override
    public SimpleResponse updateRest(Long id, RestaurantRequest request) {
        Restaurant restaurant = repository.findById(id).orElseThrow(() -> new NoSuchElementException("Not found!!!"));
            restaurant.setName(request.name());
            restaurant.setLocation(request.location());
            restaurant.setRestType(request.restType());
            restaurant.setService(request.service());
            repository.save(restaurant);
            return SimpleResponse.builder().status(HttpStatus.OK).massage("Successful update").build();
    }

    @Override
    public RestaurantResponse getById(Long id) {
        Restaurant restaurant = repository.findById(id).orElseThrow(()->new NullPointerException("Not found!!!"));
        restaurant.setNumberOfEmployees(restaurant.getEmployees().size());
        repository.save(restaurant);
      return repository.getByRestId(id).orElseThrow(()->new NoSuchElementException("Not found!!!"));
    }
    @Override
    public SimpleResponse deleteById(Long id) {
        repository.deleteById(id);
        return SimpleResponse.builder().status(HttpStatus.OK).massage("Delete!!!").build();
    }

}
