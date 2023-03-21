package peaksoft.service;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.dto.request.EmployeeRequest;
import peaksoft.dto.request.UserRequest;
import peaksoft.dto.response.EmployeeAllResponse;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.dto.response.UserResponse;
import peaksoft.entity.Employee;
import peaksoft.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserService {
    UserResponse authenticate(UserRequest authRequest);

}
