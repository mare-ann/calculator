package com.maryann.calculator.db.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This interface is used to work with table,
 * save data to table
 * and get data from logs table using pagination
 * @author Maria Gridneva
 * @version 1.0
 * @since 1.0
 */
@Repository
public interface JpaLogUtils extends PagingAndSortingRepository<JpaLog, Integer> {

    public Page<JpaLog> findAll(Pageable pageable);

    public List<JpaLog> findByExpression(String exp, Pageable pageable);

}
