package ee.mikkelsaar.inbankapi.controller.annotation;

import io.micrometer.common.util.StringUtils;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PersonalCodeValidator implements ConstraintValidator<PersonalCodeValidation, String>
{
  public boolean isValid(String personalCode, ConstraintValidatorContext cxt) {
    // TODO: Mait Mikkelsaar - Do actual personal code validation but this is not in the scope of this test assignment
    return StringUtils.isNotBlank(personalCode) && personalCode.length() == 11 && personalCode.matches("[0-9]+");
  }
}