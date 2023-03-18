package peaksoft.api;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.request.SubCategoryRequest;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.dto.response.SubCategoryResponse;
import peaksoft.service.SubCategoryService;
import java.util.List;

@RestController
@RequestMapping("/api/subCategory")
public class SubCategoryApi {
    private final SubCategoryService categoryService;

    public SubCategoryApi(SubCategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @PostMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public SimpleResponse save(@RequestBody SubCategoryRequest category,@PathVariable Long id){
        return categoryService.save(id,category);
    }
    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','WAITER','CHEF')")
    public List<SubCategoryResponse>getAll(){
        return categoryService.getAll();
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public SimpleResponse delete(@PathVariable Long id){
        return categoryService.delete(id);
    }
    @GetMapping("/{id}")
    @PreAuthorize("permitAll()")
    public SubCategoryResponse findById(@PathVariable Long id){
        return categoryService.findBiId(id);
    }
    @PutMapping
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public SimpleResponse update(Long id,@RequestBody SubCategoryRequest categoryRequest){
        return categoryService.update(id,categoryRequest);
    }

}