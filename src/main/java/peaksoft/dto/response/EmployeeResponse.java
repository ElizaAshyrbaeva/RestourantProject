package peaksoft.dto.response;

import lombok.Builder;
import peaksoft.enums.Role;

import java.time.LocalDate;

@Builder
public record EmployeeResponse (String firstName,
                                String lastName,
                                LocalDate dataOfBirth,
                                String email,
                                String password,
                                String phoneNumber,
                                int experience){
}
