package gov.diski.diskisso.web.rest;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import gov.diski.diskisso.domain.User;
import gov.diski.diskisso.repository.UserRepository;
import gov.diski.diskisso.service.UserService;
import gov.diski.diskisso.web.dto.GenericResponse;

@RestController
@RequestMapping(value="user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserResource {
	
	private UserService userService;
	
	private UserRepository userRepository;

	public UserResource(UserRepository userRepository, UserService userService) {
		super();
		this.userRepository = userRepository;
		this.userService = userService;
	}
	
	@RequestMapping(value="list", method=RequestMethod.GET)
	public List<User> list(){
		return userRepository.findAll();
	}
	
	@RequestMapping(value="{id}", method=RequestMethod.GET)
	public User findUser(@PathVariable("id") User user){
		return user;
	}
	
	@RequestMapping(value = "{uId}", method = RequestMethod.DELETE)
	@ResponseBody
	public GenericResponse delete(@PathVariable("uId") Long id) {
		userRepository.delete(id);
		return new GenericResponse(true);
	}
	
	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public User createUser(@RequestBody User user) {
		return userService.registerUser(user);
	}
	
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public User updateUser(@RequestBody User user) {
		return userService.updateUser(user);
	}

}
