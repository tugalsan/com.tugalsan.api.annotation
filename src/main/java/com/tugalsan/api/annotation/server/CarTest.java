package com.tugalsan.api.annotation.server;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.Set;

public class CarTest {

    private static Validator validator;

    public static void main(String... args) {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
        //testLicensePlateNotUpperCase
        {
            var car = new Car("Morris", "dd-ab-123", 4);
            var constraintViolations = validator.validate(car);
            assertEquals(1, constraintViolations.size());
            assertEquals("Case mode must be UPPER.", constraintViolations.iterator().next().getMessage());
        }
        //carIsValid()
        {
            var car = new Car("Morris", "DD-AB-123", 4);
            var constraintViolations = validator.validate(car);
            assertEquals(0, constraintViolations.size());
        }
    }
}
