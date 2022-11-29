package com.example.employee_book.controller;

import com.example.employee_book.model.Employee;
import com.example.employee_book.service.DepartmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/department/{id}/employees")
    public Collection<Employee> getEmployeesFromDepartment(@PathVariable("id") int departmentId){
        return departmentService.getEmployeesFromDepartment(departmentId);
    }

    @GetMapping("/department/{id}/salary/sum")
    public int getSumSalaryOfDepartment(@PathVariable("id") int departmentId){
        return departmentService.getSumSalaryOfDepartment(departmentId);
    }

    @GetMapping("/department/{id}/salary/max")
    public int getMaxSalaryOfDepartment(@PathVariable("id") int departmentId){
        return departmentService.getMaxSalaryOfDepartment(departmentId);
    }

    @GetMapping("/department/{id}/salary/min")
    public int getMinSalaryOfDepartment(@PathVariable("id") int departmentId){
        return departmentService.getMinSalaryOfDepartment(departmentId);
    }

    @GetMapping("/department/employees")
    public Map<Integer, List<Employee>> getEmployeesByDepartment(){
        return departmentService.getEmployeesByDepartment();
    }


}
