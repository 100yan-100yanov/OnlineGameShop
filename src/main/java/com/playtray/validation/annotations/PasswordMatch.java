package com.playtray.validation.annotations;

import com.playtray.validation.validators.PasswordMatchValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = PasswordMatchValidator.class)
public @interface PasswordMatch {

    String message() default "Passwords must match!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
