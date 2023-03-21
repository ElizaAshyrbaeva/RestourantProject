package peaksoft.service;
import peaksoft.dto.request.StopListRequest;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.dto.response.StopListResponse;

import java.util.List;
public interface StopListService {
    SimpleResponse save(Long id,StopListRequest request);
    List <StopListResponse>getAll();
    SimpleResponse delete(Long id);
    SimpleResponse update(Long id,StopListRequest request);
    StopListResponse findById(Long id);
}
