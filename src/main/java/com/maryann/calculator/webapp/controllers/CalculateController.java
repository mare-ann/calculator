package com.maryann.calculator.webapp.controllers;

import com.maryann.calculator.services.ExpressionTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

@RestController
@RequestMapping("/calculate")
public class CalculateController {

    private static final Logger log = LoggerFactory.getLogger(CalculateController.class);

    @RequestMapping(method = RequestMethod.GET)
    public String home(@RequestParam String q) {
        System.out.println("CalculateController: Passing through..." + q);
        if(q == null || q.isBlank()) {
            return "";
        } else {
            try {
                ExpressionTransformer trans = new ExpressionTransformer();
                double res = trans.transformIntoNumber(q);
                DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols();
                otherSymbols.setDecimalSeparator('.');
                otherSymbols.setGroupingSeparator(' ');
                String prityResult = new DecimalFormat("###,###.####", otherSymbols).format(res);
                return prityResult;
            } catch (Exception | StackOverflowError e) {
                log.error("Failed to calculate expression ", e);
                return "Error in expression";
            }
        }
    }
}
