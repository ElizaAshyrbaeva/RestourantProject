package peaksoft.dto.request;

import lombok.Builder;

@Builder
public record AcceptOrRejectRequest(
        Long restaurantId,
        Long employeeId,
        boolean isTrue
) {
}
