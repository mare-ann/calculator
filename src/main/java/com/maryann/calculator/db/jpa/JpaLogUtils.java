package com.maryann.calculator.db.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaLogUtils extends PagingAndSortingRepository<JpaLog, Integer> {

    public Page<JpaLog> findAll(Pageable pageable);

    public List<JpaLog> findByExpression(String exp, Pageable pageable);

}
