package peaksoft.service;

import peaksoft.dto.request.CategoryRequest;
import peaksoft.dto.request.EmployeeRequest;
import peaksoft.dto.response.CategoryResponse;
import peaksoft.dto.response.EmployeeResponse;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.entity.Category;

import java.util.List;

public interface CategoryService {
    SimpleResponse saveCategory(CategoryRequest request);
    List<CategoryResponse>getAll();
    CategoryResponse findById(Long id);
    SimpleResponse deleteById(Long id);
    SimpleResponse update(Long id,CategoryRequest request);

}
