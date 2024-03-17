package ee.mikkelsaar.inbankapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import ee.mikkelsaar.inbankapi.controller.exception.ApiValidationErrors;
import ee.mikkelsaar.inbankapi.controller.request.LoanDecisionRequest;
import ee.mikkelsaar.inbankapi.controller.response.LoanDecisionResponse;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class LoanDecisionControllerIntTest extends AbstractIntTest{

  public static final String LOAN_DECISION_URL = "/loan-decision";
  @Autowired
  protected TestRestTemplate template;

  @Test
  void giveLoanDecision_BAD_REQUEST_personalCodeNULL() {
    final BigDecimal loanAmount = new BigDecimal(2000);
    final Integer loanPeriod = 60;
    final LoanDecisionRequest request = new LoanDecisionRequest(null, loanAmount, loanPeriod);

    final ResponseEntity<ApiValidationErrors> responseEntity = template.postForEntity(LOAN_DECISION_URL, request, ApiValidationErrors.class);

    assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    assertEquals(1, responseEntity.getBody().getErrors().size());
    assertEquals("Invalid peronal code.", responseEntity.getBody().getErrors().get(0));
  }

  @Test
  void giveLoanDecision_BAD_REQUEST_personalCodeEmpty() {
    final BigDecimal loanAmount = new BigDecimal(2000);
    final Integer loanPeriod = 60;
    final LoanDecisionRequest request = new LoanDecisionRequest("", loanAmount, loanPeriod);

    final ResponseEntity<ApiValidationErrors> responseEntity = template.postForEntity(LOAN_DECISION_URL, request, ApiValidationErrors.class);

    assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    assertEquals(1, responseEntity.getBody().getErrors().size());
    assertEquals("Invalid peronal code.", responseEntity.getBody().getErrors().get(0));
  }

  @Test
  void giveLoanDecision_BAD_REQUEST_personalCodeWhitespace() {
    final BigDecimal loanAmount = new BigDecimal(2000);
    final Integer loanPeriod = 60;
    final LoanDecisionRequest request = new LoanDecisionRequest(" ", loanAmount, loanPeriod);

    final ResponseEntity<ApiValidationErrors> responseEntity = template.postForEntity(LOAN_DECISION_URL, request, ApiValidationErrors.class);

    assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    assertEquals(1, responseEntity.getBody().getErrors().size());
    assertEquals("Invalid peronal code.", responseEntity.getBody().getErrors().get(0));
  }

  @Test
  void giveLoanDecision_BAD_REQUEST_personalCodeLengthShorter() {
    final BigDecimal loanAmount = new BigDecimal(2000);
    final Integer loanPeriod = 60;
    final LoanDecisionRequest request = new LoanDecisionRequest("0000000000", loanAmount, loanPeriod);

    final ResponseEntity<ApiValidationErrors> responseEntity = template.postForEntity(LOAN_DECISION_URL, request, ApiValidationErrors.class);

    assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    assertEquals(1, responseEntity.getBody().getErrors().size());
    assertEquals("Invalid peronal code.", responseEntity.getBody().getErrors().get(0));
  }

  @Test
  void giveLoanDecision_BAD_REQUEST_personalCodeLengthLonger() {
    final BigDecimal loanAmount = new BigDecimal(2000);
    final Integer loanPeriod = 60;
    final LoanDecisionRequest request = new LoanDecisionRequest("000000000000", loanAmount, loanPeriod);

    final ResponseEntity<ApiValidationErrors> responseEntity = template.postForEntity(LOAN_DECISION_URL, request, ApiValidationErrors.class);

    assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    assertEquals(1, responseEntity.getBody().getErrors().size());
    assertEquals("Invalid peronal code.", responseEntity.getBody().getErrors().get(0));
  }

  @Test
  void giveLoanDecision_BAD_REQUEST_personalCodeContainsLetters() {
    final BigDecimal loanAmount = new BigDecimal(2000);
    final Integer loanPeriod = 60;
    final LoanDecisionRequest request = new LoanDecisionRequest("000a0000000", loanAmount, loanPeriod);

    final ResponseEntity<ApiValidationErrors> responseEntity = template.postForEntity(LOAN_DECISION_URL, request, ApiValidationErrors.class);

    assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    assertEquals(1, responseEntity.getBody().getErrors().size());
    assertEquals("Invalid peronal code.", responseEntity.getBody().getErrors().get(0));
  }

  @Test
  void giveLoanDecision_BAD_REQUEST_amountNULL() {
    final BigDecimal loanAmount = null;
    final Integer loanPeriod = 60;
    final LoanDecisionRequest request = new LoanDecisionRequest("00000000000", loanAmount, loanPeriod);

    final ResponseEntity<ApiValidationErrors> responseEntity = template.postForEntity(LOAN_DECISION_URL, request, ApiValidationErrors.class);

    assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    assertEquals(1, responseEntity.getBody().getErrors().size());
    assertEquals("Loan amount is required.", responseEntity.getBody().getErrors().get(0));
  }

  @Test
  void giveLoanDecision_BAD_REQUEST_amountLessThanMIN() {
    final BigDecimal loanAmount = new BigDecimal(1999);
    final Integer loanPeriod = 60;
    final LoanDecisionRequest request = new LoanDecisionRequest("00000000000", loanAmount, loanPeriod);

    final ResponseEntity<ApiValidationErrors> responseEntity = template.postForEntity(LOAN_DECISION_URL, request, ApiValidationErrors.class);

    assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    assertEquals(1, responseEntity.getBody().getErrors().size());
    assertEquals("Loan amount must be greater or equal to 2000.", responseEntity.getBody().getErrors().get(0));
  }

  @Test
  void giveLoanDecision_BAD_REQUEST_amountGreaterThanMAX() {
    final BigDecimal loanAmount = new BigDecimal(10001);
    final Integer loanPeriod = 60;
    final LoanDecisionRequest request = new LoanDecisionRequest("00000000000", loanAmount, loanPeriod);

    final ResponseEntity<ApiValidationErrors> responseEntity = template.postForEntity(LOAN_DECISION_URL, request, ApiValidationErrors.class);

    assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    assertEquals(1, responseEntity.getBody().getErrors().size());
    assertEquals("Loan amount must be less or equal to 10000.", responseEntity.getBody().getErrors().get(0));
  }

  @Test
  void giveLoanDecision_BAD_REQUEST_periodNULL() {
    final BigDecimal loanAmount = new BigDecimal(2000);
    final Integer loanPeriod = null;
    final LoanDecisionRequest request = new LoanDecisionRequest("00000000000", loanAmount, loanPeriod);

    final ResponseEntity<ApiValidationErrors> responseEntity = template.postForEntity(LOAN_DECISION_URL, request, ApiValidationErrors.class);

    assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    assertEquals(1, responseEntity.getBody().getErrors().size());
    assertEquals("Loan period is required.", responseEntity.getBody().getErrors().get(0));
  }

  @Test
  void giveLoanDecision_BAD_REQUEST_periodLessThanMIN() {
    final BigDecimal loanAmount = new BigDecimal(2000);
    final Integer loanPeriod = 11;
    final LoanDecisionRequest request = new LoanDecisionRequest("00000000000", loanAmount, loanPeriod);

    final ResponseEntity<ApiValidationErrors> responseEntity = template.postForEntity(LOAN_DECISION_URL, request, ApiValidationErrors.class);

    assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    assertEquals(1, responseEntity.getBody().getErrors().size());
    assertEquals("Loan period must be greater or equal to 12.", responseEntity.getBody().getErrors().get(0));
  }

  @Test
  void giveLoanDecision_BAD_REQUEST_periodGreaterThanMAX() {
    final BigDecimal loanAmount = new BigDecimal(2000);
    final Integer loanPeriod = 61;
    final LoanDecisionRequest request = new LoanDecisionRequest("00000000000", loanAmount, loanPeriod);

    final ResponseEntity<ApiValidationErrors> responseEntity = template.postForEntity(LOAN_DECISION_URL, request, ApiValidationErrors.class);

    assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    assertEquals(1, responseEntity.getBody().getErrors().size());
    assertEquals("Loan period must be less or equal to 60.", responseEntity.getBody().getErrors().get(0));
  }

  @Test
  void giveLoanDecision_BAD_REQUEST_multipleErrors() {
    final BigDecimal loanAmount = null;
    final Integer loanPeriod = null;
    final LoanDecisionRequest request = new LoanDecisionRequest("00000000000", loanAmount, loanPeriod);

    final ResponseEntity<ApiValidationErrors> responseEntity = template.postForEntity(LOAN_DECISION_URL, request, ApiValidationErrors.class);

    assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    assertEquals(2, responseEntity.getBody().getErrors().size());
    assertTrue(responseEntity.getBody().getErrors().stream().anyMatch(e -> e.equals("Loan amount is required.")));
    assertTrue(responseEntity.getBody().getErrors().stream().anyMatch(e -> e.equals("Loan period is required.")));
  }

  @Test
  void giveLoanDecision_OK() {
    final BigDecimal loanAmount = new BigDecimal(10000);
    final Integer loanPeriod = 60;
    final LoanDecisionRequest request = new LoanDecisionRequest("49002010998", loanAmount, loanPeriod);

    final ResponseEntity<LoanDecisionResponse> responseEntity = template.postForEntity(LOAN_DECISION_URL, request, LoanDecisionResponse.class);

    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertTrue(responseEntity.getBody().isApproved());
    assertEquals(loanAmount, responseEntity.getBody().getLoanAmount());
    assertEquals(loanPeriod, responseEntity.getBody().getLoanPeriod());
  }
}
