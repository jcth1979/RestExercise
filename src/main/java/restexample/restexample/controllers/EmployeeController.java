/**
 * 
 */
package restexample.restexample.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import restexample.restexample.entities.Employee;
import restexample.restexample.entities.Position;
import restexample.restexample.repository.EmployeeRepository;
import restexample.restexample.repository.PositionRepository;

/**
 * Employee Controller
 *
 */
@RestController
public class EmployeeController {

	private final EmployeeRepository employeeRepository;

	private final PositionRepository positionRepository;

	EmployeeController(EmployeeRepository employeeRepository, PositionRepository positionRepository) {
		this.employeeRepository = employeeRepository;
		this.positionRepository = positionRepository;
	}

	@GetMapping("/employees")
	List<Employee> all(@RequestParam String cargo, @RequestParam String nombre) {
		return employeeRepository.findAll().stream().filter(empleado -> empleado.getPosition().getName().contains(cargo)
				|| empleado.getPerson().getName().contains(nombre)).collect(Collectors.toList());
	}

	@GetMapping("/cargos")
	List<Position> allCargos() {
		return positionRepository.findAll();
	}

	@PostMapping("/employees")
	Employee createEmployee(@RequestBody Employee newEmployee) {
		return employeeRepository.save(newEmployee);
	}

	@PutMapping("/employees/{id}")
	Employee updateEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {

		return employeeRepository.findById(id).map(employee -> {
			employee.setPerson(newEmployee.getPerson());
			employee.setPosition(newEmployee.getPosition());
			employee.setSalary(newEmployee.getSalary());
			return employeeRepository.save(employee);
		}).orElseGet(() -> {
			newEmployee.setId(id);
			return employeeRepository.save(newEmployee);
		});
	}

	@DeleteMapping("/employees/{id}")
	void deleteEmployee(@PathVariable Long id) {
		employeeRepository.deleteById(id);
	}

}
