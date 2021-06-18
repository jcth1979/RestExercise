/**
 * 
 */
package restexample.restexample.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import restexample.restexample.entities.Position;

/**
 * 
 *
 */
public interface PositionRepository extends JpaRepository<Position, Long> {

}
