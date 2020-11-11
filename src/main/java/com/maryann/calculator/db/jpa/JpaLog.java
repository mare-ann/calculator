package com.maryann.calculator.db.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "logs")
public class JpaLog {

    @Id
    @Column(name = "id")
    private Integer id ;

    @Column(name = "expression")
    private String expression;

    @Column(name = "result")
    private  String result;

    @Column(name = "calculation_time")
    private Integer calculationTime;

    @Column(name = "created_at")
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
