package com.lendico.AssignmentloanPlanGenerator.service;

import com.lendico.AssignmentloanPlanGenerator.entities.RepaymentDTO;
import com.lendico.AssignmentloanPlanGenerator.entities.RepaymentDetails;
import com.lendico.AssignmentloanPlanGenerator.entities.RepaymentDetailsPlan;
import com.lendico.AssignmentloanPlanGenerator.entities.RepaymentInputs;

import java.time.LocalDateTime;

public interface RepaymentPlanGeneratorService {
    RepaymentDetailsPlan generateRepaymentPlan(RepaymentInputs repaymentInputs);
    RepaymentDetails generateMonthlyRepaymentPlan(double loanAmount, double nominalRate, int duration,
                                          LocalDateTime startDate, double initialOutstandingPrincipal, int monthsAfterStart);

}
