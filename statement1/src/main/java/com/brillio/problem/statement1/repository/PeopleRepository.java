package com.brillio.problem.statement1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brillio.problem.statement1.model.People;


public interface PeopleRepository extends JpaRepository<People,Long>{
}
