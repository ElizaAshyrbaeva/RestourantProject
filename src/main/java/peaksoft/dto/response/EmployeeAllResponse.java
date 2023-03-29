package peaksoft.dto.response;

import lombok.Builder;
import peaksoft.enums.Role;

import java.time.LocalDate;

@Builder
public record EmployeeAllResponse(Long id,
                                  String fullName,
                                  LocalDate dateOfBirth,
                                  String email,
                                  Role role) {
}
