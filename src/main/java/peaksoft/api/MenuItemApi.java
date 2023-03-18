package peaksoft.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.request.MenuItemRequest;
import peaksoft.dto.response.MenuItemResponse;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.service.MenuItemService;

import java.util.List;

@RestController
@RequestMapping("/api/menu")
public class MenuItemApi {
    private final MenuItemService service;
    public MenuItemApi(MenuItemService service) {
        this.service = service;
    }
    @PreAuthorize("hasAnyAuthority('ADMIN','CHEF')")
    @PostMapping("/{id}")
    public SimpleResponse save(@PathVariable Long id,@RequestBody MenuItemRequest request){
        return service.save(id,request);
    }
    @PreAuthorize("permitAll()")
    @GetMapping
    public List<MenuItemResponse> getAll(){
        return service.getAll();
    }
    @PreAuthorize("permitAll()")
    @GetMapping("/{id}")
    public MenuItemResponse findById(@PathVariable Long id){
        return service.findById(id);
    }
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PutMapping("/{id}")
    public SimpleResponse update(@PathVariable Long id,@RequestBody MenuItemRequest request){
        return service.update(id,request);
    }
    @PreAuthorize("hasAnyAuthority('ADMIN','CHEF')")
    @DeleteMapping("/{id}")
    public SimpleResponse delete(@PathVariable Long id){
        return service.delete(id);
    }
}
