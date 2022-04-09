package com.gl.EMS.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gl.EMS.model.Employee;
import com.gl.EMS.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmpContr {

	private EmployeeService employeeservice;

	public EmpContr(EmployeeService employeeservice) {
		super();
		this.employeeservice = employeeservice;
	}

	// build create employee RESTAPI
	@PostMapping()
	public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
		return new ResponseEntity<Employee>(employeeservice.saveEmployee(employee), HttpStatus.CREATED);
	}

	// build get all employee RESTAPI
   @GetMapping
	public List<Employee> getAllEmployees() {
		return employeeservice.getAllEmployees();
	}
   
    //build get employeeby Id RESTAPI
   //http://localhost:8080/api/employees/1
   @GetMapping("{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long employeeId){
    	return new ResponseEntity <Employee>(employeeservice.getEmployeeById(employeeId),HttpStatus.OK);
    }
   
   //build update Employee RESTAPI
   //http://localhost:8080/api/employees/3
   @PutMapping("{id}")
   public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long id
		                                            ,@RequestBody Employee employee){
	    return new ResponseEntity <Employee> (employeeservice.updateEmployee(employee,id),HttpStatus.OK);
	   
	   
   }
  // build delete Employee RESTAPI
  //http://localhost:8080/api/employees/3
   @DeleteMapping("{id}")
   public ResponseEntity<String> deleteEmployee(@PathVariable("id") long id){ 
	   
	   //delete employee from db
	   employeeservice.deleteEmployee(id);
	   return new ResponseEntity <String> ("Employee deleted Successfully!",HttpStatus.OK);
	   
	   
	   
   }
   @GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 5;
		
		Page<Employee> page = employeeservice.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Employee> listEmployees = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listEmployees", listEmployees);
		return "index";
	}
   
   
}



