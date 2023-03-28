package peaksoft.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import org.hibernate.validator.constraints.Length;

@Builder
public record SubCategoryRequest(
        @NotNull
        @Length(min = 3, max = 25)
        String name,
        @NotNull
        @Positive
        Long categoryId) {
}
