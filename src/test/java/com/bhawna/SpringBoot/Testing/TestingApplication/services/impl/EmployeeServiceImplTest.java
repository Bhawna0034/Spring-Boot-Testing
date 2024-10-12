package com.bhawna.SpringBoot.Testing.TestingApplication.services.impl;

import com.bhawna.SpringBoot.Testing.TestingApplication.TestContainerConfiguration;
import com.bhawna.SpringBoot.Testing.TestingApplication.dtos.EmployeeDto;
import com.bhawna.SpringBoot.Testing.TestingApplication.entities.Employee;
import com.bhawna.SpringBoot.Testing.TestingApplication.repositories.EmployeeRepository;
import com.bhawna.SpringBoot.Testing.TestingApplication.services.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.configuration.IMockitoConfiguration;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(TestContainerConfiguration.class)
@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {


    @Mock
    private EmployeeRepository employeeRepository;

    @Spy
    private ModelMapper modelMapper;

    @InjectMocks
    private  EmployeeServiceImpl employeeService;

    private Employee mockEmployee;
    private EmployeeDto mockEmployeeDto;

    @BeforeEach
    void setUp(){
        mockEmployee = Employee.builder()
                .id(1L)
                .email("bhawna@gmail.com")
                .name("Bhawna")
                .salary(200L)
                .build();

        mockEmployeeDto = modelMapper.map(mockEmployee, EmployeeDto.class);
    }

    @Test
    void testGetEmployeeById_WhenEmployeeIdIsPresent_ThenReturnEmployeeDto() {

        Long id = mockEmployee.getId();

        //assign
        when(employeeRepository.findById(id)).thenReturn(Optional.of(mockEmployee)); // stubbing

        // act
        EmployeeDto employeeDto = employeeService.getEmployeeById(id);

        // assert
        assertThat(employeeDto).isNotNull();
        assertThat(employeeDto.getId()).isEqualTo(id);
        assertThat(employeeDto.getEmail()).isEqualTo(mockEmployee.getEmail());
        verify(employeeRepository, atMost(1)).findById(id);
    }

    @Test
    void testCreateNewEmployee_WhenValidEmployee_ThenCreateNewEmployee(){
        // assign
        when(employeeRepository.findByEmail(anyString())).thenReturn(List.of());
        when(employeeRepository.save(any(Employee.class))).thenReturn(mockEmployee);

        // act

        EmployeeDto employeeDto = employeeService.createNewEmployee(mockEmployeeDto);


        // assert
        assertThat(employeeDto).isNotNull();
        assertThat(employeeDto.getEmail()).isEqualTo(mockEmployeeDto.getEmail());

        ArgumentCaptor<Employee> employeeArgumentCaptor = ArgumentCaptor.forClass(Employee.class);
        verify(employeeRepository).save(employeeArgumentCaptor.capture());

        Employee capturedEmployee = employeeArgumentCaptor.capture();
        assertThat(capturedEmployee.getEmail()).isEqualTo(mockEmployee.getEmail());
    }
}