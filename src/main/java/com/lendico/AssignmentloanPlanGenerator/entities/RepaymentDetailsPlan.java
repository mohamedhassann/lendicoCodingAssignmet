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

    public List<RepaymentDetails> getPlans() {
        return plans;
    }

    public void setPlans(List<RepaymentDetails> plans) {
        this.plans = plans;
    }

    public void addPlans(RepaymentDetails repaymentDetails) {
        this.plans.add(repaymentDetails);
    }
}
