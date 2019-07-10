package com.lendico.AssignmentloanPlanGenerator.entities;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;

public class RepaymentInputs {
    private double loanAmount;
    private double nominalRate;
    private int duration;
    private LocalDateTime startDate;

    public RepaymentInputs(RepaymentDTO repaymentDTO) {
        this.loanAmount = Double.parseDouble(repaymentDTO.getLoanAmount());
        this.nominalRate = Double.parseDouble(repaymentDTO.getNominalRate());
        this.duration = repaymentDTO.getDuration();
        this.startDate = ZonedDateTime.parse(repaymentDTO.getStartDate()).toLocalDateTime();
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public double getNominalRate() {
        return nominalRate;
    }

    public void setNominalRate(double nominalRate) {
        this.nominalRate = nominalRate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }
}
