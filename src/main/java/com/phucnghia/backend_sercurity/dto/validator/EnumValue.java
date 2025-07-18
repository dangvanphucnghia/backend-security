package com.phucnghia.backend_sercurity.dto.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = EnumPatternValidator.class)
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
public @interface EnumValue {
    String name() default "";

    String message() default "invalid.enum.value";

    Class<? extends  Enum> enumClass();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
