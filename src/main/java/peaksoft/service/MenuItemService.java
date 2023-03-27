package peaksoft.service;

import peaksoft.dto.request.MenuItemRequest;
import peaksoft.dto.response.MenuAllResponse;
import peaksoft.dto.response.MenuItemResponse;
import peaksoft.dto.response.SimpleResponse;

import java.util.List;
import java.util.Map;

public interface MenuItemService {
    SimpleResponse save( MenuItemRequest request);
    List<MenuItemResponse> getAll();
    MenuItemResponse findById(Long id);
    SimpleResponse delete(Long id);
    SimpleResponse update(Long id,MenuItemRequest request);
    List<MenuItemResponse>sort(String sort);
    Map<Boolean, List<MenuItemResponse>> filterByVegetarian();
    List<MenuItemResponse>globalSearch(String word);



}
