package peaksoft.api;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.request.RestaurantRequest;
import peaksoft.dto.response.RestaurantResponse;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.service.RestaurantService;

import java.util.List;

@RestController
@RequestMapping("/api/rest")
public class RestaurantApi {
    private final RestaurantService service;
    @Autowired
    public RestaurantApi( RestaurantService service) {
        this.service = service;
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public SimpleResponse save(@RequestBody  @Valid RestaurantRequest request){
        return service.save(request);
    }
    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','CHEF','WAITER')")
    public List<RestaurantResponse> getAll(){
        return service.getAll();
    }

    @GetMapping("/{restId}")
    @PreAuthorize("permitAll()")
    public RestaurantResponse findById(@PathVariable Long restId){
        return service.getById(restId);
    }

    @DeleteMapping("/{restId}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public SimpleResponse deleteRest(@PathVariable Long restId){
        return service.deleteById(restId);
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public SimpleResponse updateRest(@RequestBody @Valid RestaurantRequest request , @PathVariable  Long id){
        return service.updateRest(id,request);
    }

}
