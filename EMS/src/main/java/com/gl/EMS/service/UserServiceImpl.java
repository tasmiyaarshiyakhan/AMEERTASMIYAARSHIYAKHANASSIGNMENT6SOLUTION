package com.gl.EMS.service;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.security.auth.login.AccountNotFoundException;
import javax.swing.text.PasswordView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greatLearning.EmployeeManagement.Repository.UserRepository;
import com.greatLearning.EmployeeManagement.model.Role;
import com.greatLearning.EmployeeManagement.model.User;

@Service
public class UserServiceImpl<BCryptPasswordEncoder> implements UserService{
	
private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public User save(UserRegistrationDto registrationDto) {
		User user = new User(registrationDto.getFirstName(), 
				registrationDto.getLastName(), registrationDto.getEmail(),
				PasswordView.encode(registrationDto.getPassword()), Arrays.asList(new Role("ROLE_USER")));
		
		return userRepository.save(user);
	}

	@Override
	public UserDetailsService loadUserByUsername(String username) {
	
		User user = userRepository.findByEmail(username);
		if(user == null) {
			throw new Exception("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));		
	}
	
	private List<SimpleGrantedAuthority> mapRolesToAuthorities(Collection<javax.management.relation.Role> collection){
		return collection.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}
}
	
	
	
	

}
