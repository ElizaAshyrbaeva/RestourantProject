package peaksoft.api;

import jakarta.annotation.security.PermitAll;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.request.StopListRequest;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.entity.StopList;
import peaksoft.service.StopListService;

import java.util.List;

@RestController
@RequestMapping("/api/list")
public class StopListApi {
    private final StopListService stopListService;

    public StopListApi(StopListService stopListService) {
        this.stopListService = stopListService;
    }

    @PostMapping("{menuItemId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public SimpleResponse save(@RequestBody StopListRequest stopListRequest, @PathVariable Long menuItemId){
       return stopListService.save(menuItemId, stopListRequest);
    }
    @GetMapping
    @PermitAll
    public List<StopList> findAll() {
        return stopListService.getAll();
    }
    @GetMapping("/{id}")
    @PreAuthorize("permitAll()")
    public StopList findById(@PathVariable Long id){
        return stopListService.findById(id);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public SimpleResponse delete(@PathVariable Long id){
        return stopListService.delete(id);
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public SimpleResponse update(@PathVariable Long id,StopListRequest listRequest){
        return stopListService.update(id,listRequest);
    }

}

