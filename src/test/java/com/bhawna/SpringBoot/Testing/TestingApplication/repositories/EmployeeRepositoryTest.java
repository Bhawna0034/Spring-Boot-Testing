package com.bhawna.SpringBoot.Testing.TestingApplication.repositories;

import com.bhawna.SpringBoot.Testing.TestingApplication.TestContainerConfiguration;
import com.bhawna.SpringBoot.Testing.TestingApplication.entities.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Import(TestContainerConfiguration.class)
@DataJpaTest
//@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    private Employee employee;

    @BeforeEach
    void setUp(){
        employee = Employee.builder()
                .id(1L)
                .name("Bhawna")
                .email("bhawna@gmail.com")
                .salary(100L)
                .build();
    }
    @Test
    void testFindByEmail_whenEmailIsPresent_thenReturnEmployee() {

        // Arrange, Given
        employeeRepository.save(employee);

        // Act, When
        List<Employee> employeeList = employeeRepository.findByEmail(employee.getEmail());

        // Assert, Then
        assertThat(employeeList).isNotNull();
        assertThat(employeeList).isNotEmpty();
        assertThat(employeeList.get(0).getEmail()).isEqualTo(employee.getEmail());
    }

    @Test
    void testFindByEmail_whenEmailIsNotFound_thenReturnEmptyEmployee(){

        // Given
        String email = "sleeping@gmail.com";

        // When
        List<Employee> employeeList = employeeRepository.findByEmail(email);

        // Then
        assertThat(employeeList).isNotNull();
        assertThat(employeeList).isEmpty();

    }
}