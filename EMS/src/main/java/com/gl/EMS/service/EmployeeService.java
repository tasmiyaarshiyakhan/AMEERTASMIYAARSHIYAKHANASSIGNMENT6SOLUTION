package com.gl.EMS.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.gl.EMS.model.Employee;

public interface EmployeeService {
   Employee saveEmployee(Employee employee);
   List<Employee> getAllEmployees();
   Employee getEmployeeById(long id);   
   Employee updateEmployee(Employee employee, long id);
   Void deleteEmployee(long id);
   Page<Employee> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
   }

