package peaksoft.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.NonNull;
import org.hibernate.validator.constraints.Length;

@Builder
public record RestaurantRequest(@NonNull @Length(min=3,max = 20) String name,
                                @NotNull String location,
                                @NotNull String restType,
                                  int numberOfEmployee,

                                @Positive int service) {
}
