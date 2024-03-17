package ee.mikkelsaar.inbankapi.controller;

import ee.mikkelsaar.inbankapi.controller.request.LoanDecisionRequest;
import ee.mikkelsaar.inbankapi.controller.response.LoanDecisionResponse;
import ee.mikkelsaar.inbankapi.service.impl.LoanDecisionServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class LoanDecisionController {

  private final LoanDecisionServiceImpl loanDecisionService;

  @PostMapping("/loan-decision")
  public LoanDecisionResponse giveLoanDecision(@Valid @RequestBody LoanDecisionRequest loanDecisionRequest) {
    return loanDecisionService.handleDecision(loanDecisionRequest.getPersonalCode(), loanDecisionRequest.getLoanAmount(),
        loanDecisionRequest.getLoanPeriod());
  }
}
