package com.maryann.calculator.db.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class allows to save and get records from logs table
 * @author Maria Gridneva
 * @version 1.0
 * @since 1.0
 */
@Component
public class JdbcLogsUtils {
    private static final Logger log = LoggerFactory.getLogger(JdbcLogsUtils.class);

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String user;

    @Value("${spring.datasource.password}")
    private String password;

    /**
     * Method save record (expression, result, and calculation time) in logs table
     * @param exp - input expression
     * @param res - calculated result as a string
     * @param procTime - how much time it took to calculate expression
     */
    public void saveExpression(String  exp, String res, long procTime) {
        String query = "INSERT INTO `calculator`.`logs` (`expression`, `result`, `calculation_time`) " +
                "VALUES ('%s', '%s', '%s');";
        query = String.format(query, exp, res, procTime);

        Connection con = null;
        Statement stmt = null;
        try {
            // opening database connection to MySQL server
            con = DriverManager.getConnection(url, user, password);

            // getting Statement object to execute query
            stmt = con.createStatement();

            // executing SELECT query
            int num = stmt.executeUpdate(query);
            log.info(num + " Record(s) saved in DB");
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //close connection ,stmt and resultset here
            try { con.close(); } catch(Exception se) { /*can't do anything */ }
            try { stmt.close(); } catch(Exception se) { /*can't do anything */ }
        }
    }

    /**
     * Get all records from logs table
     * @return List of all records
     */
    public List<JdbcLog> getAll() {
        String query = "Select * from `calculator`.`logs`;";
        List<JdbcLog> logTable = new ArrayList<>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            // opening database connection to MySQL server
            con = DriverManager.getConnection(url, user, password);

            // getting Statement object to execute query
            stmt = con.createStatement();

            // executing SELECT query
            rs = stmt.executeQuery(query);

            while (rs.next()) {
//                System.out.println(rs.getInt("id") + "|" + rs.getString("expression")
//                + "|" + rs.getString("result"));
                JdbcLog log = new JdbcLog();
                log.setId(rs.getInt("id"));
                log.setExpression(rs.getString("expression"));
                log.setResult(rs.getString("result"));
                log.setCalculationTime(rs.getInt("calculation_time"));
                logTable.add(log);
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //close connection ,stmt and result set here
            try { con.close(); } catch(Exception se) { /*can't do anything */ }
            try { stmt.close(); } catch(Exception se) { /*can't do anything */ }
            try { rs.close(); } catch(Exception se) { /*can't do anything */ }
        }
        return logTable;
    }
}
