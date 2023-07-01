package org.skypro.hw.service;

import org.skypro.hw.entity.Employee;
import org.skypro.hw.exception.EmployeeNotFoundException;
import org.springframework.stereotype.Service;

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

    public void testComparator() {
        List<Employee> employees = employeeService.getAll();
        System.out.println(employees);
        employees.sort((x, y) -> Double.compare(x.getSalary(), y.getSalary()));
        System.out.println(employees);
        employees.sort((x, y) -> -1 * x.getLastName().compareTo(y.getLastName()));
        System.out.println(employees);
    }
}
