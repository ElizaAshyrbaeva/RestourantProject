package peaksoft.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
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
    private final UserService userService;
    public EmployeeApi(EmployeeService service, UserService userService) {
        this.service = service;
        this.userService = userService;
    }
    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','WAITER')")
    public SimpleResponse save(@RequestBody @Validated EmployeeRequest employeeRequest){
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
//    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public SimpleResponse update(@PathVariable Long id,@RequestBody EmployeeRequest employeeRequest) {
        return service.update(id, employeeRequest);
    }
    @PostMapping("/application")
    public SimpleResponse application(@RequestBody EmployeeRequest employeeRequest){
        return service.application(employeeRequest);
    }
//    @PostMapping
//    public  SimpleResponse applications(@RequestParam (required = false)Long id,
//                                        @RequestParam(required = false)Boolean accepted){
//        return userService.application(id,accepted);
//    }

}
