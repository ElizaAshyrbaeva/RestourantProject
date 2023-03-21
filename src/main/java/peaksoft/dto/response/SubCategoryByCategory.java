package peaksoft.dto.response;

import lombok.Builder;

@Builder
public record SubCategoryByCategory(String categoryName,
                                    String subcategoryName) {
}
