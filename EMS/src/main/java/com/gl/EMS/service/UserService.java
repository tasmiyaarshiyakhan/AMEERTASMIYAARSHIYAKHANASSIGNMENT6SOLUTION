package com.gl.EMS.service;

import com.gl.EMS.model.User;

public interface UserService extends UserDetailsService{
	User save(UserRegistrationDto registrationDto);
}
