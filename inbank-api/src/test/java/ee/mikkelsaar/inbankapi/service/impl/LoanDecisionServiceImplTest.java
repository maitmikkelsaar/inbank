package ee.mikkelsaar.inbankapi.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import ee.mikkelsaar.inbankapi.controller.response.LoanDecisionResponse;
import ee.mikkelsaar.inbankapi.model.SEGMENT;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class LoanDecisionServiceImplTest {

  private final LoanDecisionServiceImpl loanDecisionService = new LoanDecisionServiceImpl(null);

  // Skiping input validation tests for this is handled in controller, see comment in LoanDecisionService.handleDecision method

  @Test
  void handleDecision_false2000_12__DEBT_2000_12() {
    final SEGMENT segment = SEGMENT.DEBT;
    final BigDecimal amount = new BigDecimal(2000);
    final Integer period = 12;

    final LoanDecisionResponse decision = loanDecisionService.getDecision(segment, amount, period);
    assertFalse(decision.isApproved());
    assertEquals(amount, decision.getLoanAmount());
    assertEquals(12, decision.getLoanPeriod());
  }

  @Test
  void handleDecision_false10000_12__DEBT_10000_12() {
    final SEGMENT segment = SEGMENT.DEBT;
    final BigDecimal amount = new BigDecimal(10000);
    final Integer period = 12;

    final LoanDecisionResponse decision = loanDecisionService.getDecision(segment, amount, period);
    assertFalse(decision.isApproved());
    assertEquals(amount, decision.getLoanAmount());
    assertEquals(12, decision.getLoanPeriod());
  }

  @Test
  void handleDecision_true2000_60__DEBT_2000_60() {
    final SEGMENT segment = SEGMENT.DEBT;
    final BigDecimal amount = new BigDecimal(2000);
    final Integer period = 60;

    final LoanDecisionResponse decision = loanDecisionService.getDecision(segment, amount, period);
    assertFalse(decision.isApproved());
    assertEquals(amount, decision.getLoanAmount());
    assertEquals(60, decision.getLoanPeriod());
  }

  @Test
  void handleDecision_false10000_60__DEBT_10000_60() {
    final SEGMENT segment = SEGMENT.DEBT;
    final BigDecimal amount = new BigDecimal(10000);
    final Integer period = 60;

    final LoanDecisionResponse decision = loanDecisionService.getDecision(segment, amount, period);
    assertFalse(decision.isApproved());
    assertEquals(amount, decision.getLoanAmount());
    assertEquals(60, decision.getLoanPeriod());
  }

  @Test
  void handleDecision_true6000_60__SEGMENT_1_2000_60() {
    final SEGMENT segment = SEGMENT.SEGMENT_1;
    final BigDecimal amount = new BigDecimal(2000);
    final Integer period = 60;

    final LoanDecisionResponse decision = loanDecisionService.getDecision(segment, amount, period);
    assertTrue(decision.isApproved());
    assertEquals(new BigDecimal(6000), decision.getLoanAmount());
    assertEquals(period, decision.getLoanPeriod());
  }

  @Test
  void handleDecision_true6000_60__SEGMENT_1_10000_60() {
    final SEGMENT segment = SEGMENT.SEGMENT_1;
    final BigDecimal amount = new BigDecimal(10000);
    final Integer period = 60;

    final LoanDecisionResponse decision = loanDecisionService.getDecision(segment, amount, period);
    assertTrue(decision.isApproved());
    assertEquals(new BigDecimal(6000), decision.getLoanAmount());
    assertEquals(period, decision.getLoanPeriod());
  }

  @Test
  void handleDecision_true3600_12__SEGMENT_2_2000_12() {
    final SEGMENT segment = SEGMENT.SEGMENT_2;
    final BigDecimal amount = new BigDecimal(2000);
    final Integer period = 12;

    final LoanDecisionResponse decision = loanDecisionService.getDecision(segment, amount, period);
    assertTrue(decision.isApproved());
    assertEquals(new BigDecimal(3600), decision.getLoanAmount());
    assertEquals(12, decision.getLoanPeriod());
  }

  @Test
  void handleDecision_true3600_12__SEGMENT_2_10000_12() {
    final SEGMENT segment = SEGMENT.SEGMENT_2;
    final BigDecimal amount = new BigDecimal(10000);
    final Integer period = 12;

    final LoanDecisionResponse decision = loanDecisionService.getDecision(segment, amount, period);
    assertTrue(decision.isApproved());
    assertEquals(new BigDecimal(3600), decision.getLoanAmount());
    assertEquals(12, decision.getLoanPeriod());
  }

  @Test
  void handleDecision_true10000_60__SEGMENT_2_2000_60() {
    final SEGMENT segment = SEGMENT.SEGMENT_2;
    final BigDecimal amount = new BigDecimal(2000);
    final Integer period = 60;

    final LoanDecisionResponse decision = loanDecisionService.getDecision(segment, amount, period);
    assertTrue(decision.isApproved());
    assertEquals(new BigDecimal(10000), decision.getLoanAmount());
    assertEquals(60, decision.getLoanPeriod());
  }

  @Test
  void handleDecision_true10000_60__SEGMENT_2_10000_60() {
    final SEGMENT segment = SEGMENT.SEGMENT_2;
    final BigDecimal amount = new BigDecimal(10000);
    final Integer period = 60;

    final LoanDecisionResponse decision = loanDecisionService.getDecision(segment, amount, period);
    assertTrue(decision.isApproved());
    assertEquals(new BigDecimal(10000), decision.getLoanAmount());
    assertEquals(60, decision.getLoanPeriod());
  }

  @Test
  void handleDecision_true10000_12__SEGMENT_3_2000_12() {
    final SEGMENT segment = SEGMENT.SEGMENT_3;
    final BigDecimal amount = new BigDecimal(2000);
    final Integer period = 12;

    final LoanDecisionResponse decision = loanDecisionService.getDecision(segment, amount, period);
    assertTrue(decision.isApproved());
    assertEquals(new BigDecimal(10000), decision.getLoanAmount());
    assertEquals(12, decision.getLoanPeriod());
  }

  @Test
  void handleDecision_true10000_12__SEGMENT_3_10000_12() {
    final SEGMENT segment = SEGMENT.SEGMENT_3;
    final BigDecimal amount = new BigDecimal(10000);
    final Integer period = 12;

    final LoanDecisionResponse decision = loanDecisionService.getDecision(segment, amount, period);
    assertTrue(decision.isApproved());
    assertEquals(new BigDecimal(10000), decision.getLoanAmount());
    assertEquals(12, decision.getLoanPeriod());
  }

  @Test
  void handleDecision_true10000_60__SEGMENT_3_2000_60() {
    final SEGMENT segment = SEGMENT.SEGMENT_3;
    final BigDecimal amount = new BigDecimal(2000);
    final Integer period = 60;

    final LoanDecisionResponse decision = loanDecisionService.getDecision(segment, amount, period);
    assertTrue(decision.isApproved());
    assertEquals(new BigDecimal(10000), decision.getLoanAmount());
    assertEquals(60, decision.getLoanPeriod());
  }

  @Test
  void handleDecision_true10000_60__SEGMENT_3_10000_60() {
    final SEGMENT segment = SEGMENT.SEGMENT_3;
    final BigDecimal amount = new BigDecimal(10000);
    final Integer period = 60;

    final LoanDecisionResponse decision = loanDecisionService.getDecision(segment, amount, period);
    assertTrue(decision.isApproved());
    assertEquals(new BigDecimal(10000), decision.getLoanAmount());
    assertEquals(60, decision.getLoanPeriod());
  }
}