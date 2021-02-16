package org.example.domain.repository;

import org.example.domain.entity.Employee;
import org.example.domain.entity.Status;
import org.example.domain.entity.Positions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EmployeeDataTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private Employees employees;

    @Before
    public void setUp(){
        Status status = new Status(1,"description");
        Positions position= new Positions(1,"description");
        Employee employee = new Employee();
        employee.setId(1);
        employee.setName("name");
        employee.setBirthdate(LocalDate.now());
        employee.setAddress("address");
        employee.setStatus(status);
        employee.setPositions(position);
        employee.setCreated(LocalDateTime.now());
        employee.setUpdated(LocalDateTime.now());

        entityManager.persist(employee);
        entityManager.flush();

    }

    @Test
    public void findByNameTest(){
        List<Employee> employeeList = employees.findByNameStartingWith("n");

        assertEquals(1,employeeList.size());
    }
}
