package peaksoft.service;

import peaksoft.dto.request.MenuItemRequest;
import peaksoft.dto.response.MenuAllResponse;
import peaksoft.dto.response.MenuItemResponse;
import peaksoft.dto.response.SimpleResponse;

import java.util.List;
import java.util.Map;

public interface MenuItemService {
    SimpleResponse save(Long id, MenuItemRequest request);
    List<MenuAllResponse> getAll();
    MenuItemResponse findById(Long id);
    SimpleResponse delete(Long id);
    SimpleResponse update(Long id,MenuItemRequest request);
    List<MenuItemResponse>sortByPriceAndFilterVeganAndSearch(String word,Boolean vegan,String sort);


}
