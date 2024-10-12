package com.bhawna.SpringBoot.Testing.TestingApplication.repositories;

import com.bhawna.SpringBoot.Testing.TestingApplication.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByEmail(String email);
}
