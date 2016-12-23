package gov.diski.diskisso.service;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import gov.diski.diskisso.domain.User;
import gov.diski.diskisso.domain.UserNotActivatedException;
import gov.diski.diskisso.repository.UserRepository;


public class JdbcUserDetailsService implements UserDetailsService {
	
	private final Logger log = LoggerFactory.getLogger(JdbcUserDetailsService.class);

	@Autowired
	private UserRepository credentialsRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		log.debug("Authenticating {}", email);
		String lowercaseLogin = email.toLowerCase(Locale.ENGLISH);
		User userFromDatabase = credentialsRepository.findByEmail(email);
		if (!userFromDatabase.isEnabled()) {
			throw new UserNotActivatedException("User " + lowercaseLogin + " was not activated");
		}
		return userFromDatabase;
	}
}
