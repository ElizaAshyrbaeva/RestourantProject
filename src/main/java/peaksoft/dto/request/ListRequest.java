package peaksoft.dto.request;

import lombok.Builder;

@Builder
public record ListRequest (String reason,) {
}
