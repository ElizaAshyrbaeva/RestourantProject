package peaksoft.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Builder;
import org.springframework.lang.NonNull;

import java.util.List;

@Builder
public record ChequeRequest(
        @NotNull
        Long userId,
        @NotNull
        List<Long> menuId) {
}
