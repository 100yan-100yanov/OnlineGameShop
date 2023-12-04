package com.playtray.model.validation.validators;

import com.playtray.model.dto.UserRegisterDTO;
import com.playtray.model.validation.annotations.PasswordMatch;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorContext;

public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch, UserRegisterDTO> {

    private String message;

    @Override
    public void initialize(PasswordMatch constraintAnnotation) {

        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(UserRegisterDTO userRegisterDTO, ConstraintValidatorContext context) {

        final String password = userRegisterDTO.getPassword();
        final String confirmPassword = userRegisterDTO.getConfirmPassword();

        if (password == null && confirmPassword == null) {
            return true;

        } else {
            boolean passwordMatch = password != null && password.equals(confirmPassword);

            if (!passwordMatch) {
                replaceDefaultConstraintViolation(context, message);

            }
            return passwordMatch;
        }
    }

    private void replaceDefaultConstraintViolation(ConstraintValidatorContext context, String message) {

        context
                .unwrap(HibernateConstraintValidatorContext.class)
                .buildConstraintViolationWithTemplate(message)
                .addPropertyNode("confirmPassword")
                .addConstraintViolation()
                .disableDefaultConstraintViolation();

    }
}
