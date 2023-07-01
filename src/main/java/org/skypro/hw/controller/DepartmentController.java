package org.skypro.hw.controller;

import org.skypro.hw.entity.Employee;
import org.skypro.hw.service.DepartmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/max-salary")
    public Employee withMaxSalary(@RequestParam int departmentId) {
        return departmentService.withMaxSalary(departmentId);
    }

    @GetMapping("/min-salary")
    public Employee withMinSalary(@RequestParam int departmentId) {
        return departmentService.withMinSalary(departmentId);
    }

    @GetMapping("/all")
    public Map<Integer, List<Employee>> employeesByDepartment(@RequestParam(required = false) Integer departmentId) {
        return departmentService.employeesByDepartment(departmentId);
    }

    @GetMapping("/test")
    public void test() {
        departmentService.testComparator();
    }
}
