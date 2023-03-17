package peaksoft.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.request.EmployeeRequest;
import peaksoft.dto.response.EmployeeResponse;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeApi {
    private final EmployeeService service;
    public EmployeeApi(EmployeeService service) {
        this.service = service;
    }
    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','WAITER')")
    public SimpleResponse save(@RequestBody EmployeeRequest employeeRequest){
        return service.saveEmployee(employeeRequest);
    }
    @PreAuthorize("permitAll()")
    @GetMapping
    public List<EmployeeResponse> getAll(){
       return service.getAll();
    }
    @PreAuthorize("permitAll()")
    @GetMapping("/{id}")
    public EmployeeResponse findById(@PathVariable Long id){
        return service.findById(id);
    }
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public SimpleResponse delete(@PathVariable Long id){
        return service.deleteById(id);
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public SimpleResponse update(@PathVariable Long id,@RequestBody EmployeeRequest employeeRequest){
        return update(id,employeeRequest);
    }


}
