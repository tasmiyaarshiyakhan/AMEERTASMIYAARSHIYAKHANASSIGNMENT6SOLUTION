package com.gl.EMS.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.gl.EMS.Repository.EmployeeRep;
import com.gl.EMS.exception.ResNFExc;
import com.gl.EMS.model.Employee;
import com.gl.EMS.service.EmployeeService;

import net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRep employeeRep;

	public EmployeeServiceImpl(EmployeeRep employeeRep) {
		super();
		this.employeeRep = employeeRep;
	}

	@Override
	public Employee saveEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return employeeRep.save(employee);
	}

	@Override
	public List<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
		return employeeRep.findAll();
	}

	@Override
	public Employee getEmployeeById(long id) {

		return employeeRep.findById(id).orElseThrow(() -> new ResNFExc("Employee", "Id", id));
	}

	@Override
	public Employee updateEmployee(Employee employee, long id) {
		// TODO Auto-generated method stub
		// we need to check whether employee with given id is exsisting in the db or not

		Employee existingEmployee = employeeRep.findById(id)
				.orElseThrow(() -> new ResNFExc("Employee", "Id", id));

		existingEmployee.setFirstName(employee.getFirstName());
		existingEmployee.setLastName(employee.getLastName());
		existingEmployee.setEmail(employee.getEmail());

		// save existing employee to db
		employeeRep.save(existingEmployee);
		return null;
	}

	@Override
	public Void deleteEmployee(long id) {
		// TODO Auto-generated method stub
		// check whether a employee exists in a db or not

		employeeRep.findById(id).orElseThrow(() -> new ResNFExc("Employee", "Id", id));

		employeeRep.deleteById(id);
		return null;

	}

	@Override
	public Page<Employee> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
