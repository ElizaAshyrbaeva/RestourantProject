package peaksoft.service;

import peaksoft.dto.request.SubCategoryRequest;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.dto.response.SubCategoryByCategory;
import peaksoft.dto.response.SubCategoryResponse;

import java.util.List;
import java.util.Map;

public interface SubCategoryService {
    SimpleResponse save(SubCategoryRequest request);
    List<SubCategoryResponse> getAll(Long id);
    SubCategoryResponse findBiId(Long id);
    SimpleResponse delete(Long id);
    SimpleResponse update(Long id,SubCategoryRequest request);
    Map<String,List<SubCategoryResponse>> groupingByCategory();
}
