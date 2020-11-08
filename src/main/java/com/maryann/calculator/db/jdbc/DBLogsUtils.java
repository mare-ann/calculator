package com.maryann.calculator.db.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class DBLogsUtils {
    private static final Logger log = LoggerFactory.getLogger(DBLogsUtils.class);

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String user;

    @Value("${spring.datasource.password}")
    private String password;

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

    public void getLogs() {
        String query = "Select * from `calculator`.`logs`;";

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
                int count = rs.getInt(1);
                System.out.println("Total number of books in the table : " + count);
            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //close connection ,stmt and resultset here
            try { con.close(); } catch(Exception se) { /*can't do anything */ }
            try { stmt.close(); } catch(Exception se) { /*can't do anything */ }
            try { rs.close(); } catch(Exception se) { /*can't do anything */ }
        }
    }
}
