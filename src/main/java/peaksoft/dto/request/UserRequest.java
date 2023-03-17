package peaksoft.dto.request;

import lombok.Builder;
import org.springframework.validation.annotation.Validated;
@Builder
public record UserRequest(
        String email,
        String password) {
}
