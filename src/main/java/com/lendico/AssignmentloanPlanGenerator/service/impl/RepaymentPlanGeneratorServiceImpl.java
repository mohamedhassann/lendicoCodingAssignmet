package com.lendico.AssignmentloanPlanGenerator.service.impl;

import com.lendico.AssignmentloanPlanGenerator.entities.RepaymentDetails;
import com.lendico.AssignmentloanPlanGenerator.entities.RepaymentDetailsPlan;
import com.lendico.AssignmentloanPlanGenerator.entities.RepaymentInputs;
import com.lendico.AssignmentloanPlanGenerator.exceptions.RepaymentPlanGeneratorException;
import com.lendico.AssignmentloanPlanGenerator.service.RepaymentPlanGeneratorService;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.stream.IntStream;

@Service
public class RepaymentPlanGeneratorServiceImpl implements RepaymentPlanGeneratorService {

    private static DecimalFormat df = new DecimalFormat("0.00");

    @Override
    public RepaymentDetailsPlan generateRepaymentPlan(RepaymentInputs repaymentInputs) {

        double loanAmount = repaymentInputs.getLoanAmount();
        double nominalRate = repaymentInputs.getNominalRate();
        int duration = repaymentInputs.getDuration();
        final double[] initialOutstandingPrincipal = {loanAmount};
        LocalDateTime startDate = repaymentInputs.getStartDate();

        if (loanAmount <= 0 || nominalRate <= 0 || duration <= 0) {
            throw new RepaymentPlanGeneratorException("Input values for loan cannot be negative");
        }
        final double[] accumaltedInterest = {0};
        final double[] totalAmountPaid = {0};

        RepaymentDetailsPlan repaymentDetailsPlan = new RepaymentDetailsPlan();

        final RepaymentDetails[] repaymentDetails = {null};

//        for (int i = 0; i < duration; i++) {
//
//            repaymentDetails = generateMonthlyRepaymentPlan(loanAmount, nominalRate, duration, startDate, initialOutstandingPrincipal, i);
//            initialOutstandingPrincipal = repaymentDetails.getRemainingOustandingPrincipal();
//            repaymentDetailsPlan.addPlans(repaymentDetails);
//
//            accumaltedInterest = accumaltedInterest + repaymentDetails.getInterest();
//            totalAmountPaid = loanAmount + accumaltedInterest;
//
//
//            /*Calculation of annuity in case of the last month*/
//            if (i == duration - 1) {
//                double lastAnnuity = Double.parseDouble(df.format(totalAmountPaid - (repaymentDetails.getBorrowerPaymentAmount() * (duration - 1))));
//
//                repaymentDetails.setBorrowerPaymentAmount(lastAnnuity);
//                repaymentDetails.setPrincipal(repaymentDetails.getBorrowerPaymentAmount() - repaymentDetails.getInterest());
//            }
//        }

        IntStream.range(0, duration).forEach(
             i -> {
                 repaymentDetails[0] = generateMonthlyRepaymentPlan(loanAmount, nominalRate, duration, startDate, initialOutstandingPrincipal[0], i);
                 initialOutstandingPrincipal[0] = repaymentDetails[0].getRemainingOustandingPrincipal();
                 repaymentDetailsPlan.addPlans(repaymentDetails[0]);

                 accumaltedInterest[0] = accumaltedInterest[0] + repaymentDetails[0].getInterest();
                 totalAmountPaid[0] = loanAmount + accumaltedInterest[0];


                 /*Calculation of annuity in case of the last month*/
                 if (i == duration - 1) {
                     double lastAnnuity = Double.parseDouble(df.format(totalAmountPaid[0] - (repaymentDetails[0].getBorrowerPaymentAmount() * (duration - 1))));

                     repaymentDetails[0].setBorrowerPaymentAmount(lastAnnuity);
                     repaymentDetails[0].setPrincipal(repaymentDetails[0].getBorrowerPaymentAmount() - repaymentDetails[0].getInterest());
                 }
             }

        );
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
        nominalRate = Double.parseDouble(df.format(nominalRate / 100));
        double nominalRateByMonth = nominalRate / 12;
        return Double.parseDouble(df.format((loanAmount * nominalRateByMonth) / (1 - Math.pow(1 + nominalRateByMonth, -duration))));
    }

    private double getRemainingOutstandingPrincipal(double initialOutstandingPrincipal, double principal) {
        double remainingOutstandingPrincipal = Double.parseDouble(df.format(initialOutstandingPrincipal - principal));
        if (remainingOutstandingPrincipal < 0) {
            remainingOutstandingPrincipal = 0;
        }
        return remainingOutstandingPrincipal;
    }

    private double getPrincipal(double interest, double annuity) {
        return Double.parseDouble(df.format(annuity - interest));
    }

    private double getInterest(double nominalRate, double initialOutstandingPrincipal) {
        return Double.parseDouble(df.format((nominalRate * 30 * initialOutstandingPrincipal / 360) / 100));
    }
}
