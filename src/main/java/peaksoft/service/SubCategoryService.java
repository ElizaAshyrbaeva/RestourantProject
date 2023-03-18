package peaksoft.service;

import peaksoft.dto.request.SubCategoryRequest;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.dto.response.SubCategoryResponse;

import java.util.List;

public interface SubCategoryService {
    SimpleResponse save(Long id,SubCategoryRequest request);
    List<SubCategoryResponse> getAll();
    SubCategoryResponse findBiId(Long id);
    SimpleResponse delete(Long id);
    SimpleResponse update(Long id,SubCategoryRequest request);

}
