package peaksoft.dto.request;

import lombok.Builder;

@Builder
public record MenuItemRequest(String name,
                              String image,
                              int price,
                              String description,
                              Long restId) {
}
