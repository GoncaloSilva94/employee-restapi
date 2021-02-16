package org.example.service;

import org.example.domain.entity.Employee;
import org.example.rest.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {

    Employee saveEmployee(EmployeeDTO dto);

    List<Employee> findAllEmployee();

    void deleteEmployee(Integer id);

    Employee findEmployee(Integer id);

    Employee updateEmployee(Integer id, EmployeeDTO dto);

    List<Employee> searchName(String name);
}
