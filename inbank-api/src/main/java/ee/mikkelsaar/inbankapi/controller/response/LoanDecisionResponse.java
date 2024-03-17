package ee.mikkelsaar.inbankapi.controller.response;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoanDecisionResponse {
  private boolean approved;
  private BigDecimal loanAmount;
  private Integer loanPeriod;
}
