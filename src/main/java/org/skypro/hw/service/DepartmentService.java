package org.skypro.hw.service;

import org.skypro.hw.entity.Employee;
import org.skypro.hw.exception.EmployeeNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class DepartmentService {

    private final EmployeeService employeeService;

    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public Employee withMaxSalary(int departmentId) {
        return streamByDepartment(departmentId)
                .max(Comparator.comparing(Employee::getSalary))
                .orElseThrow(() -> new EmployeeNotFoundException("Сотрудник не найден"));
    }

    public Employee withMinSalary(int departmentId) {
        return streamByDepartment(departmentId)
                .min(Comparator.comparing(Employee::getSalary))
                .orElseThrow(() -> new EmployeeNotFoundException("Сотрудник не найден"));
    }

    public Map<Integer, List<Employee>> employeesByDepartment(Integer departmentId) {
        return streamByDepartment(departmentId)
                .collect(Collectors.groupingBy(Employee::getDepartmentId, Collectors.toList()));
    }

    private Stream<Employee> streamByDepartment(Integer departmentId) {
        List<Employee> employees = employeeService.getAll();
        return employees.stream()
                .filter(e -> departmentId == null || e.getDepartmentId().equals(departmentId));
    }

    @GetMapping(path = "{id}/employees")
    public List<Employee> getEmployeesByDepId(int id) {
        return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartmentId() == id)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "{id}/salary/sum")
    public double getSalarySumByDepId(int id) {
        return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartmentId() == id)
                .mapToDouble(Employee::getSalary)
                .sum();
    }

    @GetMapping(path = "{id}/salary/max")
    public double getMaxSalaryByDepId(int id) {
        return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartmentId() == id)
                .mapToDouble(Employee::getSalary)
                .max()
                .getAsDouble();
    }

    @GetMapping(path = "{id}/salary/min")
    public double getMinSalaryByDepId(int id) {
        return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartmentId() == id)
                .mapToDouble(Employee::getSalary)
                .min()
                .getAsDouble();
    }
}
