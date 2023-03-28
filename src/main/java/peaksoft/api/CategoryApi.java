package peaksoft.api;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.request.CategoryRequest;
import peaksoft.dto.response.CategoryResponse;
import peaksoft.dto.response.PaginationResponse;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.service.CategoryService;
import java.util.List;
@RestController
@RequestMapping("/api/category")
public class CategoryApi {
    private final CategoryService categoryService;

    @Autowired
    public CategoryApi(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public SimpleResponse save(@RequestBody @Valid CategoryRequest request) {
        return categoryService.saveCategory(request);
    }
    @GetMapping
    @PreAuthorize("permitAll()")
    public List<CategoryResponse>getAll(){
       return categoryService.getAll();
    }
    @DeleteMapping("/{id}")
//    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public SimpleResponse delete(@PathVariable Long id){
        return categoryService.deleteById(id);
    }
    @GetMapping("/{id}")
    @PreAuthorize("permitAll()")
    public CategoryResponse findById(@PathVariable Long id){
        return categoryService.findById(id);
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public SimpleResponse update(@PathVariable Long id,@RequestBody @Valid CategoryRequest request){
       return categoryService.update(id,request);
    }
    @GetMapping("/pagination")
        public PaginationResponse getCategoryPage(@RequestParam int page,
                                                  @RequestParam int size){
        return categoryService.getBookPage(page,size);

    }
}
