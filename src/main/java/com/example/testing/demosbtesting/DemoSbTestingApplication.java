package com.example.testing.demosbtesting;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@SpringBootApplication
public class DemoSbTestingApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoSbTestingApplication.class, args);
	}

}

@Repository
interface EmployeeRepository extends JpaRepository<Employee, Long> {
	public Employee findByName(String name);
}

interface EmployeeService {
	public Employee findByName(String n);

	List<Employee> findAll();
}

@Service
class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public Employee findByName(String n) {
		return employeeRepository.findByName(n);
	}

	@Override
	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}
}

@Entity
@Table(name = "person")
class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;


	@Size(min = 3, max = 50)
	private String name;

	Employee(){}

	Employee(String name){
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

@RestController
@RequestMapping("api")
class EmployeeController {
	@Autowired
	EmployeeService employeeService;

	@GetMapping("employees")
	public List<Employee> findAll(){
		return employeeService.findAll();
	}
}