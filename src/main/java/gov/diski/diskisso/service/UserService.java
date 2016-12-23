package gov.diski.diskisso.service;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;

import gov.diski.diskisso.domain.Role;
import gov.diski.diskisso.domain.User;
import gov.diski.diskisso.repository.UserRepository;
import gov.diski.diskisso.service.ex.EmailAlreadyExistsException;
import gov.diski.diskisso.util.RandomStringGenerator;

@Service
public class UserService {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private UserRepository userRepository;
	
	private ShaPasswordEncoder passwordEncoder;
	
	@Autowired
	public UserService(UserRepository userRepository, ShaPasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}


	public User registerUser(User user)throws EmailAlreadyExistsException{
		if (!emailExists(user.getEmail())) {
			String salt = RandomStringGenerator.generateRandomString();
			user.setPassword(passwordEncoder.encodePassword(user.getPassword(), salt));
			user.setSalt(salt);
			Set<Role> roles = new HashSet<Role>();
			roles.add(RoleService.getRoleByName(RoleService.ROLE_USER));
			user.setRoles(roles);
			user.setEnabled(true);
			return userRepository.save(user);
		}else {
			logger.error("Email is in use: {}", user.getEmail());
			throw new EmailAlreadyExistsException();
		}
		
	}
	
	public User updateUser(User user){
		User persistentUser = userRepository.findOne(user.getId());
		persistentUser.setFirstName(user.getFirstName());
		persistentUser.setLastName(user.getLastName());
		return userRepository.save(persistentUser);
		
	}
	
	public boolean emailExists(String email) {
		return userRepository.findByEmail(email) != null;
	}

}
