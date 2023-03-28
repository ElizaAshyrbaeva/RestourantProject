package peaksoft.api;

import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.request.AcceptOrRejectRequest;
import peaksoft.dto.request.EmployeeRequest;
import peaksoft.dto.response.EmployeeResponse;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.service.EmployeeService;
import peaksoft.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeApi {
    private final EmployeeService service;

    public EmployeeApi(EmployeeService service) {
        this.service = service;
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/{id}")
    public EmployeeResponse findById(@PathVariable Long id) {
        return service.findById(id);
    }

//    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public SimpleResponse delete(@PathVariable Long id) {
        return service.deleteById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public SimpleResponse update(@PathVariable Long id,
                                 @RequestBody @Valid EmployeeRequest employeeRequest) {
        return service.update(id, employeeRequest);
    }

    @GetMapping("/applications")
    @PreAuthorize("hasAuthority('ADMIN')")
    List<EmployeeResponse> getApplications() {
        return service.getApplications();
    }

    @PostMapping("/acceptOrReject")
    @PermitAll
    SimpleResponse acceptOrReject(@RequestBody @Valid AcceptOrRejectRequest request) {
        return service.acceptOrReject(request);
    }

    @PostMapping("/{id}/save")
    @PreAuthorize("hasAuthority('ADMIN')")
    public SimpleResponse save(@RequestBody @Valid EmployeeRequest employeeRequest,
                               @PathVariable Long id) {
        return service.save(employeeRequest, id);
    }

    @GetMapping
    @PermitAll
    public List<EmployeeResponse> findAll(){
        return service.getAll();
    }
}
