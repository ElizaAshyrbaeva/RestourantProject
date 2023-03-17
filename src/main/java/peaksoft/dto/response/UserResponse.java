package peaksoft.dto.response;

import lombok.Builder;

@Builder
public record UserResponse(String email,
                           String token) {
}
