package peaksoft.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import peaksoft.dto.request.EmployeeRequest;
import peaksoft.dto.request.UserRequest;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.dto.response.UserResponse;
import peaksoft.service.EmployeeService;
import peaksoft.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserApi {
    private final UserService userService;
    private final EmployeeService service;

    public UserApi(UserService userService, EmployeeService service) {
        this.userService = userService;
        this.service = service;
    }

    @PostMapping
    public UserResponse login(@RequestBody UserRequest userRequest) {
        return userService.authenticate(userRequest);
    }
    @PostMapping("/application")
    public SimpleResponse application(@RequestBody EmployeeRequest employeeRequest){
        return service.acceptOrReject(employeeRequest);
    }
}
