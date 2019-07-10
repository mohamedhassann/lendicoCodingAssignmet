package com.lendico.AssignmentloanPlanGenerator.controllers;

import com.lendico.AssignmentloanPlanGenerator.entities.RepaymentDTO;
import com.lendico.AssignmentloanPlanGenerator.entities.RepaymentInputs;
import com.lendico.AssignmentloanPlanGenerator.service.RepaymentPlanGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Mohamed M.Hassann
 */
@RestController
@RequestMapping("/generate-plan")
public class RepaymentController {
    @Autowired
    RepaymentPlanGeneratorService repaymentPlanGeneratorService;
    public void generateLoanPlan(RepaymentDTO repaymentDTO){
        RepaymentInputs repaymentInputs = null;
        repaymentInputs = new RepaymentInputs(repaymentDTO);

    }
}
