package gov.diski.diskisso.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import gov.diski.diskisso.domain.Role;

public interface RoleRepository extends JpaRepository<Role, String> {

}