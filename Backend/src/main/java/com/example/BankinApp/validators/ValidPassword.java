package com.example.BankinApp.validators;

import java.lang.annotation.*;
import jakarta.validation.*;

@Constraint(validatedBy = PasswordValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPassword {
    String message() default "Le mot de passe doit contenir au moins une minuscule, une majuscule, un chiffre et un caractère spécial.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
