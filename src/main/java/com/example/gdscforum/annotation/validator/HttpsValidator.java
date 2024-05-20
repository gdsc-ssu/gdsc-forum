package com.example.gdscforum.annotation.validator;

import com.example.gdscforum.annotation.Https;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class HttpsValidator implements ConstraintValidator<Https, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value.startsWith("https");
    }
}
