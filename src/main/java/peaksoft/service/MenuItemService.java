package peaksoft.service;

import peaksoft.dto.request.MenuItemRequest;
import peaksoft.dto.request.StopListRequest;
import peaksoft.dto.response.MenuItemResponse;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.dto.response.StopListResponse;

import java.util.List;

public interface MenuItemService {
    SimpleResponse save(Long id, MenuItemRequest request);
    List<MenuItemResponse> getAll();
    MenuItemResponse findById(Long id);
    SimpleResponse delete(Long id);
    SimpleResponse update(Long id,MenuItemRequest request);

}
