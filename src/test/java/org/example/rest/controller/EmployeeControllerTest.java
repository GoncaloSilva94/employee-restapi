package org.example.rest.controller;


import org.example.domain.entity.Employee;
import org.example.domain.entity.Positions;
import org.example.domain.entity.Status;
import org.example.rest.dto.EmployeeDTO;
import org.example.service.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;




@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @Test
    public void listAllEmployeesShouldReturnOkTest() throws Exception {
        Status status = new Status(1, "description");
        Positions positions = new Positions(1, "description");
        Employee employee = new Employee();
        employee.setId(1);
        employee.setName("name");
        employee.setBirthdate(LocalDate.now());
        employee.setAddress("address");
        employee.setStatus(status);
        employee.setPositions(positions);
        employee.setCreated(LocalDateTime.now());
        employee.setUpdated(LocalDateTime.now());
        List<Employee> allEmployees = new ArrayList<>();
        allEmployees.add(employee);
        given(employeeService.findAllEmployee()).willReturn(allEmployees);

        mockMvc.perform(get("/employee").
                contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void deleteEmployeeShouldReturnNoContentTest() throws Exception{
        Status status = new Status(1, "description");
        Positions positions = new Positions(1, "description");
        Employee employee = new Employee();
        employee.setId(1);
        employee.setName("name");
        employee.setBirthdate(LocalDate.now());
        employee.setAddress("address");
        employee.setStatus(status);
        employee.setPositions(positions);
        employee.setCreated(LocalDateTime.now());
        employee.setUpdated(LocalDateTime.now());
        doNothing().when(employeeService).deleteEmployee(employee.getId());
        mockMvc.perform(delete("/employee/1" + employee.getId().toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }


    @Test
    public void getEmployeeByIdShouldReturnOkTest() throws Exception {
        Status status = new Status(1, "description");
        Positions positions = new Positions(1, "description");
        Employee employee = new Employee();
        employee.setId(1);
        employee.setName("name");
        employee.setBirthdate(LocalDate.now());
        employee.setAddress("address");
        employee.setStatus(status);
        employee.setPositions(positions);
        employee.setCreated(LocalDateTime.now());
        employee.setUpdated(LocalDateTime.now());
        given(employeeService.findEmployee(employee.getId())).willReturn(employee);
        mockMvc.perform(get("/employee/" + employee.getId().toString()).
                contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        }

        //Check PUT response
        @Test
        public void updateEmployeeShouldReturnNoContentTest() throws Exception{
            Status status = new Status(1, "description");
            Positions positions = new Positions(1, "description");
            Employee employee = new Employee();
            employee.setId(1);
            employee.setName("name");
            employee.setBirthdate(LocalDate.now());
            employee.setAddress("address");
            employee.setStatus(status);
            employee.setPositions(positions);
            employee.setCreated(LocalDateTime.now());


            employee.setUpdated(LocalDateTime.now());
            EmployeeDTO employeeDTO = new EmployeeDTO();
            employeeDTO.setName("name1");
            employeeDTO.setBirthdate(LocalDate.now());
            employeeDTO.setAddress("address1");
            employeeDTO.setStatus(status.getId());
            employeeDTO.setPosition(positions.getId());


            String dto= "{\n" +
                    "  \"name\" : \"" + employeeDTO.getName() + "\",\n" +
                    "  \"birthdate\" : \"" + employeeDTO.getBirthdate() + "\",\n" +
                    "  \"address\" : \"" + employeeDTO.getAddress() + "\",\n" +
                    "  \"status\" :" + employeeDTO.getStatus() + ",\n" +
                    "  \"position\" :" + employeeDTO.getPosition() + "\n" +
                    "}";

            given(employeeService.updateEmployee(employee.getId(),employeeDTO)).willReturn(employee);
            mockMvc.perform(put("/employee/" + employee.getId().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(dto))
                    .andExpect(status().isNoContent());

        }

        @Test
        public void createEmployeeShouldReturnCreatedTest() throws Exception{
            Status status = new Status(1, "description");
            Positions positions = new Positions(1, "description");

            Employee employee = new Employee();
            employee.setId(1);
            employee.setName("name");
            employee.setBirthdate(LocalDate.now());
            employee.setAddress("address");
            employee.setStatus(status);
            employee.setPositions(positions);
            employee.setCreated(LocalDateTime.now());

            EmployeeDTO employeeDTO = new EmployeeDTO();
            employeeDTO.setName("name");
            employeeDTO.setBirthdate(LocalDate.now());
            employeeDTO.setAddress("address");
            employeeDTO.setStatus(status.getId());
            employeeDTO.setPosition(positions.getId());

            String dto= "{\n" +
                    "  \"name\" : \"" + employeeDTO.getName() + "\",\n" +
                    "  \"birthdate\" : \"" + employeeDTO.getBirthdate() + "\",\n" +
                    "  \"address\" : \"" + employeeDTO.getAddress() + "\",\n" +
                    "  \"status\" :" + employeeDTO.getStatus() + ",\n" +
                    "  \"position\" :" + employeeDTO.getPosition() + "\n" +
                    "}";

            given(employeeService.saveEmployee(employeeDTO)).willReturn(employee);
            mockMvc.perform(post("/employee")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(dto))
                    .andExpect(status().isCreated());


        }


}
