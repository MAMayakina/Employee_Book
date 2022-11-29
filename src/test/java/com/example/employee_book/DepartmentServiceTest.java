package com.example.employee_book;

import com.example.employee_book.model.Employee;
import com.example.employee_book.service.DepartmentService;
import com.example.employee_book.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {
    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private DepartmentService departmentService;

    private List<Employee> actualEmployeeList;

    @BeforeEach
    public void setup() {
        Employee employee1 = new Employee("Егор", "Пешков", 1, 100000);
        Employee employee2 = new Employee("Марина", "Круглова", 2, 120000);
        Employee employee3 = new Employee("Андрей", "Веселов", 1, 150000);
        actualEmployeeList = new ArrayList<>();
        actualEmployeeList.add(employee1);
        actualEmployeeList.add(employee2);
        actualEmployeeList.add(employee3);

        when(employeeService.getEmployees()).thenReturn(actualEmployeeList);
    }

    @Test
    public void checkEmployeesFromDepartment() {
        final int departmentId = 1;
        final List<Employee> actual = actualEmployeeList.stream()
                .filter(employee -> employee.getDepartment() == departmentId)
                .collect(Collectors.toList());
        final List<Employee> expected = departmentService.getEmployeesFromDepartment(departmentId);
        assertEquals(expected, actual);
    }

    @Test
    public void checkSumSalaryOfDepartment() {
        final int departmentId = 1;
        int actual = 0;
        for (Employee employee : actualEmployeeList) {
            if (employee.getDepartment() == departmentId) {
                actual = actual + employee.getSalary();
            }
        }
        final int expected = departmentService.getSumSalaryOfDepartment(departmentId);
        assertEquals(expected, actual);
    }

    @Test
    public void checkMaxSalaryOfDepartment() {
        final int departmentId = 1;
        int actual = 0;
        for (Employee employee : actualEmployeeList) {
            if (employee.getDepartment() == departmentId && employee.getSalary() > actual) {
                actual = employee.getSalary();
            }
        }
        final int expected = departmentService.getMaxSalaryOfDepartment(departmentId);
        assertEquals(expected, actual);
    }

    @Test
    public void checkMinSalaryOfDepartment() {
        final int departmentId = 1;
        int actual = 0;
        for (Employee employee : actualEmployeeList) {
            if (employee.getDepartment() == departmentId && employee.getSalary() < actual) {
                actual = employee.getSalary();
            }
        }
        final int expected = departmentService.getMaxSalaryOfDepartment(departmentId);
        assertEquals(expected, actual);
    }

    @Test
    public void checkEmployeesByDepartment() {
        final Map<Integer, List<Employee>> actual = new HashMap<>();
        for (Employee employee : actualEmployeeList) {
            if (!actual.containsKey(employee.getDepartment())) {
                actual.put(employee.getDepartment(), new ArrayList<>(List.of(employee)));
            } else{
                actual.get(employee.getDepartment()).add(employee);
            }
        }
        final Map<Integer, List<Employee>> expected = departmentService.getEmployeesByDepartment();
        assertEquals(expected, actual);

    }
}
