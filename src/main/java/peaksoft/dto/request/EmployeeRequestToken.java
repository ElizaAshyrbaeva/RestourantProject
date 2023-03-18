package peaksoft.dto.request;

import lombok.Builder;

@Builder
public record EmployeeRequestToken(String email,
                                   String password) {

}
