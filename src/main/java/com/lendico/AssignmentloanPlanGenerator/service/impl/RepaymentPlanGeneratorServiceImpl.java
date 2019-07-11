package com.lendico.AssignmentloanPlanGenerator.service.impl;

import com.lendico.AssignmentloanPlanGenerator.entities.RepaymentDetails;
import com.lendico.AssignmentloanPlanGenerator.entities.RepaymentDetailsPlan;
import com.lendico.AssignmentloanPlanGenerator.entities.RepaymentInputs;
import com.lendico.AssignmentloanPlanGenerator.exceptions.RepaymentPlanGeneratorException;
import com.lendico.AssignmentloanPlanGenerator.service.RepaymentPlanGeneratorService;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDate;

@Service
public class RepaymentPlanGeneratorServiceImpl implements RepaymentPlanGeneratorService {
    DecimalFormat df;
    @Override
    public RepaymentDetailsPlan generateRepaymentPlan(RepaymentInputs repaymentInputs) {
        double loanAmount = repaymentInputs.getLoanAmount();
        double nominalRate = repaymentInputs.getNominalRate();
        int duration = repaymentInputs.getDuration();
        LocalDate startDate = repaymentInputs.getStartDate().toLocalDate();

        if(loanAmount <= 0 || nominalRate <= 0 || duration <= 0){
            throw new RepaymentPlanGeneratorException("Input values for loan cannot be negative");
        }
        double annuity = getAnnuity(loanAmount, nominalRate, duration);
        RepaymentDetailsPlan repaymentDetailsPlan = new RepaymentDetailsPlan();
        for(int i = 0; i < duration; i++){

        }
        return repaymentDetailsPlan;
    }


    private double getAnnuity(double loanAmount, double monthlyRate, long duration) {
        return (monthlyRate * loanAmount) / (1 - Math.pow((1 + monthlyRate), -1 * duration));
    }
}
