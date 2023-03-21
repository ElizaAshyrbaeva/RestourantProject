package peaksoft.dto.response;

import peaksoft.enums.Role;

public record EmployeeAllResponse(Long id,
                                  String fullName,
                                  String email,
                                  Role role) {
}
