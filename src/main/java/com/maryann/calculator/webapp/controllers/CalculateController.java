package com.maryann.calculator.webapp.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maryann.calculator.db.jdbc.DBLogsUtils;
import com.maryann.calculator.db.jdbc.Log;
import com.maryann.calculator.services.ExpressionTransformer;
import org.checkerframework.checker.units.qual.Acceleration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;

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

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String getAll () throws JsonProcessingException {
        List<Log> returned = dbLogsUtils.getAll();
        ObjectMapper ob = new ObjectMapper();
        String asString = ob.writeValueAsString(returned);
        System.out.println(asString);
        return asString;
    }
}
