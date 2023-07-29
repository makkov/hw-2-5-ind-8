package org.skypro.hw.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.hw.entity.Employee;
import org.skypro.hw.exception.EmployeeNotFoundException;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceTest {

    @InjectMocks
    private DepartmentService departmentService;

    @Mock
    private EmployeeService employeeService;

    @Test
    void withMaxSalary_success() {
        //Подготовка входных данных
        int departmentId1 = 1;

        String firstName1 = "Ivan";
        String lastName1 = "Ivanov";
        double salary1 = 88;

        Employee employee1 = new Employee(firstName1, lastName1, departmentId1, salary1);

        String firstName2 = "Petr";
        String lastName2 = "Petrov";
        double salary2 = 999;

        Employee employee2 = new Employee(firstName2, lastName2, departmentId1, salary2);

        String firstName3 = "Petr";
        String lastName3 = "Petrov";
        double salary3 = Double.MAX_VALUE;

        int departmentId30 = 30;
        Employee employee3 = new Employee(firstName3, lastName3, departmentId30, salary3);

        //Подготовка ожидаемого результата
        when(employeeService.getAll()).thenReturn(Arrays.asList(employee1, employee2, employee3));
        double expectedMaxSalary = Math.max(salary1, salary2);

        //Начало теста
        double actualMaxSalary = departmentService.getMaxSalaryByDepId(departmentId1);
        assertEquals(expectedMaxSalary, actualMaxSalary);
        assertNotEquals(departmentId1, departmentId30);
        verify(employeeService).getAll();
        verifyNoMoreInteractions(employeeService);
    }

    @Test
    void withMaxSalary_withEmployeeNotFoundException() {
        //Подготовка входных данных
        int departmentId = 1;

        //Подготовка ожидаемого результата
        when(employeeService.getAll()).thenReturn(Collections.emptyList());
        String expectedErrorMessage = "Сотрудник не найден";

        //Начало теста
        Exception exception = assertThrows(EmployeeNotFoundException.class, () -> {
            departmentService.withMaxSalary(departmentId);
        });
        assertEquals(expectedErrorMessage, exception.getMessage());
    }

    @Test
    void withMinSalary() {
    }

    @Test
    void employeesByDepartment_AllEmployeesSuccess() {
        //Подготовка входных данных
        int departmentId1 = 1;

        String firstName1 = "Ivan";
        String lastName1 = "Ivanov";
        double salary1 = 88;

        Employee employee1 = new Employee(firstName1, lastName1, departmentId1, salary1);

        String firstName2 = "Petr";
        String lastName2 = "Petrov";
        double salary2 = 999;

        Employee employee2 = new Employee(firstName2, lastName2, departmentId1, salary2);

        String firstName3 = "Petr";
        String lastName3 = "Petrov";
        double salary3 = Double.MAX_VALUE;

        int departmentId30 = 30;
        Employee employee3 = new Employee(firstName3, lastName3, departmentId30, salary3);

        //Подготовка ожидаемого результата
        when(employeeService.getAll()).thenReturn(Arrays.asList(employee1, employee2, employee3));
        Map<Integer, List<Employee>> expectedEmployeesByDepId = new HashMap<>();
        expectedEmployeesByDepId.put(departmentId1, Arrays.asList(employee1, employee2));
        expectedEmployeesByDepId.put(departmentId30, Arrays.asList(employee3));

        //Начало теста
        Map<Integer, List<Employee>> actualEmployeesByDepId = departmentService.employeesByDepartment(null);
        assertEquals(expectedEmployeesByDepId, actualEmployeesByDepId);
        verify(employeeService).getAll();
    }

    @Test
    void employeesByDepartment_byDepIdSuccess() {
        //Подготовка входных данных
        int departmentId1 = 1;

        String firstName1 = "Ivan";
        String lastName1 = "Ivanov";
        double salary1 = 88;

        Employee employee1 = new Employee(firstName1, lastName1, departmentId1, salary1);

        String firstName2 = "Petr";
        String lastName2 = "Petrov";
        double salary2 = 999;

        Employee employee2 = new Employee(firstName2, lastName2, departmentId1, salary2);

        String firstName3 = "Petr";
        String lastName3 = "Petrov";
        double salary3 = Double.MAX_VALUE;

        int departmentId30 = 30;
        Employee employee3 = new Employee(firstName3, lastName3, departmentId30, salary3);

        //Подготовка ожидаемого результата
        when(employeeService.getAll()).thenReturn(Arrays.asList(employee1, employee2, employee3));
        Map<Integer, List<Employee>> expectedEmployeesByDepId = new HashMap<>();
        expectedEmployeesByDepId.put(departmentId1, Arrays.asList(employee1, employee2));

        //Начало теста
        Map<Integer, List<Employee>> actualEmployeesByDepId = departmentService.employeesByDepartment(departmentId1);
        assertEquals(expectedEmployeesByDepId, actualEmployeesByDepId);
        verify(employeeService).getAll();
    }

    @Test
    void getEmployeesByDepId() {
    }

    @Test
    void getSalarySumByDepId() {
    }

    @Test
    void getMaxSalaryByDepId() {
    }

    @Test
    void getMinSalaryByDepId() {
    }
}