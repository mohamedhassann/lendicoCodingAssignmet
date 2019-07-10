package com.lendico.AssignmentloanPlanGenerator.service;

import com.lendico.AssignmentloanPlanGenerator.entities.RepaymentDTO;
import com.lendico.AssignmentloanPlanGenerator.entities.RepaymentDetailsPlan;
import com.lendico.AssignmentloanPlanGenerator.entities.RepaymentInputs;

public interface RepaymentPlanGeneratorService {
    RepaymentDetailsPlan generateRepaymentPlan(RepaymentInputs repaymentInputs);
}
