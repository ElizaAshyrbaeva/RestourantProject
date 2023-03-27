package peaksoft.service;
import peaksoft.dto.request.StopListRequest;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.dto.response.StopListResponse;
import peaksoft.entity.StopList;

import java.util.List;
public interface StopListService {
    SimpleResponse save(Long id,StopListRequest request);
    List <StopList>getAll();
    SimpleResponse delete(Long id);
    SimpleResponse update(Long id,StopListRequest request);
    StopList findById(Long id);
}
