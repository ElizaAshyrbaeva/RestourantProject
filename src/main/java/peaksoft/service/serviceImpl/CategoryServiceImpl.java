package peaksoft.service.serviceImpl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.CategoryRequest;
import peaksoft.dto.response.CategoryResponse;
import peaksoft.dto.response.PaginationResponse;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.entity.Category;
import peaksoft.exceptions.AlreadyExistException;
import peaksoft.exceptions.NotFoundException;
import peaksoft.repository.CategoryRepository;
import peaksoft.service.CategoryService;
import java.awt.print.Pageable;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository repository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public SimpleResponse saveCategory(CategoryRequest request) {
        if (repository.existsByName(request.name())) {
            return SimpleResponse.builder().status(HttpStatus.CONFLICT).massage("Given name  is already exists!").build();
        }
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
        repository.findById(id).orElseThrow(()->new NotFoundException("Category with this id not found:"+id));
        repository.deleteById(id);
        return SimpleResponse.builder().status(HttpStatus.OK).massage("Successfully delete.").build();
    }

    @Override
    public SimpleResponse update(Long id, CategoryRequest request) {
        Category category = repository.findById(id).orElseThrow(() -> new NoSuchElementException("Not Found!"));
        category.setName(request.name());
        repository.save(category);
        return SimpleResponse.builder().status(HttpStatus.OK).massage("Update").build();
    }

    @Override
    public PaginationResponse getBookPage(int page, int size) {
//        Pageable pageable = PageRequest.of(size,page);
//        PaginationResponse response= new PaginationResponse();
//        pageable.set
        return null;
    }
}
