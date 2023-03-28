package peaksoft.api;

import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.request.MenuItemRequest;
import peaksoft.dto.response.MenuAllResponse;
import peaksoft.dto.response.MenuItemResponse;
import peaksoft.dto.response.PaginationResponse;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.service.MenuItemService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/menu")
public class MenuItemApi {
    private final MenuItemService service;
    @Autowired
    public MenuItemApi(MenuItemService service) {
        this.service = service;
    }
    @PreAuthorize("hasAnyAuthority('ADMIN','CHEF')")
    @PostMapping
    public SimpleResponse save(@RequestBody @Valid MenuItemRequest request){
        return service.save(request);
    }
    @PreAuthorize("permitAll()")
    @GetMapping
    public List<MenuItemResponse>getAll(@RequestParam(required = false) String sort){
        return service.sort(sort);
    }
    @PreAuthorize("permitAll()")
    @GetMapping("/{id}")
    public MenuItemResponse findById(@PathVariable Long id){
        return service.findById(id);
    }
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PutMapping("/{id}")
    public SimpleResponse update(@PathVariable Long id,@RequestBody @Valid MenuItemRequest request){
        return service.update(id,request);
    }
//    @PreAuthorize("hasAnyAuthority('ADMIN','CHEF')")
    @DeleteMapping("/{id}")
    public SimpleResponse delete(@PathVariable Long id){
        return service.delete(id);
    }
    @PermitAll
    @GetMapping("/search")
    public List<MenuItemResponse>search(@RequestParam String word){
        return service.globalSearch(word);
    }
    @PermitAll
    @GetMapping("/sort")
    public List<MenuItemResponse>sortByPrice(@RequestParam String sort){
        return service.sort(sort);
    }
    @PermitAll
    @GetMapping("/grouping")
    public Map<Boolean, List<MenuItemResponse>> filter(){
        return service.filterByVegetarian();
    }

    @PermitAll
    @GetMapping("/pagination")
    public PaginationResponse getMenuPage(@RequestParam int page,
                                          @RequestParam int size) {
        return service.getItemPagination(page, size);
    }
    @GetMapping("/get")
    @PermitAll
    public List<MenuItemResponse>getAll(){
        return service.getAll();
    }
}
