package peaksoft.dto.response;
import lombok.Builder;
@Builder
public record MenuAllResponse(Long id,
                              String categoryName,
                              String subCategoryName,
                              String name,
                              int price) {

}
