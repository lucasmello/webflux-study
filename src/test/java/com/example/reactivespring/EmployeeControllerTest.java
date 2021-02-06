package com.example.reactivespring;

import com.example.reactivespring.api.EmployeeController;
import com.example.reactivespring.domain.Employee;
import com.example.reactivespring.service.EmployeeService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    public void createValidProfile() {
//        Employee employee = new Employee("myId", "mock@testmock.com");


    }

}
