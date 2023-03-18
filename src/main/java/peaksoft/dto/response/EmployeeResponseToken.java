package peaksoft.dto.response;
import lombok.Builder;

@Builder
public record EmployeeResponseToken(String email,
                                    String token) {
}
