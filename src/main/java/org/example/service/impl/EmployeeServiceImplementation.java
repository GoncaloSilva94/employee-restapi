package org.example.service.impl;


import lombok.RequiredArgsConstructor;
import org.example.domain.entity.Employee;
import org.example.domain.entity.Positions;
import org.example.domain.entity.Status;
import org.example.domain.repository.Employees;
import org.example.domain.repository.PositionsRep;
import org.example.domain.repository.StatusRep;
import org.example.exception.ServiceException;
import org.example.rest.dto.EmployeeDTO;
import org.example.service.EmployeeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImplementation implements EmployeeService {

    private final Employees repository;
    private final StatusRep statusRepository;
    private final PositionsRep positionRepository;

    @Override
    @Transactional
    public Employee saveEmployee(EmployeeDTO dto) {

        Integer id_status = dto.getStatus();
        Integer id_position = dto.getPosition();
        Status status = statusRepository.findById(id_status).orElseThrow(() -> new ServiceException("Status not found"));   //Cant find status with id passed by dto
        Positions positions = positionRepository.findById(id_position).orElseThrow(() -> new ServiceException("Position not found")); //Cant find position with id passed by dto

        Employee employee = new Employee();
        employee.setName(dto.getName());
        employee.setBirthdate(dto.getBirthdate());
        employee.setAddress(dto.getAddress());
        employee.setStatus(status);
        employee.setPositions(positions);
        employee.setCreated(LocalDateTime.now());
        employee.setUpdated(LocalDateTime.now());

        repository.save(employee);


        return employee;

    }
    @Override
    public List<Employee> findAllEmployee(){
        return repository.findAll();

    }

    @Override
    public void deleteEmployee(Integer id) {

        repository.findById(id).map( employee -> {
            repository.delete(employee);
            return employee; })
                .orElseThrow(() -> new ServiceException("Employee not found"));

    }

    @Override
    @Transactional
    public Employee updateEmployee(Integer id, EmployeeDTO dto) {

        return repository.findById(id).
                map(employee -> {
                    employee.setName(dto.getName());
                    employee.setBirthdate(dto.getBirthdate());
                    employee.setAddress(dto.getAddress());
                    employee.setStatus(statusRepository.findById(dto.getStatus()).orElseThrow(() -> new ServiceException("Status not found")));
                    employee.setPositions(positionRepository.findById(dto.getPosition()).orElseThrow(() -> new ServiceException("Position not found")));
                    employee.setUpdated(LocalDateTime.now());
                    return repository.save(employee);
                }).orElseThrow(() -> new ServiceException("Employee not found"));


    }

    @Override
    public Employee findEmployee(Integer id) {
        return repository.findById(id).orElseThrow(() -> new ServiceException("Employee not found"));
    }

    @Override
    public List<Employee> searchName(String name) {
        return repository.findByNameStartingWith(name);
    }
}
