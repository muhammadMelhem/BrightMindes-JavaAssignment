package com.brightMinds.javaTest.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.brightMinds.javaTest.model.Statement;

@Repository
public interface StatmentRepository extends CrudRepository<Statement, Long> {

	List<Statement> findByAccountId(Long accountId);

	List<Statement> findAll();

}
