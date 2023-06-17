package org.skypro.hw.service;

import org.skypro.hw.entity.Employee;
import org.skypro.hw.exception.EmployeeAlreadyAddedException;
import org.skypro.hw.exception.EmployeeNotFoundException;
import org.skypro.hw.exception.EmployeeStorageIsFullException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmployeeService {

    private final Map<String, Employee> employeeByFullName = new HashMap<>();
    private final int MAX_SIZE = 2;

    public Employee add(String firstName, String lastName) {
        if (employeeByFullName.size() >= MAX_SIZE) {
            throw new EmployeeStorageIsFullException("Массив сотрудников переполнен");
        }

        Employee newEmployee = new Employee(firstName, lastName);

        String fullName = getFullName(newEmployee);

        if (employeeByFullName.containsKey(fullName)) {
            throw new EmployeeAlreadyAddedException("Сотрудник " + newEmployee + " уже существует");
        }

        employeeByFullName.put(fullName, newEmployee);
        return newEmployee;
    }

    public Employee find(String firstName, String lastName) {
        Employee employeeForFind = new Employee(firstName, lastName);

        String fullName = getFullName(employeeForFind);

        checkExistence(employeeForFind);

        return employeeByFullName.get(fullName);
    }

    public Employee remove(String firstName, String lastName) {
        Employee employeeForRemove = new Employee(firstName, lastName);
        String fullName = getFullName(employeeForRemove);

        checkExistence(employeeForRemove);

        employeeByFullName.remove(fullName);
        return employeeForRemove;
    }

    public Collection<Employee> getAll() {
        return employeeByFullName.values();
    }

    private void checkExistence(Employee employee) {
        if (!employeeByFullName.containsKey(getFullName(employee))) {
            throw new EmployeeNotFoundException("Такого сотрудника нет");
        }
    }

    private String getFullName(Employee employee) {
        return employee.getFirstName() + employee.getLastName();
    }
}
