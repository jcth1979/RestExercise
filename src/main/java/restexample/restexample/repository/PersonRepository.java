/**
 * 
 */
package restexample.restexample.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import restexample.restexample.entities.Person;

/**
 * 
 *
 */
public interface PersonRepository extends JpaRepository<Person, Long> {

}
