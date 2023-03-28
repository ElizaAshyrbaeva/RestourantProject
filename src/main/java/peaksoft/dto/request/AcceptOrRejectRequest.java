package peaksoft.dto.request;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.NonNull;
import org.hibernate.annotations.NotFound;

@Builder
public record AcceptOrRejectRequest(
        @NonNull
        Long restaurantId,
        @NotNull
        Long employeeId,
        @NotNull
        boolean isTrue
) {
}
