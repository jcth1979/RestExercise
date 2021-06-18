/**
 * 
 */
package restexample.restexample.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import restexample.restexample.entities.Employee;

/**
 * 
 *
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
