package peaksoft.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.request.ChequeRequest;
import peaksoft.dto.response.ChequeResponse;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.service.ChequeService;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;

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
       return chequeService.findAll();
    }
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public SimpleResponse save(@RequestBody ChequeRequest request){
        return chequeService.save(request);
    }
    @GetMapping("/{id}")
    @PreAuthorize("permitAll()")
    public ChequeResponse findById(@PathVariable Long id){
        return chequeService.findById(id);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public SimpleResponse delete(@PathVariable Long id){
        return chequeService.deleteById(id);
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public SimpleResponse update(@PathVariable Long id,ChequeRequest request){
        return chequeService.update(id,request);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/total/{id}")
    public SimpleResponse totalSum(@PathVariable Long id,
                                   @RequestParam(required = false) LocalDate date){
        return chequeService.totalSum(id, Objects.requireNonNullElseGet(date,LocalDate::now));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/avg")
    public SimpleResponse avg(@RequestParam(required = false) LocalDate date){
        return chequeService.avg(Objects.requireNonNullElseGet(date, LocalDate::now));
    }
}
