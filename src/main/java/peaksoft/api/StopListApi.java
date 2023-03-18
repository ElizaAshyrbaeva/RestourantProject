package peaksoft.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.request.StopListRequest;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.service.StopListService;


@RequestMapping("/api/list")
@RestController
public class StopListApi {
    private final StopListService listService;
    @Autowired
    public StopListApi(StopListService listService) {
        this.listService = listService;
    }

    @PostMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    private SimpleResponse save(@PathVariable Long id, @RequestBody StopListRequest request) {
        return listService.save(id, request);
    }
}

//    @GetMapping
//    @PreAuthorize("permitAll()")
//    public List<StopListResponse> getAll() {
//        return listService.getAll();
//    }
//
//    @DeleteMapping("/{id}")
//    public SimpleResponse delete(@PathVariable Long id) {
//        return listService.delete(id);
//    }
//
//    @GetMapping("/{id}")
//    public StopListResponse findBiId(@PathVariable Long id) {
//        return listService.findById(id);
//    }
//    @PutMapping("/{id}")
//    public SimpleResponse update(@PathVariable Long id,@RequestBody StopListRequest request){
//        return listService.update(id,request);
//    }
//}
