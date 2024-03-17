package ee.mikkelsaar.inbankapi.service;

import ee.mikkelsaar.inbankapi.controller.response.LoanDecisionResponse;
import java.math.BigDecimal;

public interface LoanDecisionService {
  LoanDecisionResponse handleDecision(String personalCode, BigDecimal amount, Integer period);
}
