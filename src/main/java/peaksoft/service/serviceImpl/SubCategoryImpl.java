package peaksoft.service.serviceImpl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.SubCategoryRequest;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.dto.response.SubCategoryByCategory;
import peaksoft.dto.response.SubCategoryResponse;
import peaksoft.entity.Category;
import peaksoft.entity.SubCategory;
import peaksoft.repository.CategoryRepository;
import peaksoft.repository.SubcategoryRepository;
import peaksoft.service.SubCategoryService;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Transactional
public class SubCategoryImpl implements SubCategoryService {
    private final SubcategoryRepository repository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public SubCategoryImpl(SubcategoryRepository repository, CategoryRepository categoryRepository) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public SimpleResponse save(SubCategoryRequest request) {
        Category category = categoryRepository.findById(request.categoryId()).orElseThrow(() -> new NoSuchElementException("NOT FOUND.."));
        SubCategory subCategory = new SubCategory();
        subCategory.setName(request.name());
        subCategory.setCategories(category);
        repository.save(subCategory);
        return SimpleResponse.builder().status(HttpStatus.OK).massage("Successfully saved..").build();
    }

    @Override
    public List<SubCategoryResponse> getAll(String word) {
        if (word == null) {
            return repository.getAllSub();
        }
        return null;
    }

    @Override
    public SubCategoryResponse findBiId(Long id) {
        return repository.findByIdSub(id).orElseThrow(() -> new NoSuchElementException("Not found!!!"));
    }

    @Override
    public SimpleResponse delete(Long id) {
        repository.deleteById(id);
        return SimpleResponse.builder().status(HttpStatus.OK).massage("Successfully delete...").build();
    }

    @Override
    public SimpleResponse update(Long id, SubCategoryRequest request) {
        Category category1 = categoryRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Not found!!"));
        SubCategory category = new SubCategory();
        category.setName(request.name());
        category.setCategories(category1);
        repository.save(category);
        return SimpleResponse.builder().status(HttpStatus.OK).massage("Successfully update....").build();
    }

    @Override
    public Map<String, List<SubCategoryResponse>> groupingByCategory() {
        List<SubCategoryResponse> group = repository.grouping();
        return group.stream().collect(Collectors.groupingBy(SubCategoryResponse::categoryName));
    }
}
