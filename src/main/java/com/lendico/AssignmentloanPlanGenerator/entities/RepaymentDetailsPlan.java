package com.lendico.AssignmentloanPlanGenerator.entities;

import java.util.List;

/**
 * @author Mohamed M.Hassan
 */
public class RepaymentDetailsPlan {
    private List<RepaymentDetails> repaymentDetailsList;

    public List<RepaymentDetails> getRepaymentDetailsList() {
        return repaymentDetailsList;
    }

    public void setRepaymentDetailsList(List<RepaymentDetails> repaymentDetailsList) {
        this.repaymentDetailsList = repaymentDetailsList;
    }
}
