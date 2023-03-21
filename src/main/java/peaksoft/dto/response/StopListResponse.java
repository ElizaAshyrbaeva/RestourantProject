package peaksoft.dto.response;

import lombok.Builder;

import java.time.LocalDate;
@Builder
public record StopListResponse(String reason,
                               LocalDate date,
                               String menuName) {
}
