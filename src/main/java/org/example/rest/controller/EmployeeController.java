package org.example.rest.controller;

import org.example.domain.entity.Employee;
import org.example.rest.dto.EmployeeDTO;
import org.example.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private EmployeeService service;

    public EmployeeController(EmployeeService service){

        this.service = service;
    }

    @PostMapping
    @ResponseStatus(CREATED)   //Request http for create user
    public Employee save(@RequestBody EmployeeDTO dto){
        return service.saveEmployee(dto);


    }

    @GetMapping()
    @ResponseStatus(OK)    //Request http for get a list of all employees
    public List<Employee> getAllEmployee(){

        return service.findAllEmployee();
    }

    @GetMapping("{id}")
    @ResponseStatus(OK)    //Request http for finding employee by id
    public Employee find(@PathVariable Integer id){
        return service.findEmployee(id);
    }


    @PutMapping("{id}")
    @ResponseStatus(NO_CONTENT)   //Request http for update employee by id
    public void update(@PathVariable Integer id, @RequestBody EmployeeDTO dto){

        service.updateEmployee(id, dto);
    }


    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)      //Request http for delete employee by ud
    public void delete(@PathVariable Integer id){

        service.deleteEmployee(id);
    }

    @GetMapping("/search/{name}")
    @ResponseStatus(OK)              //Request http for finding all employees with name starting by String:"?"
    public List<Employee> search(@PathVariable String name){

        return service.searchName(name);

    }



}
