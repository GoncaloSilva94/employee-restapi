package org.example.domain.repository;

import org.example.domain.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface Employees extends JpaRepository<Employee, Integer> {
    List<Employee> findByNameStartingWith(String name);  //Search for employees with name starting with string "name"

}
