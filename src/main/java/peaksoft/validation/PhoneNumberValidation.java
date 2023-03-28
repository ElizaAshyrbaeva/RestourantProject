package peaksoft.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PhoneNumberValidation  implements ConstraintValidator<PhoneNumber,String> {
    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext constraintValidatorContext) {
        return phoneNumber.length() == 13 && phoneNumber.startsWith("+996");
    }
}
