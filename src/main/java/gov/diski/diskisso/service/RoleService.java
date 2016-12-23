package gov.diski.diskisso.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gov.diski.diskisso.domain.Role;
import gov.diski.diskisso.repository.RoleRepository;

@Service
public class RoleService {
	

	private RoleRepository roleRepository;
	
	public static final String ROLE_ADMIN = "ROLE_ADMIN";
	public static final String ROLE_USER = "ROLE_USER";
	
	private static Map<String, Role> roleMap = new HashMap<>();
	
	
	@Autowired
	public RoleService(RoleRepository roleRepository){
		this.roleRepository = roleRepository;
	}
	
	@PostConstruct
	public void initRoles(){
		List<Role> roleList = roleRepository.findAll();
		
		for(Role role : roleList){
			roleMap.put(role.getName(), role);
		}
	}
	
	public static Role getRoleByName(String roleName){
		return roleMap.get(roleName);
	}


}
