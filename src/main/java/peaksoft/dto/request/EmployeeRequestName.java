package peaksoft.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import peaksoft.enums.Role;
import peaksoft.validation.PhoneNumber;

import java.time.LocalDate;

@Builder
public record EmployeeRequestName(
        @NotNull
        String firstName,
        @NotNull
        String lastName,
        @NotNull
        LocalDate dateOfBirth,
        @NotNull
        String email,
        @NotNull
        String password,
        @NotNull
        @PhoneNumber
        String phoneNumber,
        @NotNull
        Role role,
        @NotNull
        int experience,
        boolean accseptet
) {

}
