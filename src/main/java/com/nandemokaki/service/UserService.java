package com.nandemokaki.service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nandemokaki.model.Login;
import com.nandemokaki.model.UserInfo;

@Service
public interface UserService extends UserDetailsService {

	Collection<GrantedAuthority> getAuthorities(String username);

	public UserInfo readUser(String username);

	public void createUser(Login user) throws Exception ;

	public void deleteUser(String username);

	public PasswordEncoder passwordEncoder();
}
