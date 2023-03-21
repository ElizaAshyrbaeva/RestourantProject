package peaksoft.dto.response;
import lombok.Builder;
import peaksoft.entity.MenuItem;

import java.util.List;
import java.util.Set;

@Builder
public record ChequeResponse(Long id,
                             String waiterFullName,
                             double averagePrice,
                             double service,
                             Double total,
                             List<MenuAllResponse> menuItems) {
}
