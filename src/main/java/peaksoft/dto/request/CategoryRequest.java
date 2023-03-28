package peaksoft.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
@Builder
public record CategoryRequest(
        @NotNull
        String name) {
}
