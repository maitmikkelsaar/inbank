package ee.mikkelsaar.inbankapi.service.impl;

import ee.mikkelsaar.inbankapi.controller.response.LoanDecisionResponse;
import ee.mikkelsaar.inbankapi.profile.ExternalUserProfileService;
import ee.mikkelsaar.inbankapi.model.SEGMENT;
import ee.mikkelsaar.inbankapi.service.LoanDecisionService;
import java.math.BigDecimal;
import java.math.RoundingMode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LoanDecisionServiceImpl implements LoanDecisionService {

  public static final BigDecimal MIN_LOAN_AMOUNT = new BigDecimal(2000);
  public static final BigDecimal MAX_LOAN_AMOUNT = new BigDecimal(10000);
  public static final Integer MIN_LOAN_PERIOD = 12;
  public static final Integer MAX_LOAN_PERIOD = 60;

  private final ExternalUserProfileService externalUserProfileService;

  @Override
  public LoanDecisionResponse handleDecision(final String personalCode, final BigDecimal amount, final Integer period) {
    // for a more complex system I would add input validation here
    // but for this simple system I will skip it because I know the validation is done on the controller layer

    // mocked external service
    final SEGMENT userSegment = externalUserProfileService.getUserSegment(personalCode);

    return getDecision(userSegment, amount, period);
  }

  protected LoanDecisionResponse getDecision(final SEGMENT userSegment, final BigDecimal amount, final Integer period) {
    if (SEGMENT.DEBT == userSegment) {
      return new LoanDecisionResponse(false, amount, period);
    }

    final Integer creditModifier = userSegment.getCreditModifier();

    // (creditModifier / amount) * period == 1;
    // creditModifier * period == amount;
    final BigDecimal maxPossibleLoanAmount = BigDecimal.valueOf(creditModifier).multiply(BigDecimal.valueOf(period));

    BigDecimal finalAmount;

    if (maxPossibleLoanAmount.compareTo(MIN_LOAN_AMOUNT) < 0) {
      //smaller than minLoanAmount
      return getDecisionWithNewPeriod(creditModifier, amount, period);
    } else if (maxPossibleLoanAmount.compareTo(MAX_LOAN_AMOUNT) >= 0) {
      // bigger than max loan amount
      finalAmount = MAX_LOAN_AMOUNT;
    } else {
      finalAmount = maxPossibleLoanAmount;
    }

    return new LoanDecisionResponse(true, finalAmount, period);
  }

  private LoanDecisionResponse getDecisionWithNewPeriod(final Integer creditModifier, final BigDecimal amount, final Integer originalPeriod) {
    // (creditModifier / amount) * period == 1;
    // amount / creditModifier == period;
    final BigDecimal newPeriod = amount.divide(BigDecimal.valueOf(creditModifier), 0, RoundingMode.CEILING);

    if (newPeriod.compareTo(BigDecimal.valueOf(MIN_LOAN_PERIOD)) < 0) {
      return new LoanDecisionResponse(false, amount, originalPeriod);
    } else if (newPeriod.compareTo(BigDecimal.valueOf(MAX_LOAN_PERIOD)) > 0) {
      return new LoanDecisionResponse(false, amount, originalPeriod);
    } else {
      return new LoanDecisionResponse(true, amount, newPeriod.intValue());
    }
  }
}
