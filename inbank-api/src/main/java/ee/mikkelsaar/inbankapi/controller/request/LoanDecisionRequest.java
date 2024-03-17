package ee.mikkelsaar.inbankapi.controller.request;

import ee.mikkelsaar.inbankapi.controller.annotation.PersonalCodeValidation;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoanDecisionRequest {

  @PersonalCodeValidation()
  private String personalCode;

  @NotNull(message = "Loan amount is required.")
  @Min(value = 2000, message = "Loan amount must be greater or equal to 2000.")
  @Max(value = 10000, message = "Loan amount must be less or equal to 10000.")
  private BigDecimal loanAmount;

  @NotNull(message = "Loan period is required.")
  @Min(value = 12, message = "Loan period must be greater or equal to 12.")
  @Max(value = 60, message = "Loan period must be less or equal to 60.")
  private Integer loanPeriod;
}
