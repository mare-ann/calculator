package com.maryann.calculator.webapp.controllers;

import com.maryann.calculator.db.jdbc.DBLogsUtils;
import com.maryann.calculator.services.ExpressionTransformer;
import org.checkerframework.checker.units.qual.Acceleration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

@RestController
@RequestMapping("/calculate")
public class CalculateController {

    private static final Logger log = LoggerFactory.getLogger(CalculateController.class);

    @Autowired
    private DBLogsUtils dbLogsUtils;

    @Autowired
    private ExpressionTransformer trans;

    @RequestMapping(method = RequestMethod.GET)
    public String home(@RequestParam String q) {
        System.out.println("CalculateController: Passing through..." + q);
        String prityResult = "";
        if(q == null || q.isBlank()) {
            return "";
        } else {
            long timeStart = System.currentTimeMillis();
            try {
                double res = trans.transformIntoNumber(q);
                DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols();
                otherSymbols.setDecimalSeparator('.');
                otherSymbols.setGroupingSeparator(' ');
                prityResult = new DecimalFormat("###,###.####", otherSymbols).format(res);
                log.info("Result = "  + prityResult);
                return prityResult;
            } catch (Exception | StackOverflowError e) {
                log.error("Failed to calculate expression ", e);
                prityResult = "Error in expression";
                return prityResult;
            } finally {
                long timeEnd = System.currentTimeMillis();
                dbLogsUtils.saveExpression(q, prityResult, (timeEnd - timeStart));
            }
        }
    }
}
