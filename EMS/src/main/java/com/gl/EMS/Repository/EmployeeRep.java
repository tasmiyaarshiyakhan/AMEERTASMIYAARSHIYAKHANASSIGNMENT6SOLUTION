package com.gl.EMS.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gl.EMS.model.Employee;


public interface EmployeeRep extends JpaRepository<Employee, Long> {

}
