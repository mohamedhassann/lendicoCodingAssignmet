package com.lendico.AssignmentloanPlanGenerator.controllers;

import com.lendico.AssignmentloanPlanGenerator.entities.RepaymentDTO;
import com.lendico.AssignmentloanPlanGenerator.entities.RepaymentDetailsPlan;
import com.lendico.AssignmentloanPlanGenerator.entities.RepaymentInputs;
import com.lendico.AssignmentloanPlanGenerator.exceptions.RepaymentPlanGeneratorException;
import com.lendico.AssignmentloanPlanGenerator.service.RepaymentPlanGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.time.format.DateTimeParseException;

/**
 * @author Mohamed M.Hassann
 */
@RestController
@RequestMapping("/generate-plan")
public class RepaymentController {
    @Autowired
    RepaymentPlanGeneratorService repaymentPlanGeneratorService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity generateLoanPlan(@RequestBody RepaymentDTO repaymentDTO) {
        try{
            RepaymentInputs repaymentInputs = null;
            repaymentInputs = new RepaymentInputs(repaymentDTO);
            RepaymentDetailsPlan repaymentDetailsPlan = repaymentPlanGeneratorService.generateRepaymentPlan(repaymentInputs);
            return ResponseEntity.ok(repaymentDetailsPlan);
        }catch(NumberFormatException | DateTimeParseException | RepaymentPlanGeneratorException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }

    }
}
