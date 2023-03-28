package peaksoft.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record StopListRequest(
        @NotNull

        String reason,
        @NotNull
        LocalDate date,
        @NotNull
        @Positive
        Long menuId) {
}
