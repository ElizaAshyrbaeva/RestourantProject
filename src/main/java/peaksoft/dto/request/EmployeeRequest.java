package peaksoft.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import peaksoft.enums.Role;

import java.time.LocalDate;

@Builder
public record EmployeeRequest(
        @NotNull
        Long restId,
        @NotNull
        String firstName,
        @NotNull
        String lastName,
        @NotNull
        LocalDate dateOfBirth,
        @NotNull
        String email,
        @NotNull
        @Size(min = 5, max = 100, message = "Password must be at least 4 characters!")

        String password,
        @NotNull
        String phoneNumber,
        @NotNull
        Role role,
        @NotNull
        int experience) {
}
