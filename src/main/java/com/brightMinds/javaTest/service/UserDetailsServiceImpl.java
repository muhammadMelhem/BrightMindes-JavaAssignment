package com.brightMinds.javaTest.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.brightMinds.javaTest.Repository.UserRepository;
import com.brightMinds.javaTest.model.Role;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		com.brightMinds.javaTest.model.User appUser = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("No existe usuario"));

		List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
		for (Role authority : appUser.getRole()) {
			GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(authority.getRole());
			grantList.add(grantedAuthority);
		}

		UserDetails user = (UserDetails) new User(appUser.getUsername(), appUser.getPassword(), grantList);
		return user;
	}
}