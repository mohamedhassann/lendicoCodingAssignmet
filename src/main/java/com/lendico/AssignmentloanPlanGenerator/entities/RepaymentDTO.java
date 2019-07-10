package com.lendico.AssignmentloanPlanGenerator.entities;

import java.util.Date;

/**
 * @author Mohamed M.Hassan
 */
public class RepaymentDTO {
    private String loanAmount;
    private String nominalRate;
    private int duration;
    private String startDate;

    public RepaymentDTO(String loanAmount, String nominalRate,
                        int duration, String startDate) {
        this.loanAmount = loanAmount;
        this.nominalRate = nominalRate;
        this.duration = duration;
        this.startDate = startDate;
    }

    public String getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(String loanAmount) {
        this.loanAmount = loanAmount;
    }

    public String getNominalRate() {
        return nominalRate;
    }

    public void setNominalRate(String nominalRate) {
        this.nominalRate = nominalRate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
}
