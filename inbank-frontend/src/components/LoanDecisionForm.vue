<template>
  <v-sheet rounded width="900" class="mx-auto">
    <v-container>
      <v-row class="flex-child text-subtitle-2">
        <v-col class="d-flex">
          <v-card theme="dark" class="pa-4 mx-auto" width="400">
            <v-form class="submit-form" @submit.prevent>
              <v-alert
                  closable
                  title="Error"
                  :text="alertMessage"
                  type="error"
                  v-model="alert"
              ></v-alert>
              <div>
                <v-text-field
                    v-model="query.personalCode"
                    label="Personal code"
                    :rules="personalCodeRules"
                ></v-text-field>
                <v-text-field
                    v-model="query.loanAmount"
                    label="Loan amount"
                    :rules="loanAmountRules"
                ></v-text-field>
                <v-text-field
                    v-model="query.loanPeriod"
                    label="Loan period"
                    :rules="loanPeriodRules"
                ></v-text-field>
                <v-btn class="mt-2"
                       type="submit"
                       block
                       @click="submitQuery"
                       color="red-accent-2">Query
                </v-btn>
              </div>
            </v-form>
          </v-card>
        </v-col>
        <v-col class="d-flex" v-if="submitted">
          <v-card theme="dark" title="Result:" width="400" class="pa-4 mx-auto">
            <v-text-field
                v-model="result.approved"
                readonly
                label="Eligible for loan"
            ></v-text-field>
            <v-text-field
                v-model="result.loanAmount"
                readonly
                label="Loan amount"
            ></v-text-field>
            <v-text-field
                v-model="result.loanPeriod"
                readonly
                label="Loan period"
            ></v-text-field>
          </v-card>
        </v-col>
      </v-row>
    </v-container>
  </v-sheet>

</template>

<script lang="ts">
import { defineComponent } from "vue";
import LoanDecisionService from "@/services/LoanDecisionService";
import type LoanDecisionRequest from "@/types/LoanDecisionRequest";
import type LoanDecisionResponse from "@/types/LoanDecisionResponse";


export default defineComponent({
  name: "loan-decision-form",
  data() {
    return {
      query: {
        personalCode: "",
        loanAmount: 2000,
        loanPeriod: 12,
      } as LoanDecisionRequest,
      result: {
        approved: false,
        loanAmount: 0,
        loanPeriod: 0
      } as LoanDecisionResponse,
      submitted: false,
      alert: false,
      alertMessage: "",
      personalCodeRules: [
        (v: any) => !!v || 'Personal code is required',
        (v: any) => (v && /^[0-9]+$/.test(v)) || 'Personal code must be a number',
        (v: any) => (v && v.length == 11) || 'Personal code must be 11 characters',
      ],
      loanAmountRules: [
        (v: any) => !!v || 'Loan amount is required',
        (v: any) => (v && /^[0-9]+$/.test(v)) || 'Loan amount must be a number',
        (v: any) => (v && /^[0-9]+$/.test(v)) && Number(v) >= 2000 || 'Loan amount must be greater or equal to 2000.',
        (v: any) => (v && /^[0-9]+$/.test(v)) && Number(v) <= 10000 || 'Loan amount must be less or equal to 10000.',
      ],
      loanPeriodRules: [
        (v: any) => !!v || 'Loan period is required',
        (v: any) => (v && /^[0-9]+$/.test(v)) || 'Loan period must be a number',
        (v: any) => (v && /^[0-9]+$/.test(v)) && Number(v) >= 12 || 'Loan period must be greater or equal to 12.',
        (v: any) => (v && /^[0-9]+$/.test(v)) && Number(v) <= 60 || 'Loan period must be less or equal to 60.',
      ],
    };
  },
  methods: {
    submitQuery() {
      this.alert = false;
      LoanDecisionService.giveLoanDecision(this.query.personalCode, this.query.loanAmount, this.query.loanPeriod)
      .then((response) => {
        this.result = response.data;
        this.submitted = true;
      })
      .catch((e) => {
        this.submitted = false;
        this.result = {
          approved: false,
          loanAmount: 0,
          loanPeriod: 0
        } as LoanDecisionResponse;
        if (e.response.data?.errors) {
          this.alertMessage = `${e.response.data.errors.join("\n")}`;
        } else {
          this.alertMessage = "An error occurred while giving loan decision, we are working on fixing it. Please try again later.";
        }
        this.alert = true;
      });
    },
  },
});
</script>