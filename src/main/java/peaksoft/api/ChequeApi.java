package peaksoft.api;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.request.ChequeRequest;
import peaksoft.dto.response.ChequeResponse;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.service.ChequeService;
import java.util.List;

@RestController
@RequestMapping("/api/check")
public class ChequeApi {
    private final ChequeService chequeService;

    @Autowired
    public ChequeApi(ChequeService chequeService) {
        this.chequeService = chequeService;
    }
    @GetMapping
    @PreAuthorize("permitAll()")
    public List<ChequeResponse>getAll(){
       return chequeService.getAll();
    }
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public SimpleResponse save(@RequestBody @Valid ChequeRequest request){
        return chequeService.save(request);
    }
    @GetMapping("/{id}")
    @PreAuthorize("permitAll()")
    public ChequeResponse findById(@PathVariable Long id){
        return chequeService.getById(id);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public SimpleResponse delete(@PathVariable Long id){
        return chequeService.deleteById(id);
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public SimpleResponse update(@PathVariable @Valid Long id,ChequeRequest request){
        return chequeService.update(id,request);
    }
//    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/total/{id}")
    public Double totalSum(@PathVariable Long id){
        return chequeService.getAllChequesByUser(id);
    }
//    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/avg/{id}")
    public Double avg(@PathVariable Long id){
        return chequeService.getAverageSum(id);
    }
}
