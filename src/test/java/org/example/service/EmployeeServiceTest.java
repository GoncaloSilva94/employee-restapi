package org.example.service;

import org.example.domain.entity.Employee;
import org.example.domain.entity.Status;
import org.example.domain.entity.Positions;
import org.example.domain.repository.Employees;
import org.example.domain.repository.PositionsRep;
import org.example.domain.repository.StatusRep;
import static org.junit.Assert.*;
import org.example.exception.ServiceException;
import org.example.rest.dto.EmployeeDTO;
import org.example.service.impl.EmployeeServiceImplementation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTest {

    @Mock
    private Employees employees;
    @Mock
    private StatusRep statusRepository;
    @Mock
    private PositionsRep positionsRep;


    @InjectMocks
    private EmployeeServiceImplementation employeeService;


    @Test
    public void listAllEmployeesTest() {
        Status status = new Status(1, "description");
        Positions positions = new Positions(1, "description");
        List<Employee> listEmployees = new ArrayList();
        listEmployees.add(new Employee(1, "Goncalo", LocalDate.now(), "address", status, positions, LocalDateTime.now(), LocalDateTime.now()));
        given(employees.findAll()).willReturn(listEmployees);
        List<Employee> test = employeeService.findAllEmployee();
        assertEquals(listEmployees, test);
        verify(employees).findAll();

    }

    @Test
    public void createEmployeeTest(){
        Status status = new Status(1,"description");
        Positions positions = new Positions(1,"description");
        EmployeeDTO newEmployeeDTO = new EmployeeDTO("Goncalo", LocalDate.now(), "address", status.getId(), positions.getId());
        given(statusRepository.findById(newEmployeeDTO.getStatus())).willReturn(Optional.of(status));
        given(positionsRep.findById(newEmployeeDTO.getPosition())).willReturn(Optional.of(positions));
        Employee employee = employeeService.saveEmployee(newEmployeeDTO);
        assertNotNull(employee);
        verify(employees).save(employee);


    }

    @Test
    public void updateEmployeeTest(){
        Status status = new Status(1,"description");
        Positions positions = new Positions(1,"description");
        Employee employee = new Employee(1,"Goncalo", LocalDate.now(),"address",status,positions, LocalDateTime.now(),LocalDateTime.now());
        EmployeeDTO newEmployeeDTO = new EmployeeDTO("Goncalo", LocalDate.now(), "address", status.getId(), positions.getId());
        given(employees.findById(employee.getId())).willReturn(Optional.of(employee));
        given(statusRepository.findById(newEmployeeDTO.getStatus())).willReturn(Optional.of(status));
        given(positionsRep.findById(newEmployeeDTO.getPosition())).willReturn(Optional.of(positions));
        employee.setName(newEmployeeDTO.getName());
        employee.setBirthdate(newEmployeeDTO.getBirthdate());
        employee.setAddress(newEmployeeDTO.getAddress());
        employee.setStatus(status);
        employee.setPositions(positions);
        assertEquals(employee.getName(), newEmployeeDTO.getName());


    }

    @Test(expected = RuntimeException.class)
    public void updateEmployeeExceptionTest(){

        Status status = new Status(1,"description");
        Positions positions = new Positions(1,"description");
        Employee employee = new Employee(1,"Goncalo", LocalDate.now(),"address",status,positions, LocalDateTime.now(),LocalDateTime.now());
        EmployeeDTO newEmployeeDTO = new EmployeeDTO("Goncalo", LocalDate.now(), "address", status.getId(), positions.getId());
        given(employees.findById(anyInt())).willReturn(Optional.ofNullable(null));
        employeeService.updateEmployee(employee.getId(), newEmployeeDTO);


    }

    @Test
    public void deleteEmployeeByIdTest(){
        Status status = new Status(1,"description");
        Positions positions = new Positions(1,"description");
        Employee employee = new Employee(1,"Goncalo", LocalDate.now(),"address",status,positions, LocalDateTime.now(),LocalDateTime.now());
        when(employees.findById(employee.getId())).thenReturn(Optional.of(employee));
        employeeService.deleteEmployee(employee.getId());
        verify(employees).delete(employee);
    }

    @Test(expected = RuntimeException.class)
    public void deleteEmployeeByIdExceptionTest() {
        Status status = new Status(1,"description");
        Positions positions = new Positions(1,"description");
        Employee employee = new Employee(90,"Goncalo", LocalDate.now(),"address",status,positions, LocalDateTime.now(),LocalDateTime.now());
        given(employees.findById(anyInt())).willReturn(Optional.ofNullable(null));
        employeeService.deleteEmployee(employee.getId());
    }

    @Test
    public void getEmployeeByIdTest(){
        Status status = new Status(1,"description");
        Positions positions = new Positions(1,"description");
        Employee employee = new Employee(1,"Goncalo", LocalDate.now(),"address",status,positions, LocalDateTime.now(),LocalDateTime.now());
        when(employees.findById(employee.getId())).thenReturn(Optional.of(employee));
        Employee expected = employeeService.findEmployee(employee.getId());
        assertEquals(employee, expected);
        verify(employees).findById(employee.getId());
    }

    @Test(expected = ServiceException.class)
    public void ReturnEmployeeByIdException() throws ServiceException{
        Status status = new Status(1,"description");
        Positions positions = new Positions(1,"description");
        Employee employee = new Employee(89,"Goncalo", LocalDate.now(),"address",status,positions, LocalDateTime.now(),LocalDateTime.now());
        given(employees.findById(anyInt())).willReturn(Optional.ofNullable(null));
        employeeService.findEmployee(employee.getId());
    }



}