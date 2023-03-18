package peaksoft.service;

import peaksoft.dto.request.StopListRequest;
import peaksoft.dto.response.SimpleResponse;


public interface StopListService {
    SimpleResponse save(Long id,StopListRequest request);

}
