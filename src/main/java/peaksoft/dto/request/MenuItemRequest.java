package peaksoft.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import org.hibernate.validator.constraints.Length;

@Builder
public record MenuItemRequest(@NotNull @Length(min = 3,max = 25) String name,
                               String image,
                             @NotNull @Positive int price,
                             String description,
                              Boolean isVegetarian,
                             @NotNull Long restId,
                             @NotNull  Long subCategoryId) {
}
