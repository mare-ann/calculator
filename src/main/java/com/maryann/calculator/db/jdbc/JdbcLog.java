package com.maryann.calculator.db.jdbc;

import java.util.Date;
//class to present one row from logs table

/**
 * This class to present one row from logs table for Jdbc uses
 * @author Maria Gridneva
 * @version 1.0
 * @since 1.0
 */
public class JdbcLog {
    private Integer id ;
    private String expression;
    private  String result;
    private Integer calculationTime;
    private Date createdAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Integer getCalculationTime() {
        return calculationTime;
    }

    public void setCalculationTime(Integer calculationTime) {
        this.calculationTime = calculationTime;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Log{" +
                "id=" + id +
                ", expression='" + expression + '\'' +
                ", result='" + result + '\'' +
                ", calculationTime=" + calculationTime +
                '}';
    }
}
