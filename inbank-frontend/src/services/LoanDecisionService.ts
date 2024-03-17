import http from "@/http-common";

class LoanDecisionService {
  giveLoanDecision(personalCode: string, loanAmount: Number, loanPeriod: Number): Promise<any> {
    return http.post("/loan-decision", {
      personalCode: personalCode,
      loanAmount: loanAmount,
      loanPeriod: loanPeriod
    });
  }
}

export default new LoanDecisionService();