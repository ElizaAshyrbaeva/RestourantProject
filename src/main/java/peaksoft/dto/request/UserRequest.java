package peaksoft.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import org.springframework.validation.annotation.Validated;
@Builder
public record UserRequest(
        @Email(message = "Invalid email!")
                @NotNull(message = "email cannot be empty!")
        String email,
        @Size(min = 5, max = 100, message = "Password must be at least 4 characters!")
        @NotNull(message = "password cannot be empty!")
        String password) {
}
