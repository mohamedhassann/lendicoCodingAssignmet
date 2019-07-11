package com.lendico.AssignmentloanPlanGenerator.service.impl;

import com.lendico.AssignmentloanPlanGenerator.entities.RepaymentDetails;
import com.lendico.AssignmentloanPlanGenerator.entities.RepaymentDetailsPlan;
import com.lendico.AssignmentloanPlanGenerator.entities.RepaymentInputs;
import com.lendico.AssignmentloanPlanGenerator.exceptions.RepaymentPlanGeneratorException;
import com.lendico.AssignmentloanPlanGenerator.service.RepaymentPlanGeneratorService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class RepaymentPlanGeneratorServiceImpl implements RepaymentPlanGeneratorService {

    private static DecimalFormat df = new DecimalFormat("0.00");

    @Override
    public RepaymentDetailsPlan generateRepaymentPlan(RepaymentInputs repaymentInputs) {

        double loanAmount = repaymentInputs.getLoanAmount();
        double nominalRate = repaymentInputs.getNominalRate();
        int duration = repaymentInputs.getDuration();
        double initialOutstandingPrincipal = loanAmount;
        LocalDateTime startDate = repaymentInputs.getStartDate();

        if(loanAmount <= 0 || nominalRate <= 0 || duration <= 0){
            throw new RepaymentPlanGeneratorException("Input values for loan cannot be negative");
        }
        double accumaltedInterest = 0;
        double totalAmountPaid = 0;
        /* Annuity. */
        double annuity = getAnnuity(loanAmount, nominalRate, duration);
        RepaymentDetailsPlan repaymentDetailsPlan = new RepaymentDetailsPlan();

        RepaymentDetails repaymentDetails = null;

        for(int i = 0; i < duration; i++){

            repaymentDetails = generateMonthlyRepaymentPlan(loanAmount, nominalRate, duration, startDate, initialOutstandingPrincipal, i);
            initialOutstandingPrincipal = repaymentDetails.getRemainingOustandingPrincipal();
            repaymentDetailsPlan.addPlans(repaymentDetails);

            accumaltedInterest = accumaltedInterest + repaymentDetails.getInterest();
            totalAmountPaid = loanAmount + accumaltedInterest;


            /*Calculation of annuity in case of the last month*/
            if(i == duration -1){
                double lastAnnuity = Double.parseDouble(df.format(totalAmountPaid - (repaymentDetails.getBorrowerPaymentAmount() * (duration - 1))));

                repaymentDetails.setBorrowerPaymentAmount(lastAnnuity);
                repaymentDetails.setPrincipal(repaymentDetails.getBorrowerPaymentAmount() - repaymentDetails.getInterest());
            }
        }
        //repaymentDetailsPlan.addPlans(repaymentDetails);
        return repaymentDetailsPlan;
    }

    @Override
    public RepaymentDetails generateMonthlyRepaymentPlan(double loanAmount, double nominalRate, int duration, LocalDateTime startDate, double initialOutstandingPrincipal, int monthsAfterStart) {
        RepaymentDetails monthlyRepaymentDetails = new RepaymentDetails();

        /* Date calculation */
        LocalDateTime date = startDate.plusMonths(monthsAfterStart);
        monthlyRepaymentDetails.setDate(date);

        /* Interest calculation*/
        double interest = getInterest(nominalRate, initialOutstandingPrincipal);
        monthlyRepaymentDetails.setInterest(interest);

        /* Annuity calculation */
        double annuity = getAnnuity(loanAmount, nominalRate, duration);
        monthlyRepaymentDetails.setBorrowerPaymentAmount(annuity);

        /* Principal calculation*/
        double principal = getPrincipal(interest, annuity);
        monthlyRepaymentDetails.setPrincipal(principal);

        monthlyRepaymentDetails.setInitialOutstandingPrincipal(initialOutstandingPrincipal);

        /* Remaing oustanding principal calculation*/
        double remainingOutstandingPrincipal = getRemainingOutstandingPrincipal(initialOutstandingPrincipal, principal);
        monthlyRepaymentDetails.setRemainingOustandingPrincipal(remainingOutstandingPrincipal);

        return monthlyRepaymentDetails;
    }


    private double getAnnuity(double loanAmount, double nominalRate, int duration) {
        nominalRate = Double.parseDouble(df.format(nominalRate /100));
        double nominalRateByMonth = nominalRate / 12;
        return Double.parseDouble(df.format((loanAmount * nominalRateByMonth) / (1 - Math.pow(1 + nominalRateByMonth,-duration))));
    }
    private double getRemainingOutstandingPrincipal(double initialOutstandingPrincipal, double principal){
        double remainingOutstandingPrincipal = Double.parseDouble(df.format(initialOutstandingPrincipal - principal));
        if(remainingOutstandingPrincipal < 0){
            remainingOutstandingPrincipal = 0;
        }
        return remainingOutstandingPrincipal;
    }
    private double getPrincipal(double interest, double annuity){
        return Double.parseDouble(df.format(annuity - interest));
    }
    private double getInterest(double nominalRate, double initialOutstandingPrincipal){
        return Double.parseDouble(df.format((nominalRate * 30 * initialOutstandingPrincipal / 360)  / 100));
    }
}
