package peaksoft.dto.response;
import lombok.Builder;
import lombok.Data;
import peaksoft.entity.MenuItem;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Builder
public record ChequeResponse(  Long checkId,
                               String user,
                               List<String> menuItems,
                               LocalDate createdAt,
                               double priceAverage,
                               double service,
                               double total) {


}
