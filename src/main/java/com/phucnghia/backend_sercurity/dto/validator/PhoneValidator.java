package com.phucnghia.backend_sercurity.dto.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class PhoneValidator implements ConstraintValidator<PhoneNumber, String> {

    private static final Pattern BASIC_PATTERN = Pattern.compile("^\\d{10}$");
    private static final Pattern DASH_DOT_SPACE_PATTERN = Pattern.compile("^\\d{3}[-.\\s]\\d{3}[-.\\s]\\d{4}$");
    private static final Pattern EXTENSION_PATTERN = Pattern.compile("^\\d{3}-\\d{3}-\\d{4}\\s(x|ext)\\d{3,5}$");
    private static final Pattern BRACKET_AREA_PATTERN = Pattern.compile("^\\(\\d{3}\\)-\\d{3}-\\d{4}$");

    @Override
    public void initialize(PhoneNumber constraintAnnotation) {
        //
    }

    @Override
    public boolean isValid(String phoneNo, ConstraintValidatorContext context) {
        if(phoneNo == null) {
            return false;
        }

        return BASIC_PATTERN.matcher(phoneNo).matches()
                || DASH_DOT_SPACE_PATTERN.matcher(phoneNo).matches()
                || EXTENSION_PATTERN.matcher(phoneNo).matches()
                || BRACKET_AREA_PATTERN.matcher(phoneNo).matches();
    }
}
