package peaksoft.service;

import org.springframework.stereotype.Repository;
import peaksoft.dto.request.UserRequest;
import peaksoft.dto.response.UserResponse;
import peaksoft.entity.User;

import java.util.Optional;

@Repository
public interface UserService {
    UserResponse authenticate(UserRequest authRequest);

}
