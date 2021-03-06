package com.maryann.calculator.webapp.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maryann.calculator.db.jdbc.JdbcLog;
import com.maryann.calculator.db.jdbc.JdbcLogsUtils;
import com.maryann.calculator.services.ExpressionTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;


/**
 * Rest controller with Jdbc usage
 * @author Maria Gridneva
 * @version 1.0
 * @since 1.0
 */

@RestController
@RequestMapping("/jdbc/calculate")
public class JdbcCalculateController {

    private static final Logger log = LoggerFactory.getLogger(JdbcCalculateController.class);

    @Autowired
    private JdbcLogsUtils dbLogsUtils;

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
    public String getAllWithJdbc () throws JsonProcessingException {
        List<JdbcLog> returned = dbLogsUtils.getAll();
        ObjectMapper ob = new ObjectMapper();
        String asString = ob.writeValueAsString(returned);
        System.out.println(asString);
        return asString;
    }

}
