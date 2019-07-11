package com.lendico.AssignmentloanPlanGenerator.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mohamed M.Hassan
 */
public class RepaymentDetailsPlan {
    private List<RepaymentDetails> plans;

    public RepaymentDetailsPlan() {
        this.plans = new ArrayList<>();
    }

    public List<RepaymentDetails> getRepaymentDetailsList() {
        return plans;
    }

    public void setRepaymentDetailsList(List<RepaymentDetails> repaymentDetailsList) {
        this.plans = repaymentDetailsList;
    }

    public void addPlans(RepaymentDetails repaymentDetails) {
        this.plans.add(repaymentDetails);
    }
}
