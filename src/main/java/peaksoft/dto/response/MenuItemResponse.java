package peaksoft.dto.response;
import lombok.Builder;

@Builder
public record MenuItemResponse(Long id,
                               String name,
                               String image,
                               int price,
                               String description,
                               boolean isVegetarian
                               ) {
}
