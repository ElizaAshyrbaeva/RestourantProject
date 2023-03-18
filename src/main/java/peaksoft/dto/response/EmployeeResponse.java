package peaksoft.dto.response;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record EmployeeResponse (
                                Long id,
                                String fullName,
                                LocalDate dataOfBirth,
                                String email,
                                String password,
                                String phoneNumber,
                                int experience){
}
