package gov.diski.diskisso.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import gov.diski.diskisso.domain.User;

/**
 * @author Moritz Schulze
 */
public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);

}
