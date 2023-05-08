package com.tugalsan.api.annotation.server;

import jakarta.validation.*;
import jakarta.validation.constraints.*;
import java.lang.annotation.*;

@NotNull
@Size(min = 2, max = 14)
@CheckCase(CaseMode.UPPER)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@Documented
//@ReportAsSingleViolation
public @interface ValidLicensePlate {

    String message() default "{com.mycompany.constraints.validlicenseplate}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
