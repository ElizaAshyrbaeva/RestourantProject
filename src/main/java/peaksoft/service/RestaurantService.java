package peaksoft.service;

import org.springframework.stereotype.Service;
import peaksoft.dto.request.RestaurantRequest;
import peaksoft.dto.response.RestaurantResponse;
import peaksoft.dto.response.SimpleResponse;

import java.util.List;


public interface RestaurantService {
    SimpleResponse save(RestaurantRequest request);
    List<RestaurantResponse>getAll();
    SimpleResponse updateRest(Long id,RestaurantRequest request);
    RestaurantResponse getById(Long id);
    SimpleResponse deleteById(Long id);
}
