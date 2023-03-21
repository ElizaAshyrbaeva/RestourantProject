package peaksoft.dto.response;

import lombok.Builder;
import peaksoft.enums.Role;

import java.time.LocalDate;

@Builder
public record EmployeeResponse (
                                Long id,
                                String fullName,
                                LocalDate dataOfBirth,
                                String email,
                                String password,
                                String phoneNumber,
                                Role role,
                                int experience){
}
