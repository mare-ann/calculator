package com.maryann.calculator.webapp.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maryann.calculator.db.jdbc.JdbcLog;
import com.maryann.calculator.db.jdbc.JdbcLogsUtils;
import com.maryann.calculator.db.jpa.JpaLog;
import com.maryann.calculator.db.jpa.JpaLogUtils;
import com.maryann.calculator.services.ExpressionTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;

@RestController
@RequestMapping("/jpa/calculate")
public class JpaCalculateController {

    private static final Logger log = LoggerFactory.getLogger(JpaCalculateController.class);

    @Autowired
    private ExpressionTransformer trans;

    @Autowired
    private JpaLogUtils jpaLogUtils;


    @RequestMapping(method = RequestMethod.GET)
    public String home(@RequestParam String q) {
        System.out.println("CalculateController: Passing through..." + q);
        String prityResult = "";
        JpaLog jpaLog = new JpaLog();
        jpaLog.setExpression(q);
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
                jpaLog.setResult(prityResult);
                long timeEnd = System.currentTimeMillis();
                jpaLog.setCalculationTime((int)(timeEnd - timeStart));
                jpaLogUtils.save(jpaLog);
            }
        }
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String getAllWithJpa (@RequestParam (defaultValue = "0") Integer page, @RequestParam (defaultValue = "20") Integer size) throws JsonProcessingException {
        Pageable pageable = PageRequest.of(page, size, Sort.by("expression"));
        Page<JpaLog> returned = jpaLogUtils.findAll(pageable);

        ObjectMapper ob = new ObjectMapper();
        String asString = ob.writeValueAsString(returned);
        System.out.println(asString);
        return asString;
    }
}
