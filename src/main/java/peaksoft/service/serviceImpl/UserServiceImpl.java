package peaksoft.service.serviceImpl;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import peaksoft.config.jwt.JwtService;
import peaksoft.dto.request.UserRequest;
import peaksoft.dto.response.UserResponse;
import peaksoft.entity.User;
import peaksoft.enums.Role;
import peaksoft.repository.UserRepository;
import peaksoft.service.UserService;

import java.util.NoSuchElementException;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;

    public UserServiceImpl(UserRepository userRepository, JwtService jwtService, PasswordEncoder encoder, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.encoder = encoder;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public UserResponse authenticate(UserRequest userRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userRequest.email(), userRequest.password()));
        User user = userRepository.findByEmail(userRequest.email()).orElseThrow(() -> new NoSuchElementException(String.format("User with email: %s doesn't exists", userRequest.email())));
        String token = jwtService.generateToken(user);
        return UserResponse.builder().token(token).email(user.getEmail()).build();
    }


    @PostConstruct
    public void init() {
        if (!userRepository.existsByEmail("admin@gmail.com")) {
            User user = new User();
            user.setEmail("admin@gmail.com");
            user.setPassword(encoder.encode("admin"));
            user.setRole(Role.ADMIN);
            userRepository.save(user);
        }
    }


}

