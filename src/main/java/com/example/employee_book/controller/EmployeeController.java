package com.example.employee_book.controller;

import com.example.employee_book.model.Employee;
import com.example.employee_book.record.EmployeeRequest;
import com.example.employee_book.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Set;

@RestController
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public Collection<Employee> getAllEmployees() {
        return this.employeeService.getEmployees();
    }

    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody EmployeeRequest employeeRequest){
        return this.employeeService.addEmployee(employeeRequest);
    }

    @GetMapping("/employees/salary/sum")
    public int getSumSalary() {
        return this.employeeService.getSumSalary();
    }

    @GetMapping("/employees/salary/min")
    public Employee getEmployeeWithMinSalary() {
        return this.employeeService.getEmployeeWithMinSalary();
    }

    @GetMapping("/employees/salary/max")
    public Employee getEmployeeWithMaxSalary() {
        return this.employeeService.getEmployeeWithMaxSalary();
    }

    @GetMapping("/employees/salary/average")
    public Set<Employee> getEmployeesWithSalaryMoreAverage() {
        return this.employeeService.getEmployeesWithSalaryMoreAverage();
    }
}
