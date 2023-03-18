package peaksoft.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.CategoryRequest;
import peaksoft.dto.response.CategoryResponse;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.entity.Category;
import peaksoft.repository.CategoryRepository;
import peaksoft.service.CategoryService;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository repository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public SimpleResponse saveCategory(CategoryRequest request) {
        Category category = new Category();
        category.setName(request.name());
        repository.save(category);
        return SimpleResponse.builder().status(HttpStatus.OK).massage("Successfully saved..").build();
    }
    @Override
    public List<CategoryResponse> getAll() {
        return repository.getAllCategory();
    }

    @Override
    public CategoryResponse findById(Long id) {
        return repository.getByIdCategory(id).orElseThrow(()->new NoSuchElementException(String.format("Category with name: %s doesn't exists")));
    }

    @Override
    public SimpleResponse deleteById(Long id) {
        repository.deleteById(id);
        return SimpleResponse.builder().status(HttpStatus.OK).massage("Successfully delete.").build();
    }

    @Override
    public SimpleResponse update(Long id, CategoryRequest request) {
        Category category = new Category();
        category.setName(request.name());
        repository.save(category);
        return SimpleResponse.builder().status(HttpStatus.OK).massage("Update").build();
    }
}