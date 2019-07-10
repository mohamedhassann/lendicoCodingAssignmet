package com.lendico.AssignmentloanPlanGenerator.entities;


import java.time.LocalDate;

/**
 * @author Mohamed M.Hassan
 *
 * This class represent the details of repayment that should done every month
 * i.e The attributes of the generated plan
 */
public class RepaymentDetails {
    // The naming of the following attributes based on the sent PDF of the task

    private double borrowerPaymentAmount; // annuity
    private double initialOutstandingPrincipal;
    private double remainingOustandingPrincipal;
    private double interest;
    private double principal;
    private LocalDate date;

    public double getBorrowerPaymentAmount() {
        return borrowerPaymentAmount;
    }

    public void setBorrowerPaymentAmount(double borrowerPaymentAmount) {
        this.borrowerPaymentAmount = borrowerPaymentAmount;
    }

    public double getInitialOutstandingPrincipal() {
        return initialOutstandingPrincipal;
    }

    public void setInitialOutstandingPrincipal(double initialOutstandingPrincipal) {
        this.initialOutstandingPrincipal = initialOutstandingPrincipal;
    }

    public double getRemainingOustandingPrincipal() {
        return remainingOustandingPrincipal;
    }

    public void setRemainingOustandingPrincipal(double remainingOustandingPrincipal) {
        this.remainingOustandingPrincipal = remainingOustandingPrincipal;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public double getPrincipal() {
        return principal;
    }

    public void setPrincipal(double principal) {
        this.principal = principal;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
