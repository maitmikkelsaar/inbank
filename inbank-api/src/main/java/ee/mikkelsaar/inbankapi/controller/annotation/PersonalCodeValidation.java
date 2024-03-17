package ee.mikkelsaar.inbankapi.controller.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target( { FIELD, PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = PersonalCodeValidator.class)
public @interface PersonalCodeValidation {

  public String message() default "Invalid peronal code.";

  public Class<?>[] groups() default {};

  public Class<? extends Payload>[] payload() default {};
}