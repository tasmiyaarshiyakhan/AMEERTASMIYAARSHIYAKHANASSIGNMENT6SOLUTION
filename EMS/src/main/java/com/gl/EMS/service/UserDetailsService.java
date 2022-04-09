package com.gl.EMS.service;

import javax.security.auth.login.AccountNotFoundException;

public interface UserDetailsService {

	UserDetailsService loadUserByUsername(String username) throws AccountNotFoundException;

}
